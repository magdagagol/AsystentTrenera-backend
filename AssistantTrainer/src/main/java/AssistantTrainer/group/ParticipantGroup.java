package AssistantTrainer.group;

import javax.persistence.*;

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
}
