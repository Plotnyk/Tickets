package app.persistence.repository;

import app.model.entity.geography.City;

import java.util.List;

/**
 * Defines CRUD methods City objects in the persistent storage
 *
 * @author Plotnyk
 */
public interface CityRepository {
    /**
     * Saves(creates or modifies) specified city instance
     * @param city
     */
    void save(City city);

    /**
     * Returns city with specified identifier. If no city exists with such identifier
     * then null is returned
     * @param cityId
     * @return
     */
    City findById(int cityId);

    /**
     * Delete city with specified identifier
     * @param cityId
     */
    void delete(int cityId);

    /**
     * Returns all the cities
     * @return
     */
    List<City> findAll();
}
