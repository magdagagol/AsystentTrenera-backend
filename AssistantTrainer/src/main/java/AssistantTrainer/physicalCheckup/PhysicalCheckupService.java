package AssistantTrainer.physicalCheckup;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysicalCheckupService {
    private final PhysicalCheckupRepository physicalCheckupRepository;

    public PhysicalCheckupService(PhysicalCheckupRepository physicalCheckupRepository) {
        this.physicalCheckupRepository = physicalCheckupRepository;
    }

    public PhysicalCheckup save(PhysicalCheckup physicalCheckup){ return physicalCheckupRepository.save(physicalCheckup); }

    public List<PhysicalCheckup> getAll(){ return physicalCheckupRepository.findAll(); }

    public PhysicalCheckup getOne(Long id){ return physicalCheckupRepository.findById(id).get(); }

    public PhysicalCheckup update(PhysicalCheckup physicalCheckup, Long id){
        physicalCheckupRepository.findById(id).map(p -> {
            p.setPhysicalCheckupData(physicalCheckup.getPhysicalCheckupData());
            p.setHeight(physicalCheckup.getHeight());
            p.setWeight(physicalCheckup.getWeight());
            p.setComment(physicalCheckup.comment);
            return physicalCheckupRepository.save(p);
        });
        return physicalCheckup;
    }

    public void deleteById(Long id){ physicalCheckupRepository.deleteById(id); }
}
