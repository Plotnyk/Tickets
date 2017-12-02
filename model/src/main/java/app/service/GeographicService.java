package app.service;

import app.model.entity.geography.City;

import java.util.List;

/**
 *Entry point to perform operations
 * over geographic entities_
 * @author Plotnyk
 *
 *  */
public interface GeographicService {

    /**
     * Returns list of existing cities
     * @return
     * */
    List<City> findCities();

    /**
     * Save specified city instance
     * @param city
     * */
    void saveCity(City city);
}
