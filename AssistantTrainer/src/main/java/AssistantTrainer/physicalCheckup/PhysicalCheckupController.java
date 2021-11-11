package AssistantTrainer.physicalCheckup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/physicalcheckup")
public class PhysicalCheckupController {
    public PhysicalCheckupController(PhysicalCheckupService physicalCheckupService) {
        this.physicalCheckupService = physicalCheckupService;
    }

    @Autowired
    private final PhysicalCheckupService physicalCheckupService;

    @GetMapping
    public List<PhysicalCheckup> getPhysicalCheckups(){ return physicalCheckupService.getAll(); }

    @PostMapping
    public void addPhysicalCheckup(@RequestBody PhysicalCheckup physicalCheckup){ physicalCheckupService.save(physicalCheckup); }

    @PutMapping("/{id}")
    private PhysicalCheckup update(@RequestBody PhysicalCheckup physicalCheckup, @PathVariable Long id){
        return physicalCheckupService.update(physicalCheckup, id);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id){ physicalCheckupService.deleteById(id); }
}
