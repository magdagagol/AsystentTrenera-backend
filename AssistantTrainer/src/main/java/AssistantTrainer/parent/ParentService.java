package AssistantTrainer.parent;

import AssistantTrainer.participant.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {
    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) { this.parentRepository = parentRepository; }

    public Parent save(Parent parent){ return parentRepository.save(parent); }

    public Optional<Parent> findById(Long id){ return parentRepository.findById(id); }

    public List<Parent> getParent(){ return parentRepository.findAll(); }

    public Parent getOneParent(Long id){ return parentRepository.findById(id).get(); }

    /*TODO
    code refactor method addNewParent and method save
    */
    public void addNewParent(Parent parent){
        parentRepository.save(parent);
    }

    public Parent updateParent(Parent parent, Long id){
        parentRepository.findById(id).map(p -> {
            p.setName(parent.getName());
            p.setSurname(parent.getSurname());
            p.setPhoneNumber(parent.getPhoneNumber());
            p.setEmail(parent.getEmail());
            p.setContactAgree(parent.getContactAgree());
            return parentRepository.save(p);
        });
        return parent;
    }

    public void deleteByParentId(Long id){
        parentRepository.deleteById(id);}

   //public void delete(Parent parent, Participant participant){
   //   // parent.getParticipantList().remove(participant);
   //    //participant.getEnrolledParents().remove(parent);
   //    System.out.println("Delete parent");
   //}
}
