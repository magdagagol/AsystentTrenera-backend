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

    public Zawodnik getOneZawodnik(Long id){return zawodnikRepository.findById(id).get();}

    public void addNewZawodnik(Zawodnik zawodnik) {
        zawodnikRepository.save(zawodnik);
    }

    public Zawodnik updateZawodnik(Zawodnik zawodnik, Long id) {
        zawodnikRepository.findById(id).map( zaw -> {
                    zaw.setImie(zawodnik.getImie());
                    zaw.setNazwisko(zawodnik.getNazwisko());
                    zaw.setRokUrodzenia(zawodnik.getRokUrodzenia());
                    zaw.setEmail(zawodnik.getEmail());
                    zaw.setNumerTelefonu(zawodnik.getNumerTelefonu());
                    return zawodnikRepository.save(zaw);
        });
        return zawodnik;
    }

    public Zawodnik save(Zawodnik zawodnik){return zawodnikRepository.save(zawodnik);}

    public void deleteById(Long id) {
        zawodnikRepository.deleteById(id);
    }
}
