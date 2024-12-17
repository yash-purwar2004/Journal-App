package com.project.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.journalApp.apiResponse.WeatherResponse;
import com.project.journalApp.cache.AppCache;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;
    @Autowired
    private RestTemplate restTemplate; // It is a class in a spring framework, which process HTTPS request and give response

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
