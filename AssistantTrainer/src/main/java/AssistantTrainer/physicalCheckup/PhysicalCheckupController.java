package AssistantTrainer.physicalCheckup;

import AssistantTrainer.participant.Participant;
import AssistantTrainer.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/physicalCheckup")
public class PhysicalCheckupController {
    public PhysicalCheckupController(PhysicalCheckupService physicalCheckupService, ParticipantService zawodnikService) {
        this.physicalCheckupService = physicalCheckupService;
        this.zawodnikService = zawodnikService;
    }

    @Autowired
    private final PhysicalCheckupService physicalCheckupService;

    private final ParticipantService zawodnikService;

    @GetMapping
    public List<PhysicalCheckup> getPhysicalCheckups(){ return physicalCheckupService.getAll(); }

    @GetMapping("/withParticipant/{id}")
        public List<PhysicalCheckup> getPhysicalCheckupsByParticipant(@PathVariable Long id){ return physicalCheckupService.findPhysicalCheckupWithParticipant(id); }

    @PostMapping
    public void addPhysicalCheckup(@RequestBody PhysicalCheckup physicalCheckup){ physicalCheckupService.save(physicalCheckup); }

    @PutMapping("/{id}")
    private PhysicalCheckup update(@RequestBody PhysicalCheckup physicalCheckup, @PathVariable Long id){
        return physicalCheckupService.update(physicalCheckup, id);
    }

    @PutMapping("/zawodnik/{zawodnikId}")
    public PhysicalCheckup addZawodnikToPhysicalCheckup(
            @PathVariable Long zawodnikId,
            @RequestBody PhysicalCheckup physicalCheckup
    ) {
        Participant zawodnik = zawodnikService.getOneZawodnik(zawodnikId);
        physicalCheckup.assignZawodnik(zawodnik);
        return physicalCheckupService.save(physicalCheckup);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ physicalCheckupService.deleteById(id); }
}
