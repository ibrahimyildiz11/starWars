package be.vdab.starwars.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("film")
class FilmController {
    @GetMapping
    public String film() {
        return "film";
    }
}
