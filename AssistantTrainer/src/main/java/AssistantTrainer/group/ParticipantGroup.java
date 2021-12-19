package AssistantTrainer.group;

import AssistantTrainer.participant.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class ParticipantGroup {
    @Id
    @SequenceGenerator(
            name = "participantGroup_sequence",
            sequenceName = "participantGroup_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "participantGroup_sequence"
    )
    private Long id;
    private String name;

   // @JsonIgnore
    @OneToMany(
            mappedBy = "participantGroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Participant> participants = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }
}
