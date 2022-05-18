package be.vdab.starwars.forms;

import org.hibernate.validator.constraints.Range;

public class ScoreForm {
    @Range(min = 1, max = 10)
    private Long score;

    public ScoreForm(@Range(min = 1, max = 10) Long score) {
        this.score = score;
    }

    public Long getScore() {
        return score;
    }
}
