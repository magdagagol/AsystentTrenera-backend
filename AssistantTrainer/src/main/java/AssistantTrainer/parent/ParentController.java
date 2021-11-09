package AssistantTrainer.parent;

import AssistantTrainer.zawodnik.Zawodnik;
import AssistantTrainer.zawodnik.ZawodnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/parent")
public class ParentController {
    private final ParentService parentService;
    private final ZawodnikService zawodnikService;

    @Autowired
    public ParentController(ParentService parentService, ZawodnikService zawodnikService) {
        this.parentService = parentService;
        this.zawodnikService = zawodnikService;
    }

   @GetMapping
   public List<Parent> getParent(){return parentService.getParent();}

   @PostMapping
   public void addParent(@RequestBody Parent parent){parentService.addNewParent(parent);}

   @PutMapping("/{parentId}/zawodnik/{zawodnikId}")
   public Parent enrollZawodnikToParent(
            @PathVariable Long parentId,
            @PathVariable Long zawodnikId
   ) {
        Parent parent = parentService.getOneParent(parentId);
        Zawodnik zawodnik = zawodnikService.getOneZawodnik(zawodnikId);
        parent.enrolledParticipants(zawodnik);
        return parentService.save(parent);
   }

   @DeleteMapping
    public void delete(@PathVariable Long id){
        parentService.deleteByParentId(id);
   }
}
