package AssistantTrainer.attendance;

import AssistantTrainer.group.ParticipantGroup;
import AssistantTrainer.participant.Participant;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


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

    @JsonSerialize
    @JsonDeserialize
    //@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private Date date;
/*
    //@JsonIgnore
    @ManyToMany(mappedBy = "enrolledAttendance")
    private Set<Participant> participantList = new HashSet<>();
*/
    @ManyToMany()
    @JoinTable(
            name="enrolledParticipant",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "attendance_id")
    )
    private Set<Participant> participants = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
     private ParticipantGroup group;

    public Attendance() {
    }

    public Attendance(Long id, Date date, Set<Participant> participants) {
        this.id = id;
        this.date = date;
        this.participants = participants;
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

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public ParticipantGroup getGroup() {
        return group;
    }

    public void setGroup(ParticipantGroup group) {
        this.group = group;
    }

    public void enrolledParticipants(Participant participant) {
        participants.add(participant);
    }

    public void removeParticipants(Participant participant) {
        participants.remove(participant);
    }
}
