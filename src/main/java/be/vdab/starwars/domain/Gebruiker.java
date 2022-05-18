package be.vdab.starwars.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "gebruikers")
public class Gebruiker {
    @Id
    private long id;
    private String familienaam;
    private String voornaam;
    private String email;
    private String paswoord;

    @OneToMany(mappedBy = "gebruiker")
    private List<Score> scores;

    public Gebruiker(String familienaam, String voornaam, String email, String paswoord) {
        this.familienaam = familienaam;
        this.voornaam = voornaam;
        this.email = email;
        this.paswoord = paswoord;
    }
    protected Gebruiker() {

    }

    public long getId() {
        return id;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmail() {
        return email;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public List<Score> getScores() {
        return scores;
    }
}
