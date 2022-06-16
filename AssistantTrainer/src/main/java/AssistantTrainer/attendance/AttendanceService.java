package AssistantTrainer.attendance;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
private final AttendanceRepository attendanceRepository;


    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAttendance(){ return attendanceRepository.findAll(); }
}
