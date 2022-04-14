package com.oddle.app.weather.dao;

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
        currentSession.saveOrUpdate(weatherReport);
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
        currentSession.delete(weatherReport);
    }
}
