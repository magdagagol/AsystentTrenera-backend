package AssistantTrainer.physicalCheckup;

import AssistantTrainer.participant.Participant;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class PhysicalCheckup {
    @Id
    @SequenceGenerator(
            name = "physicalCheckup_sequence",
            sequenceName = "physicalCheckup_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "physicalCheckup_sequence"
    )
    Long id;
    Date physicalCheckupData;
    Float height;

    Float weight;
    String comment;

    @ManyToOne
    @JoinColumn(name = "zawodnik_id")
    private Participant zawodnik;

    public PhysicalCheckup() {}

    public PhysicalCheckup(Long id, Date physicalCheckupData, Float height, Float weight, String comment) {
        this.id = id;
        this.physicalCheckupData = physicalCheckupData;
        this.height = height;
        this.weight = weight;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPhysicalCheckupData() {
        return physicalCheckupData;
    }

    public void setPhysicalCheckupData(Date physicalCheckupData) {
        this.physicalCheckupData = physicalCheckupData;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Participant getZawodnik() {
        return zawodnik;
    }

    public void assignZawodnik(Participant zawodnik) {
        this.zawodnik = zawodnik;
    }
}
