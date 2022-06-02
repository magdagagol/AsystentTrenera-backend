package AssistantTrainer.physicalCheckup;

import AssistantTrainer.participant.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhysicalCheckupService {
    private final PhysicalCheckupRepository physicalCheckupRepository;

    public PhysicalCheckupService(PhysicalCheckupRepository physicalCheckupRepository) {
        this.physicalCheckupRepository = physicalCheckupRepository;
    }

    public PhysicalCheckup save(PhysicalCheckup physicalCheckup){ return physicalCheckupRepository.save(physicalCheckup); }

    public List<PhysicalCheckup> getAll(){ return physicalCheckupRepository.findAll(); }

    public Optional<PhysicalCheckup> findById(Long id){ return physicalCheckupRepository.findById(id); }

    public PhysicalCheckup getOne(Long id){ return physicalCheckupRepository.findById(id).get(); }

    public PhysicalCheckup update(PhysicalCheckup physicalCheckup, Long id){
        physicalCheckupRepository.findById(id).map(p -> {
            p.setPhysicalCheckupData(physicalCheckup.getPhysicalCheckupData());
            p.setHeight(physicalCheckup.getHeight());
            p.setWeight(physicalCheckup.getWeight());
            p.setComment(physicalCheckup.getComment());
            return physicalCheckupRepository.save(p);
        });
        return physicalCheckup;
    }

    public void deleteById(Long id){ physicalCheckupRepository.deleteById(id); }

    public List<PhysicalCheckup> findPhysicalCheckupWithParticipant(Long id) { return physicalCheckupRepository.findPhysicalCheckupWithParticipant(id); }
}
