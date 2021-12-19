package AssistantTrainer.group;

import AssistantTrainer.participant.Participant;
import AssistantTrainer.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/group")
public class ParticipantGroupController {
    private final ParticipantGroupService groupService;
    private final ParticipantService participantService;

    @Autowired
    public ParticipantGroupController(ParticipantGroupService groupService, ParticipantService participantService) {
        this.groupService = groupService;
        this.participantService = participantService;
    }

    @GetMapping
    public List<ParticipantGroup> getGroup(){ return groupService.get(); }

    @PostMapping
    public void addGroup(@RequestBody ParticipantGroup participantGroup){ groupService.save(participantGroup); }

    @PutMapping("/{id}")
    public ParticipantGroup edit(@RequestBody ParticipantGroup participantGroup, @PathVariable Long id){
        return groupService.update(participantGroup, id);
    }

    @PutMapping("/{groupId}/zawodnik/{participantId}")
    public Participant addParticipantToGroup(
            @PathVariable Long groupId,
            @PathVariable Long participantId
    ){
        ParticipantGroup participantGroup = groupService.findById(groupId);
        Participant participant = participantService.getOneZawodnik(participantId);
        participant.setParticipantGroup(participantGroup);
        return participantService.save(participant);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){ groupService.delete(id); }
}
