package AssistantTrainer.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public List<Attendance> getAttendance(){ return attendanceService.getAttendance(); }

    /*
        @DeleteMapping("/{parent_id}/participant/{participant_id}")
    public void delete(@PathVariable Long parent_id, @PathVariable Long participant_id){
        System.out.println("participant id: " + participant_id);
        Parent parent = parentService.getOneParent(parent_id);
        Participant participant = zawodnikService.getOneZawodnik(participant_id);

        parentService.delete(parent, participant);
    }
     */
}
