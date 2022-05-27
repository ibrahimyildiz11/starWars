package be.vdab.starwars.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SwapiClientTest {
    private final SwapiClient client;

    SwapiClientTest(SwapiClient client) {
        this.client = client;
    }

    @Test
    void findBestaandeFilm() {
        assertThat(client.findById(1).get().getTitle()).isEqualTo("A New Hope");
    }

    @Test
    void findOnbestaandeFilm() {
        assertThat(client.findById(-1)).isEmpty();
    }
}