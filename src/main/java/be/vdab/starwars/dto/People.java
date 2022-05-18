package be.vdab.starwars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class People {
    @JsonProperty
    private String name;

    public String getName() {
        return name;
    }
}
