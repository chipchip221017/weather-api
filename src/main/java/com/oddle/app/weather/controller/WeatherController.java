package com.oddle.app.weather.controller;

import com.oddle.app.weather.exception.ResourceNotFoundException;
import com.oddle.app.weather.model.WeatherReport;
import com.oddle.app.weather.service.WeatherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to the show!!!");
    }

    @GetMapping(value = "/{city}")
    @ApiOperation(value = "Search for today weather of a specific city")
    public Mono<WeatherReport> getWeatherByCity(
            @ApiParam(value = "city name", required = true) @PathVariable(value = "city") String city) {
        return weatherService.getByCity(city);
    }

    @PostMapping
    @ApiOperation(value = "Save weather data")
    public WeatherReport saveWeather(
            @ApiParam(value = "The weather data to save", required = true) @RequestBody WeatherReport weatherReport) {
        weatherService.save(weatherReport);
        return weatherReport;
    }

    @GetMapping("/history/{id}")
    @ApiOperation(value = "Get weather by id")
    public ResponseEntity<WeatherReport> getWeatherById(
            @ApiParam(value = "The weather id", required = true) @PathVariable(value = "id") int id) {
        final WeatherReport weather = weatherService.get(id);
        if (weather == null) {
            throw new ResourceNotFoundException("Weather data not found");
        }
        return ResponseEntity.ok().body(weather);
    }

    @ApiOperation(value = "Get historical weather data from past periods")
    @GetMapping("/history/period/{startTime}/{endTime}")
    public List<WeatherReport> getWeatherHistoryPeriod(
            @ApiParam(value = "UNIX start time of period", required = true) @PathVariable(value = "startTime") int startTime,
            @ApiParam(value = "UNIX end time of period", required = true) @PathVariable(value = "endTime") int endTime) {
        return weatherService.getWeatherHistory(startTime, endTime);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update historical weather data")
    public WeatherReport updateWeather(
            @ApiParam(value = "The weather report id to update", required = true) @PathVariable(value = "id") int id,
            @ApiParam(value = "The update weather report ", required = true) @RequestBody WeatherReport weatherReport) {
        weatherService.update(id, weatherReport);
        return weatherReport;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete historical weather data")
    public ResponseEntity<?> deleteWeather(
            @ApiParam(value = "The weather report id to delete", required = true) @PathVariable(value = "id") int id) {
        weatherService.delete(id);
        return ResponseEntity.ok().build();
    }
}