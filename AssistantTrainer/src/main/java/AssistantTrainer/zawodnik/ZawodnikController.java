package AssistantTrainer.zawodnik;

import AssistantTrainer.exception.ApiRequestException;
import AssistantTrainer.kyu.Kyu;
import AssistantTrainer.kyu.KyuService;
import AssistantTrainer.physicalCheckup.PhysicalCheckup;
import AssistantTrainer.physicalCheckup.PhysicalCheckupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/zawodnik")
public class  ZawodnikController {

    private final ZawodnikService zawodnikService;
    private final KyuService kyuService;
    private final PhysicalCheckupService physicalCheckupService;

    @Autowired
    public ZawodnikController(ZawodnikService zawodnikService, KyuService kyuService, PhysicalCheckupService physicalCheckupService) {
        this.zawodnikService = zawodnikService;
        this.kyuService = kyuService;
        this.physicalCheckupService = physicalCheckupService;
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
        if(zawodnikService.findById(zawodnikId).isEmpty() || kyuService.findById(kyuId).isEmpty()){
            throw new ApiRequestException("Nie można przypisać zawodnika do stopnia kyu");
        }
        Zawodnik zawodnik = zawodnikService.getOneZawodnik(zawodnikId);
        Kyu kyu = kyuService.getOneKyu(kyuId);
        kyu.assignZawodnik(zawodnik);
        return kyuService.save(kyu);
    }

    @PutMapping("/{zawodnikId}/checkup/{checkupId}")
    public PhysicalCheckup enrollZawodnikToPhysicalCheckup(
            @PathVariable Long zawodnikId,
            @PathVariable Long checkupId
    ) {
        if(zawodnikService.findById(zawodnikId).isEmpty() || physicalCheckupService.findById(checkupId).isEmpty()){
        throw new ApiRequestException("Nie można przypisać zawodnika do badania");
    }
        Zawodnik zawodnik = zawodnikService.getOneZawodnik(zawodnikId);
        PhysicalCheckup physicalCheckup = physicalCheckupService.getOne(checkupId);

        physicalCheckup.assignZawodnik(zawodnik);
        return physicalCheckupService.save(physicalCheckup);
    }

    @DeleteMapping("/{id}")
    public void usunZawodnika(@PathVariable Long id) {
        zawodnikService.deleteById(id);
    }
}
