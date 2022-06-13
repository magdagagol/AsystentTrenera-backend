package AssistantTrainer.parent;

import AssistantTrainer.participant.Participant;
import AssistantTrainer.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/parent")
public class ParentController {
    private final ParentService parentService;
    private final ParticipantService zawodnikService;

    @Autowired
    public ParentController(ParentService parentService, ParticipantService participantService) {
        this.parentService = parentService;
        this.zawodnikService = participantService;
    }

   @GetMapping
   public List<Parent> getParent(){ return parentService.getParent(); }

   @PutMapping("/{id}")
   public Parent edit(@RequestBody Parent parent, @PathVariable Long id){
        return parentService.updateParent(parent, id);
   }

   @PostMapping
   public void addParent(@RequestBody Parent parent){ parentService.addNewParent(parent); }

   @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        parentService.deleteByParentId(id);
   }

    @DeleteMapping("/{parent_id}/participant/{participant_id}")
    public void delete(@PathVariable Long parent_id, @PathVariable Long participant_id){
        System.out.println("participant id: " + participant_id);
        Parent parent = parentService.getOneParent(parent_id);
        Participant participant = zawodnikService.getOneZawodnik(participant_id);

        parentService.delete(parent, participant);
    }
}
