package AssistantTrainer.zawodnik;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZawodnikService {
    private final ZawodnikRepository zawodnikRepository;

    @Autowired
    public ZawodnikService(ZawodnikRepository zawodnikRepository) {
        this.zawodnikRepository = zawodnikRepository;
    }

    public List<Zawodnik> getZawodnik(){
        return zawodnikRepository.findAll();
    }

    public void addNewZawodnik(Zawodnik zawodnik) {
        System.out.println(zawodnik);
        zawodnikRepository.save(zawodnik);
    }
}
