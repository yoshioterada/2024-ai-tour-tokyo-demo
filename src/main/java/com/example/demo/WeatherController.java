package com.example.demo;

import com.example.demo.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WeatherController {

    List<Country> countries;

    WeatherController(ResourceLoader resourceLoader) {
        Resource resource = resourceLoader.getResource("classpath:weather.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            countries = objectMapper.readValue(resource.getInputStream(), objectMapper.getTypeFactory().constructCollectionType(List.class, Country.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public List<Country> index() {
        return countries;
    }

    //Create a new route that exposes the cities of a country
    @GetMapping("/countries/{country}")
    public List<String> getCities(@PathVariable String country) {
        final String countryCapitalized = country.substring(0, 1).toUpperCase() + country.substring(1);
        return countries.stream()
                .filter(c -> c.name.equals(countryCapitalized))
                .flatMap(c -> c.cities.stream())
                .map(City::getName)
                .toList();
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

    @GetMapping("/countries/{country}/{city}")  
    public Weather getWeather(@PathVariable String country, @PathVariable String city) {
        final String countryCapitalized = country.substring(0, 1).toUpperCase() + country.substring(1);
        final String cityCapitalized = city.substring(0, 1).toUpperCase() + city.substring(1);

        return countries.stream()
                .filter(c -> c.name.equals(countryCapitalized))
                .flatMap(c -> c.cities.stream())
                .filter(ci -> ci.name.equals(cityCapitalized))
                .map(ci -> ci.weather.get("January"))
                .findFirst()
                .orElse(null);
    }

    // 閏年の判定
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    // 月の日数を返す
    public static int getDaysOfMonth(int year, int month) {
        int[] days = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return days[month - 1];
    }

    // 月の日数を返す
    public static int getDaysOfMonth(String month) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return days[Integer.parseInt(month) - 1];
    }

    // 月の日数を返す
    public static int getDaysOfMonth(String year, String month) {
        return getDaysOfMonth(Integer.parseInt(year), Integer.parseInt(month));
    }

    // 月の日数を返す
    public static int getDaysOfMonth(int year, String month) {
        return getDaysOfMonth(year, Integer.parseInt(month));
    }

    // 月の日数を返す
    public static int getDaysOfMonth(String year, int month) {
        return getDaysOfMonth(Integer.parseInt(year), month);
    }

    // 月の日数を返す
    public static int getDaysOfMonth(String year, int month) {
        return getDaysOfMonth(Integer.parseInt(year), month);
    }

    // 月の日数を返す
    public static int getDaysOfMonth(int year, int month) {
        int[] days = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return days[month - 1];
    }

    // 月の日数を返す
    public static int getDaysOfMonth(String month) {
        int[] days = {31, 28, 31, 30, 31, 30, 31,
}
