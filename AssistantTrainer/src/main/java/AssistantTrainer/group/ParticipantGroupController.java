package AssistantTrainer.group;

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        ParticipantGroup participantGroup = groupService.getOneGroup(id);
        participantGroup.getParticipants().forEach(p -> {
            p.setParticipantGroup(null);
        });
        groupService.delete(id);
    }
}
