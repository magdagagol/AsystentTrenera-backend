package AssistantTrainer.kyu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/kyu")
public class KyuController {

    private final KyuService kyuService;

    @Autowired
    public KyuController(KyuService kyuService) {
        this.kyuService = kyuService;
    }

    @GetMapping
    public List<Kyu> getKyu(){return kyuService.getKyu();}

    @PostMapping
    public void dodajKyu(@RequestBody Kyu kyu){ kyuService.addNewKyu(kyu);}
}
