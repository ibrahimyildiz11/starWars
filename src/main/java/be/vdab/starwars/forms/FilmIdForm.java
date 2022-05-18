package be.vdab.starwars.forms;

import javax.validation.constraints.Positive;

public class FilmIdForm {
    @Positive
    private final Long filmId;

    public FilmIdForm(Long filmId) {
        this.filmId = filmId;
    }

    public Long getFilmId() {
        return filmId;
    }
}
