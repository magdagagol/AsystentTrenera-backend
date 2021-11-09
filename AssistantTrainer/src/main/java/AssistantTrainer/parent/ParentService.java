package AssistantTrainer.parent;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {
    private final ParentRepository parentRepository;
    public ParentService(ParentRepository parentRepository) {this.parentRepository = parentRepository;}

    public List<Parent> getParent(){ return parentRepository.findAll();}

    public Parent getOneParent(Long id){return parentRepository.getOne(id);}

    public void addNewParent(Parent parent){
        parentRepository.save(parent);
    }

    public void updateParent(Parent parent, Long id){
        parentRepository.findById(id).map(p -> {
            p.setName(parent.getName());
            p.setSurname(parent.getSurname());
            p.setPhoneNumber(parent.getPhoneNumber());
            p.setEmail(parent.getEmail());
            p.setContactAgree(parent.getContactAgree());
            return parentRepository.save(p);
        });
    }

    public void deleteByParentId(Long id){parentRepository.deleteById(id);}

    public Parent save(Parent parent){return parentRepository.save(parent);}
}
