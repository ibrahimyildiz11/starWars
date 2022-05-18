package be.vdab.starwars.repositories;

import be.vdab.starwars.domain.Gebruiker;
import be.vdab.starwars.domain.Score;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql({"/insertGebruiker.sql", "/insertScore.sql"})
class ScoreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String SCORES = "scores";
    private final GebruikerRepository gebruikerRepository;
    private final ScoreRepository scoreRepository;

    ScoreRepositoryTest(GebruikerRepository gebruikerRepository, ScoreRepository scoreRepository) {
        this.gebruikerRepository = gebruikerRepository;
        this.scoreRepository = scoreRepository;
    }
    String sql =
            "select gebruikerid from scores where gebruikerid = (select id from gebruikers where familienaam = 'test1')";

    private long idVanGebruikerTest() {
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Test
    void findScoreByGebruikerIdAndFilmId() {
        var score = scoreRepository.findScoreByGebruikerIdAndFilmid(idVanGebruikerTest(),1);
        assertThat(score.get().getScore()).isEqualTo(6);
    }

    @Test
    void findGemiddeldeScoreByFilmId() {
        var score = scoreRepository.findGemiddeldeScoreByFilmid(11);
        assertThat(score).isEqualByComparingTo("6");
    }

    @Test
    void findDistinctFilmids() {
        assertThat(scoreRepository.findDistinctFilmids()).contains(1L);
    }


}