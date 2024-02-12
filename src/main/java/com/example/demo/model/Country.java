package com.example.demo.model;

import java.util.List;

public class Country {

    public Country() {
    }

    public Country(String name, List<City> cities) {
        this.name = name;
        this.cities = cities;
    }

    public String name;

    public List<City> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
