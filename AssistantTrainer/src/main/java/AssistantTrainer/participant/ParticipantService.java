package AssistantTrainer.participant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    private final ParticipantRepository zawodnikRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.zawodnikRepository = participantRepository;
    }

    public List<Participant> getZawodnik(){
        return zawodnikRepository.findAll();
    }

    public Optional<Participant> findById(Long id){ return zawodnikRepository.findById(id); }

    public Participant getOneZawodnik(Long id){return zawodnikRepository.findById(id).get();}

    public void addNewZawodnik(Participant zawodnik) {
        zawodnikRepository.save(zawodnik);
    }

    public Participant updateZawodnik(Participant zawodnik, Long id) {
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

    public Participant save(Participant zawodnik){return zawodnikRepository.save(zawodnik);}

    public void deleteById(Long id) {
        zawodnikRepository.deleteById(id);
    }
}
