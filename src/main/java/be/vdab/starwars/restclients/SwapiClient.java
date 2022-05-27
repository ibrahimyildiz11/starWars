package be.vdab.starwars.restclients;

import be.vdab.starwars.dto.Film;
import be.vdab.starwars.dto.People;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
public class SwapiClient {
    private final WebClient client;
    private final String filmURI;

    public SwapiClient(WebClient.Builder builder, @Value("${swapi.film}") String filmURI) {
        client = builder.build();
        this.filmURI = filmURI;
    }

    public Optional<Film> findById(long id) {
        try {
            return Optional.ofNullable(client.get()
                    .uri(filmURI, uriBuilder -> uriBuilder.build(id))
                    .retrieve()
                    .bodyToMono(Film.class)
                    .block());
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }

    public Optional<People> findKaraktersByFilmId(String uri) {
        try {
            return Optional.ofNullable(client.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(People.class)
                    .block());
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }
}
