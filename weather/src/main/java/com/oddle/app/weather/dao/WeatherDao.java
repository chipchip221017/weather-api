package com.oddle.app.weather.dao;

import com.oddle.app.weather.model.WeatherReport;

import java.util.List;

public interface WeatherDao {
    void save(WeatherReport weatherReport);
    WeatherReport get(int id);
    List<WeatherReport> getWeatherHistory(int startTime, int endTime);
    void delete(int id);
    void update(int id, WeatherReport weatherReport);
}