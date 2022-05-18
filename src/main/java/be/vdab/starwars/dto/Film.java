package be.vdab.starwars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class Film {
    private String title;
    private String director;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    @JsonProperty("characters")
    private List<String> characters;

    public Film(String title, String director, LocalDate releaseDate, List<String> characters) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.characters = characters;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }
}
