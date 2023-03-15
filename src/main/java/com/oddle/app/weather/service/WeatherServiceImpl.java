package com.oddle.app.weather.service;

import com.oddle.app.weather.dao.WeatherDao;
import com.oddle.app.weather.model.WeatherReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Transactional
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherDao weatherDao;

    @Value("${weather.api.url}")
    String weatherApiUrl;

    @Value("${weather.api.key}")
    String weatherApiKey;

    private final WebClient webClient;

    public WeatherServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(weatherApiUrl).build();
    }

    @Override
    public Mono<WeatherReport> getByCity(final String city) {
        String url = weatherApiUrl;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("q", city)
                .queryParam("apiKey", weatherApiKey);
        return this.webClient.get().uri(builder.build().toUri()).retrieve().bodyToMono(WeatherReport.class);
    }

    @Override
    public void save(final WeatherReport weatherReport) {
        weatherDao.save(weatherReport);
    }

    @Override
    public WeatherReport get(final int id) {
        return weatherDao.get(id);
    }

    @Override
    public List<WeatherReport> getWeatherHistory(int startTime, int endTime) {
        return weatherDao.getWeatherHistory(startTime, endTime);
    }

    @Override
    public void delete(int id) {
        weatherDao.delete(id);
    }

    @Override
    public void update(final int id, final WeatherReport weatherReport) {
        weatherDao.update(id, weatherReport);
    }
}
