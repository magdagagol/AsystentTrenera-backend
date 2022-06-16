package AssistantTrainer.participant;

import AssistantTrainer.exception.ApiRequestException;
import AssistantTrainer.group.ParticipantGroup;
import AssistantTrainer.group.ParticipantGroupService;
import AssistantTrainer.kyu.Kyu;
import AssistantTrainer.kyu.KyuService;
import AssistantTrainer.parent.Parent;
import AssistantTrainer.parent.ParentService;
import AssistantTrainer.physicalCheckup.PhysicalCheckup;
import AssistantTrainer.physicalCheckup.PhysicalCheckupService;
import org.apache.catalina.Group;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/zawodnik")
public class ParticipantController {

    private final ParticipantService zawodnikService;
    private  final ParentService parentService;
    private final KyuService kyuService;
    private final PhysicalCheckupService physicalCheckupService;
    private final ParticipantGroupService participantGroupService;

    @Autowired
    public ParticipantController(ParticipantService zawodnikService, KyuService kyuService, PhysicalCheckupService physicalCheckupService, ParentService parentService, ParticipantGroupService participantGroupService) {
        this.zawodnikService = zawodnikService;
        this.kyuService = kyuService;
        this.physicalCheckupService = physicalCheckupService;
        this.parentService = parentService;
        this.participantGroupService = participantGroupService;
    }

    @GetMapping
    public List<Participant> getZawodnik(){
        return zawodnikService.getZawodnik();
    }

    @PostMapping
    public void dodajZawodnika(@RequestBody Participant zawodnik){
        zawodnikService.addNewZawodnik(zawodnik);
    }

    @PutMapping("/{id}")
    public Participant edytujZawodnika(@RequestBody Participant zawodnik, @PathVariable Long id){
        return zawodnikService.updateZawodnik(zawodnik, id);
    }

    //@PutMapping("/{participantId}/parent/{parentId}")
    //public Participant enrollZawodnikToParent(
    //        @PathVariable Long participantId,
    //        @PathVariable Long parentId
    //) {
    //    Participant participant = zawodnikService.getOneZawodnik(participantId);
    //    Parent parent = parentService.getOneParent(parentId);
    //    participant.enrolledParents(parent);
    //    return zawodnikService.save(participant);
    //}

    @PutMapping("/{zawodnikId}/kyu/{kyuId}")
    public Kyu enrollZawodnikToKyu(
            @PathVariable Long zawodnikId,
            @PathVariable Long kyuId
    ) {
        if(zawodnikService.findById(zawodnikId).isEmpty() || kyuService.findById(kyuId).isEmpty()){
            throw new ApiRequestException("Nie można przypisać zawodnika do stopnia kyu");
        }
        Participant zawodnik = zawodnikService.getOneZawodnik(zawodnikId);
        Kyu kyu = kyuService.getOneKyu(kyuId);
        kyu.assignZawodnik(zawodnik);
        return kyuService.save(kyu);
    }

    @PutMapping("/{zawodnikId}/checkup/{checkupId}")
    public PhysicalCheckup enrollZawodnikToPhysicalCheckup(
            @PathVariable Long zawodnikId,
            @PathVariable Long checkupId
    ) {
        if(zawodnikService.findById(zawodnikId).isEmpty() || physicalCheckupService.findById(checkupId).isEmpty()){
        throw new ApiRequestException("Nie można przypisać zawodnika do badania");
    }
        Participant zawodnik = zawodnikService.getOneZawodnik(zawodnikId);
        PhysicalCheckup physicalCheckup = physicalCheckupService.getOne(checkupId);

        physicalCheckup.assignZawodnik(zawodnik);
        return physicalCheckupService.save(physicalCheckup);
    }

    @PutMapping("/{zawodnikId}/checkup")
    public PhysicalCheckup addZawodnikToPhysicalCheckup(
            @PathVariable Long zawodnikId,
            @RequestBody PhysicalCheckup physicalCheckup
    ) {
        Participant zawodnik = zawodnikService.getOneZawodnik(zawodnikId);
        physicalCheckup.assignZawodnik(zawodnik);
        return physicalCheckupService.save(physicalCheckup);
    }

    @DeleteMapping("/{id}")
    public void usunZawodnika(@PathVariable Long id) {
        zawodnikService.deleteById(id);
    }

    @GetMapping("/group/{id}")
    public List<Participant> getParticipantsWithGroup(@PathVariable Long id){
        return zawodnikService.findParticipantWithGroup(id);
    }

    @GetMapping("/withoutGroup/{id}")
    public List<Participant> getParticipantsWithoutGroup(@PathVariable Long id){
        return zawodnikService.findParticipantWithoutGroup(id);
    }

    @PutMapping("/{participantId}/withGroup/{groupId}")
    public Participant enrollParticipantToGroup(
            @PathVariable Long participantId,
            @PathVariable Long groupId
    ) {
        Participant participant = zawodnikService.getOneZawodnik(participantId);
        ParticipantGroup participantGroup = participantGroupService.getOneGroup(groupId);
        participant.setParticipantGroup(participantGroup);
        return zawodnikService.save(participant);
    }

    @PutMapping("/{participantId}/withGroup/null")
    public Participant enrollParticipantRemoveGroup(@PathVariable Long participantId) {
        Participant participant = zawodnikService.getOneZawodnik(participantId);
        participant.setParticipantGroup(null);
        return zawodnikService.save(participant);
    }
}
