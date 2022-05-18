package be.vdab.starwars.projections;

import java.math.BigDecimal;

public class GemiddeldeScoresPerFilm {
    private final long filmId;
    private final BigDecimal gemiddelde;

    public GemiddeldeScoresPerFilm(long filmId, BigDecimal gemiddelde) {
        this.filmId = filmId;
        this.gemiddelde = gemiddelde;
    }

    public long getFilmId() {
        return filmId;
    }

    public BigDecimal getGemiddelde() {
        return gemiddelde;
    }
}
