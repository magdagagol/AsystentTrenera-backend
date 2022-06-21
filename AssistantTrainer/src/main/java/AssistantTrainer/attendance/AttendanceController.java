package AssistantTrainer.attendance;

import AssistantTrainer.group.ParticipantGroup;
import AssistantTrainer.group.ParticipantGroupService;
import AssistantTrainer.participant.Participant;
import AssistantTrainer.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final ParticipantGroupService participantGroupService;
    private final ParticipantService participantService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService, ParticipantGroupService participantGroupService, ParticipantService participantService) {
        this.attendanceService = attendanceService;
        this.participantGroupService = participantGroupService;
        this.participantService = participantService;
    }

    @GetMapping
    public List<Attendance> getAttendance(){ return attendanceService.getAttendance(); }

    //@PostMapping()
    //public void addNewAttendance(@RequestBody Attendance attendance){
    //    System.out.println("attendance: " + attendance.getDate().toString());
    //    System.out.println("date now: " + LocalDateTime.now());
    //    attendanceService.save(attendance);
    //}

    @PutMapping("/group/{groupId}")
    public Attendance addNewAttendance(
            @PathVariable Long groupId,
            @RequestBody Attendance attendance
    ) {
        ParticipantGroup participantGroup = participantGroupService.getOneGroup(groupId);
        attendance.setGroup(participantGroup);
        return attendanceService.save(attendance);
    }

    public Attendance updateAttendance(Attendance attendance, Long id) {
        attendanceService.findById(id).map( atend -> {
            atend.setDate(attendance.getDate());
            return null;
        });
        return attendance;
    }

    @PutMapping("/{attendanceId}/ participant/{ participantId}")
    public Attendance enrollParticipantToAttendance(
            @PathVariable Long attendanceId,
            @PathVariable Long  participantId
    ) {
        Attendance attendance = attendanceService.getOneAttendance(attendanceId);
        Participant participant = participantService.getOneZawodnik(participantId);
        attendance.enrolledParticipants(participant);
        return attendanceService.save(attendance);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ attendanceService.deleteById(id); }

}
