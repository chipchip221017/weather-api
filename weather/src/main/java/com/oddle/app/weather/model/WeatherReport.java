package com.oddle.app.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "coord",
        "weather",
        "base",
        "main",
        "visibility",
        "wind",
        "clouds",
        "dt",
        "sys",
        "timezone",
        "id",
        "name",
        "cod"
})
@Entity
@Table(name = "weather_report")
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class WeatherReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @JsonProperty("coord")
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Coord coord;

    @JsonProperty("weather")
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<WeatherDetail> weather = new ArrayList<>();

    @JsonProperty("base")
    @Column(name = "base")
    private String base;

    @JsonProperty("main")
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Main main;

    @JsonProperty("visibility")
    @Column(name = "visibility")
    private Integer visibility;

    @JsonProperty("wind")
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Wind wind;

    @JsonProperty("clouds")
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Clouds clouds;

    @JsonProperty("dt")
    @Column(name = "dt")
    private Integer dt;

    @JsonProperty("sys")
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Sys sys;

    @JsonProperty("timezone")
    @Column(name = "timezone")
    private Integer timezone;

    @JsonProperty("id")
    @Column(name = "city_id")
    private Integer cityId;

    @JsonProperty("name")
    @Column(name = "city_name")
    private String cityName;

    @JsonProperty("cod")
    @Column(name = "cod")
    private Integer cod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("coord")
    public Coord getCoord() {
        return coord;
    }

    @JsonProperty("coord")
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @JsonProperty("weather")
    public List<WeatherDetail> getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(List<WeatherDetail> weather) {
        this.weather = weather;
    }

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    @JsonProperty("visibility")
    public Integer getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @JsonProperty("clouds")
    public Clouds getClouds() {
        return clouds;
    }

    @JsonProperty("clouds")
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    @JsonProperty("sys")
    public Sys getSys() {
        return sys;
    }

    @JsonProperty("sys")
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @JsonProperty("timezone")
    public Integer getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("id")
    public Integer getCityId() {
        return cityId;
    }

    @JsonProperty("id")
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @JsonProperty("name")
    public String getCityName() {
        return cityName;
    }

    @JsonProperty("name")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @JsonProperty("cod")
    public Integer getCod() {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(Integer cod) {
        this.cod = cod;
    }
}
