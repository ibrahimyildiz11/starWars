package be.vdab.starwars.services;

import be.vdab.starwars.domain.Score;
import be.vdab.starwars.repositories.ScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScoreService {
    private final ScoreRepository repository;

    public ScoreService(ScoreRepository repository) {
        this.repository = repository;
    }

    public void create(Score score) {
        repository.save(score);
    }

    public Optional<Score> findScoreByGebruikerIdAndFilmid(long gebruikerid, long idVanFilm) {
        return repository.findScoreByGebruikerIdAndFilmid(gebruikerid, idVanFilm);
    }

    public BigDecimal findGemiddeldeScoreByFilmid(long idVanFilm) {
        return repository.findGemiddeldeScoreByFilmid(idVanFilm);
    }

    public List<Long> findDistinctFilmids() {
        return repository.findDistinctFilmids();
    }
}
