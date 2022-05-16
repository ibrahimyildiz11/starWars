package be.vdab.starwars.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("score")
class ScoreController {
    @GetMapping
    public String score() {
        return "score";
    }
}
