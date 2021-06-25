package AssistantTrainer.zawodnik;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{id}")
    public Zawodnik edytujZawodnika(@RequestBody Zawodnik zawodnik, @PathVariable Long id){
        System.out.println("sssssssssssssssssss " + id);
        zawodnikService.updateZawodnik(zawodnik, id);
        return null;
    }

    @DeleteMapping("/{id}")
    public void usunZawodnika(@PathVariable Long id) {
        zawodnikService.deleteById(id);
    }
}
