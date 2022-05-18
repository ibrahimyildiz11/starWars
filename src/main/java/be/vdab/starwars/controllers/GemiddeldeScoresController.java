package be.vdab.starwars.controllers;

import be.vdab.starwars.projections.GemiddeldeScoresPerFilm;
import be.vdab.starwars.services.ScoreService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/gemiddeldescores")
class GemiddeldeScoresController {
    private final ScoreService scoreService;

    GemiddeldeScoresController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }


    @GetMapping
    CollectionModel<EntityModel<GemiddeldeScoresPerFilm>> findGemiddeldeScores() {
        return CollectionModel.of(scoreService.findDistinctFilmids().stream()
                .map(filmId -> EntityModel.of(
                        new GemiddeldeScoresPerFilm(
                                filmId, scoreService.findGemiddeldeScoreByFilmid(filmId))))::iterator);
    }
}
