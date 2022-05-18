package be.vdab.starwars.repositories;

import be.vdab.starwars.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findScoreByGebruikerIdAndFilmid(@Param("id") long gebruikerid, @Param("idVanFilm") long idVanFilm);
    BigDecimal findGemiddeldeScoreByFilmid(@Param("idVanFilm") long idVanFilm);
    List<Long> findDistinctFilmids();
}
