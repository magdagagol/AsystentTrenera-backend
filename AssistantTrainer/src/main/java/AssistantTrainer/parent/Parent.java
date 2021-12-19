package AssistantTrainer.parent;

import AssistantTrainer.participant.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Parent {
    @Id
    @SequenceGenerator(
            name = "parent_sequence",
            sequenceName = "parent_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parent_sequence"
    )
    Long id;
    String name;
    String surname;
    String phoneNumber;
    String email;
    Boolean contactAgree;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledParents")
    private Set<Participant> participantList = new HashSet<>();

    public Parent() {}

    public Parent(Long id, String name, String surname, String phoneNumber, String email, Boolean contactAgree) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactAgree = contactAgree;
    }

    public Parent(String name, String surname, String phoneNumber, String email, Boolean contactAgree) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactAgree = contactAgree;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getContactAgree() {
        return contactAgree;
    }

    public void setContactAgree(Boolean contactAgree) {
        this.contactAgree = contactAgree;
    }

    public Set<Participant> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(Set<Participant> participantList) {
        this.participantList = participantList;
    }

}
