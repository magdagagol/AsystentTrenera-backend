package AssistantTrainer.zawodnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/zawodnik")
public class ZawodnikController {

    private final ZawodnikService zawodnikService;

    @Autowired
    public ZawodnikController(ZawodnikService zawodnikService) {
        this.zawodnikService = zawodnikService;
    }

    @GetMapping
    public List<Zawodnik> getZawodnik(){
        return zawodnikService.getZawodnik();
    }

    @PostMapping
    public void dodajZawodnika(@RequestBody Zawodnik zawodnik){
        zawodnikService.addNewZawodnik(zawodnik);
    }

}
