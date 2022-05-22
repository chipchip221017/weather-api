package com.oddle.app.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Clouds implements Serializable {
    @JsonProperty("all")
    private Integer all;

    @JsonProperty("all")
    public Integer getAll() {
        return all;
    }

    @JsonProperty("all")
    public void setAll(Integer all) {
        this.all = all;
    }
}
