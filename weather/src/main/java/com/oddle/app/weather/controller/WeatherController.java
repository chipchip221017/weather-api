package com.oddle.app.weather.controller;

import com.oddle.app.weather.model.WeatherReport;
import com.oddle.app.weather.service.WeatherService;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Value("${weather.api.url}")
    String weatherApiUrl;

    @Value("${weather.api.key}")
    String weatherApiKey;

    @Autowired
    WeatherService weatherService;

    RestTemplate restTemplate;

    public WeatherController(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Thank you for giving me the opportunity to take this challenge.");
    }

    @GetMapping(value = "/{city}")
    public WeatherReport getWeatherByCity(@PathVariable(value = "city") String city) {
        String url = weatherApiUrl;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("q", city)
                .queryParam("apiKey", weatherApiKey);
        ResponseEntity<WeatherReport> response = restTemplate.getForEntity(builder.build().toUri(), WeatherReport.class);
        return response.getBody();
    }

    @PostMapping
    public WeatherReport saveWeather(@RequestBody WeatherReport weatherReport) {
        weatherService.save(weatherReport);
        return weatherReport;
    }

    @GetMapping("/history/{id}")
    public WeatherReport getWeatherById(@PathVariable(value = "id") int id) {
       return weatherService.get(id);
    }

    @GetMapping("/history/period/{startTime}/{endTime}")
    public List<WeatherReport> getWeatherHistoryPeriod(@PathVariable(value = "startTime") int startTime,
                                                       @PathVariable(value = "endTime") int endTime) {
        return weatherService.getWeatherHistory(startTime, endTime);
    }

    @PutMapping("/{id}")
    public WeatherReport updateWeather(@PathVariable(value = "id") int id, @RequestBody WeatherReport weatherReport) {
        weatherReport.setId(id);
        weatherService.save(weatherReport);
        return weatherReport;
    }

    @DeleteMapping("/history/{id}")
    public ResponseEntity<?> deleteWeather(@PathVariable(value = "id") int id) {
        weatherService.delete(id);
        return ResponseEntity.ok().build();
    }
}