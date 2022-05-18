package be.vdab.starwars.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/insertGebruiker.sql")
class GebruikerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GEBRUIKERS = "gebruikers";
    private final GebruikerRepository repository;

    GebruikerRepositoryTest(GebruikerRepository repository) {
        this.repository = repository;
    }
    private String emailVanTest() {
        return jdbcTemplate.queryForObject("select email from gebruikers where email = 'test1'", String.class);
    }

    @Test
    void findByEmail() {
        assertThat(repository.findByEmail(emailVanTest()).get().getEmail()).isEqualTo("test1");
    }
}