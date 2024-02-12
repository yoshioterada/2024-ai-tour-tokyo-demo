package com.example.demo.model;

import java.util.Map;

public class City {

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public String name;
    public Map<String, Weather> weather;

    public String getName() {
        return name;
    }

    public Map<String, Weather> getWeather() {
        return weather;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeather(Map<String, Weather> weather) {
        this.weather = weather;
    }
}
