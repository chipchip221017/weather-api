package com.oddle.app.weather.service;

import com.oddle.app.weather.dao.WeatherDao;
import com.oddle.app.weather.model.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherDao weatherDao;

    @Transactional
    @Override
    public void save(final WeatherReport weatherReport) {
        weatherDao.save(weatherReport);
    }

    @Transactional
    @Override
    public WeatherReport get(final int id) {
        return weatherDao.get(id);
    }

    @Transactional
    @Override
    public List<WeatherReport> getWeatherHistory(int startTime, int endTime) {
        return weatherDao.getWeatherHistory(startTime, endTime);
    }

    @Transactional
    @Override
    public void delete(int id) {
        weatherDao.delete(id);
    }
}
