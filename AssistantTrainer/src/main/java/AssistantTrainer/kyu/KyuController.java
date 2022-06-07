package AssistantTrainer.kyu;

import AssistantTrainer.participant.Participant;
import AssistantTrainer.participant.ParticipantService;
import AssistantTrainer.physicalCheckup.PhysicalCheckup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/kyu")
public class KyuController {

    private final KyuService kyuService;
    private final ParticipantService participantService;

    @Autowired
    public KyuController(KyuService kyuService, ParticipantService participantService) {
        this.kyuService = kyuService;
        this.participantService = participantService;
    }

    @GetMapping
    public List<Kyu> getKyu(){return kyuService.getKyu();}

    @GetMapping("/withParticipant/{id}")
    public List<Kyu> getKyuByParticipant(@PathVariable Long id){ return kyuService.findKyuWithParticipant(id); }


    @PostMapping
    public void addKyu(@RequestBody Kyu kyu){ kyuService.addNewKyu(kyu);}

    @PutMapping("/{id}")
    private Kyu update(@RequestBody Kyu kyu, @PathVariable Long id){
        return kyuService.update(kyu, id);
    }

    @PutMapping("/zawodnik/{zawodnikId}")
    public Kyu addKyuToParticipant(
            @PathVariable Long zawodnikId,
            @RequestBody Kyu kyu
    ) {
        Participant participant = participantService.getOneZawodnik(zawodnikId);
        kyu.assignZawodnik(participant);
        return kyuService.save(kyu);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ kyuService.deleteById(id); }

}
