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

    @PutMapping("/group/{groupId}")
    public Attendance addNewAttendance(
            @PathVariable Long groupId,
            @RequestBody Attendance attendance
    ) {
        ParticipantGroup participantGroup = participantGroupService.getOneGroup(groupId);
        attendance.setGroup(participantGroup);
        return attendanceService.save(attendance);
    }

    @PutMapping("/{attendanceId}/participant/{participantId}")
    public Attendance enrollParticipantToAttendance(
            @PathVariable Long attendanceId,
            @PathVariable Long participantId
    ) {
        Attendance attendance = attendanceService.getOneAttendance(attendanceId);
        Participant participant = participantService.getOneZawodnik(participantId);
        attendance.enrolledParticipants(participant);
        return attendanceService.save(attendance);
    }

    @PostMapping("/{id}")
    public Attendance update(@RequestBody Attendance attendance, @PathVariable Long id){
        return attendanceService.updateAttendance(attendance, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ attendanceService.deleteById(id); }

}
