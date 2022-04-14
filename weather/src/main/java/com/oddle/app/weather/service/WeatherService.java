package com.oddle.app.weather.service;

import com.oddle.app.weather.model.WeatherReport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WeatherService {
    void save(final WeatherReport weatherReport);
    WeatherReport get(int id);
    List<WeatherReport> getWeatherHistory(int startTime, int endTime);
    void delete(int id);
}
