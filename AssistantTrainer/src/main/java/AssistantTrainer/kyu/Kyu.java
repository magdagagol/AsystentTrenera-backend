package AssistantTrainer.kyu;

import AssistantTrainer.participant.Participant;

import javax.persistence.*;


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
    private String dataEgzaminu;
    private String stopienKyu;

    @ManyToOne
    @JoinColumn(name = "zawodnik_id")
    private Participant zawodnik;

    public Kyu() {
    }

    public Kyu(Long id, String dataEgzaminu, String stopienKyu) {
        this.id = id;
        this.dataEgzaminu = dataEgzaminu;
        this.stopienKyu = stopienKyu;
    }

    public Kyu(String dataEgzaminu, String stopienKyu) {
        this.dataEgzaminu = dataEgzaminu;
        this.stopienKyu = stopienKyu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataEgzaminu() {
        return dataEgzaminu;
    }

    public void setDataEgzaminu(String dataEgzaminu) {
        this.dataEgzaminu = dataEgzaminu;
    }

    public String getStopienKyu() {
        return stopienKyu;
    }

    public void setStopienKyu(String stopienKyu) {
        this.stopienKyu = stopienKyu;
    }

    public Participant getZawodnik() {
        return zawodnik;
    }

    public void assignZawodnik(Participant zawodnik) {
        this.zawodnik = zawodnik;
    }
}
/*
{
    "dataEgzaminu": "123",
    "stopienKyu": "zolty"
}
 */