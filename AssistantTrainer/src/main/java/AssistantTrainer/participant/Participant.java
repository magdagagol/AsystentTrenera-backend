package AssistantTrainer.participant;

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
            name = "zawodnik_sequence",
            sequenceName = "zawodnik_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    private String imie;
    private String nazwisko;
    private String rokUrodzenia;
    private String email;
    private String numerTelefonu;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledParticipants")
    private Set<Parent> parents = new HashSet<>();

    @OneToMany(mappedBy = "zawodnik")
    private Set<Kyu> kyu = new HashSet<>();

    @OneToMany(mappedBy = "zawodnik")
    private Set<PhysicalCheckup> physicalCheckups = new HashSet<>();

    public Participant(){}

    public Participant(Long id, String imie, String nazwisko, String rokUrodzenia, String email, String numerTelefonu) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
    }

    public Participant(String imie, String nazwisko, String rokUrodzenia, String email, String numerTelefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getRokUrodzenia() {
        return rokUrodzenia;
    }

    public void setRokUrodzenia(String rokUrodzenia) {
        this.rokUrodzenia = rokUrodzenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public Set<Parent> getParents() {
        return parents;
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