package AssistantTrainer.kyu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KyuService {
    private final KyuRepository kyuRepository;

    @Autowired
    public KyuService(KyuRepository kyuRepository) {
        this.kyuRepository = kyuRepository;
    }

    public List<Kyu> getKyu(){
        return kyuRepository.findAll();
    }

    public Optional<Kyu> findById(Long id){ return kyuRepository.findById(id); }

    public Kyu getOneKyu(Long id){ return kyuRepository.findById(id).get(); }

    public void addNewKyu(Kyu kyu){
        System.out.println(kyu);
        kyuRepository.save(kyu);
    }

    public Kyu update(Kyu kyu, Long id){
        kyuRepository.findById(id).map(k -> {
            k.setExamDate(kyu.getExamDate());
            k.setKyuDegree(kyu.getKyuDegree());
            return kyuRepository.save(k);
        });
        return kyu;
    }

    public Kyu save(Kyu kyu){ return kyuRepository.save(kyu); }

    public void deleteById(Long id){ kyuRepository.deleteById(id);}

    public List<Kyu> findKyuWithParticipant(Long id){ return kyuRepository.findKyuWithParticipant(id);}
}
