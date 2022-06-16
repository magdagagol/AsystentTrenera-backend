package AssistantTrainer.attendance;

import AssistantTrainer.participant.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Attendance {
    @Id
    @SequenceGenerator(
            name = "attendance_sequence",
            sequenceName = "attendance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "attendance_sequence"
    )
    private Long id;

    private Date date;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledAttendance")
    private Set<Participant> participantList = new HashSet<>();

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(Set<Participant> participantList) {
        this.participantList = participantList;
    }
}
