package app.service;

import app.model.entity.geography.City;
import app.model.entity.geography.Station;
import app.model.search.criteria.StationCriteria;
import app.model.search.criteria.range.RangeCriteria;

import java.util.List;
import java.util.Optional;

/**
 *Entry point to perform operations
 * over geographic entities
 * @author Plotnyk
 *
 *  */
public interface GeographicService {

    /**
     * Returns list of existing cities
     * @return
     */
    List<City> findCities();

    /**
     * Return city with specified identifier. If no city is found then empty
     * optional is returned
     *
     * @param id
     * @return
     * */
    Optional<City> findCityById(int id);

    /**
     * Returns all the stations that match specified criteria
     * @param criteria
     * @param rangeCriteria
     * @return
     * */
    List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria);

    /**
     * Saves specified city instance
     * @param city
     */
    void saveCity(City city);
}

