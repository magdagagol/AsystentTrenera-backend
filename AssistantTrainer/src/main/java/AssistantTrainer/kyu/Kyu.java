package AssistantTrainer.kyu;

import AssistantTrainer.participant.Participant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
public class Kyu {
    @Id
    @SequenceGenerator(
            name = "kyu_sequence",
            sequenceName = "kyu_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "kyu_sequence"
    )
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date examDate;
    private String kyuDegree;

    @JsonIgnore
    @ManyToOne
    //@JoinColumn(name = "participant_id")
    private Participant participant;

    public Kyu() {
    }

    public Kyu(Long id, Date examDate, String kyuDegree) {
        this.id = id;
        this.examDate = examDate;
        this.kyuDegree = kyuDegree;
    }

    public Kyu(Date examDate, String kyuDegree) {
        this.examDate = examDate;
        this.kyuDegree = kyuDegree;
    }

    public Long getId() {
        return id;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getKyuDegree() {
        return kyuDegree;
    }

    public void setKyuDegree(String kyuDegree) {
        this.kyuDegree = kyuDegree;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public void assignZawodnik(Participant participant) { this.participant = participant; }
}
