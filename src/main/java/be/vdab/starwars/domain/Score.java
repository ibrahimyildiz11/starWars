package be.vdab.starwars.domain;

import javax.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long filmid;
    private long score;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gebruikerid")
    private Gebruiker gebruiker;

    public Score(long filmid, Long score, Gebruiker gebruiker) {
        this.filmid = filmid;
        this.score = score;
        this.gebruiker = gebruiker;
    }

    protected Score() {
    }

    public long getId() {
        return id;
    }

    public long getFilmid() {
        return filmid;
    }

    public long getScore() {
        return score;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }
}
