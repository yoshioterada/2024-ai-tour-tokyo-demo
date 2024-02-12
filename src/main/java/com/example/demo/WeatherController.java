package com.example.demo;

import com.example.demo.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class WeatherController {

    List<Country> countries;

    WeatherController() {
        Resource resource = new ClassPathResource("weather.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            countries = objectMapper.readValue(resource.getFile(), objectMapper.getTypeFactory().constructCollectionType(List.class, Country.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public List<Country> index() {
        return countries;
    }

    @GetMapping("/countries/{country}/{city}/{month}")
    public Weather getWeather(@PathVariable String country, @PathVariable String city, @PathVariable String month) {
        final String countryCapitalized = country.substring(0, 1).toUpperCase() + country.substring(1);
        final String cityCapitalized = city.substring(0, 1).toUpperCase() + city.substring(1);
        final String monthCapitalized = month.substring(0, 1).toUpperCase() + month.substring(1);

        return countries.stream()
                .filter(c -> c.name.equals(countryCapitalized))
                .flatMap(c -> c.cities.stream())
                .filter(ci -> ci.name.equals(cityCapitalized))
                .map(ci -> ci.weather.get(monthCapitalized))
                .findFirst()
                .orElse(null);
    }
}
