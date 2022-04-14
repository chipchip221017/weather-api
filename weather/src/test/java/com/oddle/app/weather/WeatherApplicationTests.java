package com.oddle.app.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddle.app.weather.model.WeatherReport;
import com.oddle.app.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherApplicationTests {

	@MockBean
	private WeatherService weatherService;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void canGetWeatherByCity() throws Exception {
		mvc.perform(get("/api/weather/Singapore")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.name", is("Singapore")));
	}

	@Test
	public void canGetWeatherById() throws Exception {
		WeatherReport weatherReport = new WeatherReport();
		weatherReport.setId(1);
		weatherReport.setCityName("Singapore");
		weatherReport.setCod(100);
		when(weatherService.get(1)).thenReturn(weatherReport);
		mvc.perform(get("/api/weather/history/1")
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.name").value("Singapore"))
					.andExpect(jsonPath("$.cod").value("100"));
	}

	@Test
	public void canSaveWeather() throws Exception {
		WeatherReport weather = new WeatherReport();
		weather.setCityName("Singapore");
		mvc.perform(post("/api/weather")
					.content(asJsonString(weather))
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.name").value("Singapore"));
	}

	@Test
	public void canGetWeatherHistoryPeriod() throws Exception {
		List<WeatherReport> weatherReportList = new ArrayList();
		WeatherReport weather1 = new WeatherReport();
		weather1.setCityName("Singapore");
		WeatherReport weather2 = new WeatherReport();
		weather2.setCityName("London");
		weatherReportList.add(weather1);
		weatherReportList.add(weather2);
		when(weatherService.getWeatherHistory(1,2)).thenReturn(weatherReportList);
		mvc.perform(get("/api/weather/history/period/1/2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[0].name").value("Singapore"))
				.andExpect(jsonPath("$[1].name").value("London"));
	}

	@Test
	public void canUpdateWeather() throws Exception {
		WeatherReport weather = new WeatherReport();
		weather.setCityName("Singapore");
		mvc.perform(put("/api/weather/1")
						.content(asJsonString(weather))
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.name").value("Singapore"));
	}

	@Test
	public void canDeleteWeather() throws Exception {
		mvc.perform(delete("/api/weather/1"))
				.andExpect((status().isOk()));
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
