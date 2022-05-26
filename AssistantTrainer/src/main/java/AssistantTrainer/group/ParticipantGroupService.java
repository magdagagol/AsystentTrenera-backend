package AssistantTrainer.group;

import AssistantTrainer.parent.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantGroupService {
    private final ParticipantGroupRepository participantGroupRepository;

    @Autowired
    public ParticipantGroupService(ParticipantGroupRepository participantGroupRepository) {
        this.participantGroupRepository = participantGroupRepository;
    }

    public ParticipantGroup findById(Long id){ return participantGroupRepository.findById(id).get(); }

    public List<ParticipantGroup> get() { return participantGroupRepository.findAll(); }

    public void save(ParticipantGroup participantGroup){ participantGroupRepository.save(participantGroup); }

    public ParticipantGroup update(ParticipantGroup participantGroup, Long id){
        participantGroupRepository.findById(id).map( group -> {
            group.setName(participantGroup.getName());
            return participantGroupRepository.save(group);
        });
        return participantGroup;
    }

    public void delete(Long id){ participantGroupRepository.deleteById(id); }

    public ParticipantGroup getOneGroup(Long id){ return participantGroupRepository.findById(id).get(); }
}
