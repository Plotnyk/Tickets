package app.persistence.repository;

import app.model.entity.geography.Station;
import app.model.search.criteria.StationCriteria;

import java.util.List;

/**
 * Defines CRUD methods to access Station objects in the persistent storage
 * @author Plotnyk
 * */
public interface StationRepository {

    /**
     * Returns all the stations that match specified criteria
     * @param stationCriteria
     * @return
     */
    List<Station> findAllByCriteria(StationCriteria stationCriteria);
}
