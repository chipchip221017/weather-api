package com.oddle.app.weather.dao;

import com.oddle.app.weather.exception.ResourceNotFoundException;
import com.oddle.app.weather.model.WeatherReport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class WeatherDaoImpl implements WeatherDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(final WeatherReport weatherReport) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(weatherReport);
    }

    @Override
    public WeatherReport get(final int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(WeatherReport.class, id);
    }

    @Override
    public List<WeatherReport> getWeatherHistory(final int startTime, final int endTime) {
        String hql = "FROM WeatherReport w WHERE w.dt >= :startTime and w.dt <= :endTime";
        Session currentSession = entityManager.unwrap(Session.class);
        Query<WeatherReport> query = currentSession.createQuery(hql, WeatherReport.class);
        query.setParameter("startTime",startTime);
        query.setParameter("endTime",endTime);
        return query.getResultList();
    }

    @Override
    public void delete(final int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        WeatherReport weatherReport = currentSession.get(WeatherReport.class, id);
        if (weatherReport == null) {
            throw new ResourceNotFoundException("Weather data not found");
        }
        currentSession.delete(weatherReport);
    }

    @Override
    public void update(final int id, final WeatherReport weatherReport) {
        Session currentSession = entityManager.unwrap(Session.class);
        WeatherReport existingWeather = currentSession.byId(WeatherReport.class).load(id);
        if (existingWeather == null) {
            currentSession.save(weatherReport);
        } else {
            update(weatherReport, existingWeather);
            currentSession.flush();
        }
    }

    private void update(final WeatherReport weatherReport, final WeatherReport existingWeather) {
        existingWeather.setCityName(weatherReport.getCityName());
        existingWeather.setCityId(weatherReport.getCityId());
        existingWeather.setCod(weatherReport.getCod());
        existingWeather.setWeather(weatherReport.getWeather());
        existingWeather.setBase(weatherReport.getBase());
        existingWeather.setClouds(weatherReport.getClouds());
        existingWeather.setCoord(weatherReport.getCoord());
        existingWeather.setDt(weatherReport.getDt());
        existingWeather.setMain(weatherReport.getMain());
        existingWeather.setSys(weatherReport.getSys());
        existingWeather.setTimezone(weatherReport.getTimezone());
        existingWeather.setVisibility(weatherReport.getVisibility());
        existingWeather.setWind(weatherReport.getWind());
    }
}
