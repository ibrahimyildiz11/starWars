package be.vdab.starwars.controllers;

import be.vdab.starwars.domain.Score;
import be.vdab.starwars.forms.FilmIdForm;
import be.vdab.starwars.forms.ScoreForm;
import be.vdab.starwars.restclients.SwapiClient;
import be.vdab.starwars.services.GebruikerService;
import be.vdab.starwars.services.ScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.LinkedHashSet;

@Controller
@RequestMapping("films")
class FilmController {
    private final SwapiClient client;
    private final ScoreService scoreService;
    private final GebruikerService gebruikerService;

    FilmController(SwapiClient client, ScoreService scoreService, GebruikerService gebruikerService) {
        this.client = client;
        this.scoreService = scoreService;
        this.gebruikerService = gebruikerService;
    }

    @GetMapping("form")
    public ModelAndView filmZoeken() {
        return new ModelAndView("filmzoeken")
                .addObject(new FilmIdForm(null));
    }

    @GetMapping
    public ModelAndView getFilm(@Valid FilmIdForm form, Principal principal, Errors errors) {
        var modelAndView = new ModelAndView("filmzoeken");
        if (errors.hasErrors()) {
            return modelAndView;
        }
        var people = new LinkedHashSet<>();
        client.findById(form.getFilmId())
                .ifPresent(film -> film.getCharacters()
                        .stream().forEach(peopleURL ->
                                people.add(client.findKaraktersByFilmId(peopleURL).get().getName())));
        client.findById(form.getFilmId()).ifPresent(film -> modelAndView.addObject(film)
                .addObject("people", people)
                .addObject(new FilmIdForm(form.getFilmId())));
        scoreService.findScoreByGebruikerIdAndFilmid(gebruikerService
                .findByEmail(principal.getName()).get().getId(), form.getFilmId())
                .ifPresentOrElse(score -> modelAndView.addObject("score", score.getScore()),
                        () -> modelAndView.addObject("scoreForm", new ScoreForm(null)));
        return modelAndView;
    }

    @PostMapping("{id}")
    public String bewaarScore(@PathVariable long id, @Valid ScoreForm scoreForm, Errors errors, Principal principal,
                              RedirectAttributes redirect) {
        redirect.addFlashAttribute("filmIdForm", id);
        if (errors.hasErrors()) {
            return "redicect:/films";
        }
        if (scoreService.findScoreByGebruikerIdAndFilmid(
                gebruikerService.findByEmail(principal.getName()).get().getId(), id).isEmpty()) {
            scoreService.create(new Score(
                    id, scoreForm.getScore(), gebruikerService.findByEmail(principal.getName()).get()));
        }
        return "redirect:/films";
    }
}
