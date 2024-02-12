package com.example.demo;

import com.example.demo.model.Country;
import com.example.demo.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherControllerTest {

    @MockBean
    private WeatherController weatherController;

    private List<Country> countries;

    @BeforeEach
    public void setUp() {
        countries = Arrays.asList(
                new Country("Country1", Arrays.asList(new City("City1"), new City("City2"))),
                new Country("Country2", Arrays.asList(new City("City3"), new City("City4")))
        );

        when(weatherController.index()).thenReturn(countries);
        when(weatherController.getCities("Country1")).thenReturn(Arrays.asList("City1", "City2"));
    }

    @Test
    public void testIndex() {
        List<Country> result = weatherController.index();
        assertEquals(countries, result);
    }

    @Test
    public void testGetCities() {
        List<String> result = weatherController.getCities("Country1");
        assertEquals(Arrays.asList("City1", "City2"), result);
    }
}