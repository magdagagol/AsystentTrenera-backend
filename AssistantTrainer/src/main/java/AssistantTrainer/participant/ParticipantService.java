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
                    zaw.setName(zawodnik.getName());
                    zaw.setSurname(zawodnik.getSurname());
                    zaw.setYearOfBirth(zawodnik.getYearOfBirth());
                    zaw.setEmail(zawodnik.getEmail());
                    zaw.setPhoneNumber(zawodnik.getPhoneNumber());
                    return zawodnikRepository.save(zaw);
        });
        return zawodnik;
    }

    public Participant save(Participant zawodnik){return zawodnikRepository.save(zawodnik);}

    public void deleteById(Long id) {
        zawodnikRepository.deleteById(id);
    }
}
