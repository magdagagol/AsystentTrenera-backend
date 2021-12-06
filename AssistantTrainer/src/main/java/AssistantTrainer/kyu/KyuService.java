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

    public Kyu save(Kyu kyu){ return kyuRepository.save(kyu); }
}
