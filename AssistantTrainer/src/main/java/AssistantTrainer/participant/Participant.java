package AssistantTrainer.participant;

import AssistantTrainer.attendance.Attendance;
import AssistantTrainer.group.ParticipantGroup;
import AssistantTrainer.kyu.Kyu;
import AssistantTrainer.parent.Parent;
import AssistantTrainer.physicalCheckup.PhysicalCheckup;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Participant {
    @Id
    @SequenceGenerator(
            name = "participant_sequence",
            sequenceName = "participant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participant_sequence"
    )
    private Long id;
    private String name;
    private String surname;
    private String yearOfBirth;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "participant", cascade=CascadeType.ALL)
    private Set<Parent> parents = new HashSet<>();

    @OneToMany(mappedBy = "participant", cascade=CascadeType.ALL)
    private Set<Kyu> kyu = new HashSet<>();
    
    @OneToMany(mappedBy = "participant", cascade=CascadeType.ALL)
    private Set<PhysicalCheckup> physicalCheckups = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", nullable = true)
    //@JsonIgnore
    private ParticipantGroup participantGroup;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="enrolledAttendance",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "attendance_id")
    )
    private Set<Attendance> enrolledAttendance = new HashSet<>();
    public Participant(){}

    public Participant(Long id, String name, String surname, String yearOfBirth, String email, String phoneNumber, Set<Parent> parents, Set<Kyu> kyu, Set<PhysicalCheckup> physicalCheckups, ParticipantGroup participantGroup, Set<Attendance> enrolledAttendance) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.parents = parents;
        this.kyu = kyu;
        this.physicalCheckups = physicalCheckups;
        this.participantGroup = participantGroup;
        this.enrolledAttendance = enrolledAttendance;
    }

    public Participant(String name, String surname, String yearOfBirth, String email, String phoneNumber, Set<Parent> parents, Set<Kyu> kyu, Set<PhysicalCheckup> physicalCheckups, ParticipantGroup participantGroup, Set<Attendance> enrolledAttendance) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.parents = parents;
        this.kyu = kyu;
        this.physicalCheckups = physicalCheckups;
        this.participantGroup = participantGroup;
        this.enrolledAttendance = enrolledAttendance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Parent> getParents() {
        return parents;
    }

    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }

    public Set<Kyu> getKyu() {
        return kyu;
    }

    public void setKyu(Set<Kyu> kyu) {
        this.kyu = kyu;
    }

    public Set<PhysicalCheckup> getPhysicalCheckups() {
        return physicalCheckups;
    }

    public void setPhysicalCheckups(Set<PhysicalCheckup> physicalCheckups) {
        this.physicalCheckups = physicalCheckups;
    }

    public ParticipantGroup getParticipantGroup() {
        return participantGroup;
    }

    public void setParticipantGroup(ParticipantGroup participantGroup) {
        this.participantGroup = participantGroup;
    }

    public Set<Attendance> getEnrolledAttendance() {
        return enrolledAttendance;
    }

    public void setEnrolledAttendance(Set<Attendance> enrolledAttendance) {
        this.enrolledAttendance = enrolledAttendance;
    }

    public void enrolledAttendance(Attendance attendance) {
       enrolledAttendance.add(attendance);
   }

   public void assignKyu(Kyu kyu) { this.kyu.add(kyu); }

}