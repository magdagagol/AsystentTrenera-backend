package AssistantTrainer.participant;

import AssistantTrainer.group.ParticipantGroup;
import AssistantTrainer.kyu.Kyu;
import AssistantTrainer.parent.Parent;
import AssistantTrainer.physicalCheckup.PhysicalCheckup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @ManyToMany
    @JoinTable(
            name="parent_enrolled",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private Set<Parent> enrolledParents = new HashSet<>();

    @OneToMany(mappedBy = "participant")
    private Set<Kyu> kyu = new HashSet<>();
    
    @OneToMany(mappedBy = "participant")
    private Set<PhysicalCheckup> physicalCheckups = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", nullable = true)
    //@JsonIgnore
    private ParticipantGroup participantGroup;

    public Participant(){}

    public Participant(Long id, String name, String surname, String yearOfBirth, String email, String phoneNumber, Set<Parent> enrolledParents, Set<Kyu> kyu, Set<PhysicalCheckup> physicalCheckups) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrolledParents = enrolledParents;
        this.kyu = kyu;
        this.physicalCheckups = physicalCheckups;
    }

    public Participant(String name, String surname, String yearOfBirth, String email, String phoneNumber, Set<Parent> enrolledParents, Set<Kyu> kyu, Set<PhysicalCheckup> physicalCheckups) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.enrolledParents = enrolledParents;
        this.kyu = kyu;
        this.physicalCheckups = physicalCheckups;
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

    public Set<Parent> getEnrolledParents() {
        return enrolledParents;
    }

    public void setEnrolledParents(Set<Parent> enrolledParents) {
        this.enrolledParents = enrolledParents;
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

    public void enrolledParents(Parent parent) {
        enrolledParents.add(parent);
    }

    public void assignKyu(Kyu kyu) { this.kyu.add(kyu); }

    public ParticipantGroup getParticipantGroup() {
        return participantGroup;
    }

    public void setParticipantGroup(ParticipantGroup participantGroup) {
        this.participantGroup = participantGroup;
    }
}
/*
{
"imie":"test",
"nazwisko":"test",
"rokUrodzenia":"test",
"email":"t@tesr",
"numerTelefonu":"111-111-111"
}
 */