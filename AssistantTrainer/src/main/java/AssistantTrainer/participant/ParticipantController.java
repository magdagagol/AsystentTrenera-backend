package AssistantTrainer.participant;

import AssistantTrainer.exception.ApiRequestException;
import AssistantTrainer.kyu.Kyu;
import AssistantTrainer.kyu.KyuService;
import AssistantTrainer.parent.Parent;
import AssistantTrainer.parent.ParentService;
import AssistantTrainer.physicalCheckup.PhysicalCheckup;
import AssistantTrainer.physicalCheckup.PhysicalCheckupService;
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

    @Autowired
    public ParticipantController(ParticipantService zawodnikService, KyuService kyuService, PhysicalCheckupService physicalCheckupService, ParentService parentService) {
        this.zawodnikService = zawodnikService;
        this.kyuService = kyuService;
        this.physicalCheckupService = physicalCheckupService;
        this.parentService = parentService;
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

    @PutMapping("/{participantId}/parent/{parentId}")
    public Participant enrollZawodnikToParent(
            @PathVariable Long participantId,
            @PathVariable Long parentId
    ) {
        Participant participant = zawodnikService.getOneZawodnik(participantId);
        Parent parent = parentService.getOneParent(parentId);
        participant.enrolledParents(parent);
        return zawodnikService.save(participant);
    }

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

    @DeleteMapping("/{id}")
    public void usunZawodnika(@PathVariable Long id) {
        zawodnikService.deleteById(id);
    }
}
