package AssistantTrainer.zawodnik;

import javax.persistence.*;

@Entity
@Table
public class Zawodnik {
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

    public Zawodnik(){}

    public Zawodnik(Long id, String imie, String nazwisko, String rokUrodzenia, String email, String numerTelefonu) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rokUrodzenia = rokUrodzenia;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
    }

    public Zawodnik(String imie, String nazwisko, String rokUrodzenia, String email, String numerTelefonu) {
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