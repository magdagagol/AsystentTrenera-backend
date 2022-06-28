package AssistantTrainer.participant;

import AssistantTrainer.attendance.Attendance;
import AssistantTrainer.attendance.AttendanceService;
import AssistantTrainer.exception.ApiRequestException;
import AssistantTrainer.group.ParticipantGroup;
import AssistantTrainer.group.ParticipantGroupService;
import AssistantTrainer.kyu.Kyu;
import AssistantTrainer.kyu.KyuService;
import AssistantTrainer.parent.Parent;
import AssistantTrainer.parent.ParentService;
import AssistantTrainer.physicalCheckup.PhysicalCheckup;
import AssistantTrainer.physicalCheckup.PhysicalCheckupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/zawodnik")
public class ParticipantController {

    private final ParticipantService  participantService;
    private  final ParentService parentService;
    private final KyuService kyuService;
    private final PhysicalCheckupService physicalCheckupService;
    private final ParticipantGroupService participantGroupService;

    private final AttendanceService attendanceService;

    @Autowired
    public ParticipantController(ParticipantService  participantService, KyuService kyuService, PhysicalCheckupService physicalCheckupService, ParentService parentService, ParticipantGroupService participantGroupService, AttendanceService attendanceService) {
        this. participantService =  participantService;
        this.kyuService = kyuService;
        this.physicalCheckupService = physicalCheckupService;
        this.parentService = parentService;
        this.participantGroupService = participantGroupService;
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public List<Participant> getZawodnik(){ return  participantService.getZawodnik(); }

    @PostMapping
    public void dodajZawodnika(@RequestBody Participant zawodnik){
         participantService.addNewZawodnik(zawodnik);
    }

    @PutMapping("/{id}")
    public Participant edytujZawodnika(@RequestBody Participant zawodnik, @PathVariable Long id){
        return  participantService.updateZawodnik(zawodnik, id);
    }

    @PutMapping("/{participantId}/parent/{parentId}")
    public Parent enrollZawodnikToParent(
            @PathVariable Long zawodnikId,
            @PathVariable Long parentId
    ) {
        if( participantService.findById(zawodnikId).isEmpty() || parentService.findById(parentId).isEmpty()){
            throw new ApiRequestException("Nie można przypisać zawodnika do stopnia kyu");
        }
        Participant zawodnik =  participantService.getOneZawodnik(zawodnikId);
        Parent parent = parentService.getOneParent(parentId);
        parent.assignParticipant(zawodnik);
        return parentService.save(parent);
    }

    @PutMapping("/{zawodnikId}/kyu/{kyuId}")
    public Kyu enrollZawodnikToKyu(
            @PathVariable Long zawodnikId,
            @PathVariable Long kyuId
    ) {
        if( participantService.findById(zawodnikId).isEmpty() || kyuService.findById(kyuId).isEmpty()){
            throw new ApiRequestException("Nie można przypisać zawodnika do stopnia kyu");
        }
        Participant zawodnik =  participantService.getOneZawodnik(zawodnikId);
        Kyu kyu = kyuService.getOneKyu(kyuId);
        kyu.assignZawodnik(zawodnik);
        return kyuService.save(kyu);
    }

    @PutMapping("/{zawodnikId}/checkup/{checkupId}")
    public PhysicalCheckup enrollZawodnikToPhysicalCheckup(
            @PathVariable Long zawodnikId,
            @PathVariable Long checkupId
    ) {
        if( participantService.findById(zawodnikId).isEmpty() || physicalCheckupService.findById(checkupId).isEmpty()){
        throw new ApiRequestException("Nie można przypisać zawodnika do badania");
    }
        Participant zawodnik =  participantService.getOneZawodnik(zawodnikId);
        PhysicalCheckup physicalCheckup = physicalCheckupService.getOne(checkupId);

        physicalCheckup.assignZawodnik(zawodnik);
        return physicalCheckupService.save(physicalCheckup);
    }

    @PutMapping("/{zawodnikId}/checkup")
    public PhysicalCheckup addZawodnikToPhysicalCheckup(
            @PathVariable Long zawodnikId,
            @RequestBody PhysicalCheckup physicalCheckup
    ) {
        Participant zawodnik =  participantService.getOneZawodnik(zawodnikId);
        physicalCheckup.assignZawodnik(zawodnik);
        return physicalCheckupService.save(physicalCheckup);
    }

    @DeleteMapping("/{id}")
    public void removeParticipant(@PathVariable Long id) {
       Participant participant = participantService.getOneZawodnik(id);

       participant.getattendances().forEach(p -> {
           p.removeParticipants(participant);
       });

        participantService.deleteById(id);
    }

    @GetMapping("/group/{id}")
    public List<Participant> getParticipantsWithGroup(@PathVariable Long id){
        return  participantService.findParticipantWithGroup(id);
    }

    @GetMapping("/withoutGroup/{id}")
    public List<Participant> getParticipantsWithoutGroup(@PathVariable Long id){
        return  participantService.findParticipantWithoutGroup(id);
    }

    @PutMapping("/{participantId}/withGroup/{groupId}")
    public Participant enrollParticipantToGroup(
            @PathVariable Long participantId,
            @PathVariable Long groupId
    ) {
        Participant participant =  participantService.getOneZawodnik(participantId);
        ParticipantGroup participantGroup = participantGroupService.getOneGroup(groupId);
        participant.setParticipantGroup(participantGroup);
        return  participantService.save(participant);
    }

    @PutMapping("/{participantId}/withGroup/null")
    public Participant enrollParticipantRemoveGroup(@PathVariable Long participantId) {
        Participant participant =  participantService.getOneZawodnik(participantId);
        participant.setParticipantGroup(null);
        return  participantService.save(participant);
    }
}
