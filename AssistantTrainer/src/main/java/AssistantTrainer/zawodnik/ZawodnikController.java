package AssistantTrainer.zawodnik;

import AssistantTrainer.kyu.Kyu;
import AssistantTrainer.kyu.KyuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/zawodnik")
public class  ZawodnikController {

    private final ZawodnikService zawodnikService;
    private final KyuService kyuService;

    @Autowired
    public ZawodnikController(ZawodnikService zawodnikService, KyuService kyuService) {
        this.zawodnikService = zawodnikService;
        this.kyuService = kyuService;
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
        return zawodnikService.updateZawodnik(zawodnik, id);
    }

    @PutMapping("/{zawodnikId}/kyu/{kyuId}")
    public Kyu enrollZawodnikToKyu(
            @PathVariable Long zawodnikId,
            @PathVariable Long kyuId
    ) {
        Zawodnik zawodnik = zawodnikService.getOneZawodnik(zawodnikId);
        Kyu kyu = kyuService.getOneKyu(kyuId);
        kyu.assignZawodnik(zawodnik);
        return kyuService.save(kyu);
    }

    @DeleteMapping("/{id}")
    public void usunZawodnika(@PathVariable Long id) {
        zawodnikService.deleteById(id);
    }
}
