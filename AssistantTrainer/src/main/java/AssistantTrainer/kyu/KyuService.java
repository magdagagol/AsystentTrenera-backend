package AssistantTrainer.kyu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addNewKyu(Kyu kyu){
        System.out.println(kyu);
        kyuRepository.save(kyu);
    }
}
