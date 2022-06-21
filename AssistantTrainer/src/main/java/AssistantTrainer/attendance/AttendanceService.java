package AssistantTrainer.attendance;

import AssistantTrainer.participant.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
private final AttendanceRepository attendanceRepository;


    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAttendance(){ return attendanceRepository.findAll(); }
    public Optional<Attendance> findById(Long id){ return attendanceRepository.findById(id); }
    public Attendance getOneAttendance(Long id){ return attendanceRepository.findById(id).get(); }
    public void deleteById(Long id) {
        attendanceRepository.deleteById(id);
    }
    public Attendance save(Attendance attendance){return attendanceRepository.save(attendance); }
}
