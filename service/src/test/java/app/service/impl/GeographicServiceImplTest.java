package app.service.impl;


import app.model.entity.geography.City;
import app.model.entity.geography.Station;
import app.model.entity.transport.TransportType;
import app.model.search.criteria.StationCriteria;
import app.model.search.criteria.range.RangeCriteria;
import app.persistence.hibernate.SessionFactoryBuilder;
import app.persistence.repository.CityRepository;
import app.persistence.repository.StationRepository;
import app.persistence.repository.hibernate.HibernateCityRepository;
import app.persistence.repository.hibernate.HibernateStationRepository;
import app.persistence.repository.inmemory.InMemoryCityRepository;
import app.service.GeographicService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Contain unit-tests for {@link GeographicServiceImpl}
 * @author jeka
 * */
public class GeographicServiceImplTest {
    private static final int DEFAULT_CITY_ID = 1;

    private GeographicService service;

    @Before
    public void setup() {
        SessionFactoryBuilder builder = new SessionFactoryBuilder();
        CityRepository repository = new HibernateCityRepository(builder);
        StationRepository stationRepository = new HibernateStationRepository(builder);
        service = new GeographicServiceImpl(repository, stationRepository);
    }

    @Test
    public void testNoDataReturnedAtStart() {
        List<City> cities = service.findCities();
        assertTrue(!cities.isEmpty());
    }

    @Test
    public void testSaveNewCitySuccess() {
        City city = createCity();
        service.saveCity(city);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), 5);
        assertEquals(cities.get(0).getName(), "Odessa");
    }

    @Test
    public void testFindCityByIdSuccess() {
        City city = createCity();
        service.saveCity(city);

        Optional<City> foundCity = service.findCityById(DEFAULT_CITY_ID);
        assertTrue(foundCity.isPresent());
        assertEquals(foundCity.get().getId(), DEFAULT_CITY_ID);
    }

    @Test
    public void testFindCityByIdNotFound() {
        Optional<City> foundCity = service.findCityById(DEFAULT_CITY_ID);
        assertFalse(!foundCity.isPresent());
    }

    @Test
    public void testSearchStationsByNameSuccess() {
        City city = createCity();
        city.addStation(TransportType.AUTO);
        city.addStation(TransportType.RAILWAY);
        service.saveCity(city);

        List<Station> stations = service.searchStations(StationCriteria.byName("Odessa"), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertEquals(stations.size(), 2);
        assertEquals(stations.get(0).getCity(), city);
    }

    @Test
    public void testSearchStationsByNameNotFound() {
        List<Station> stations = service.searchStations(StationCriteria.byName("London"), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertTrue(stations.isEmpty());
    }

    @Test
    public void testSearchStationsByTransportTypeSuccess() {
        City city = createCity();
        city.addStation(TransportType.AUTO);
        service.saveCity(city);
        City city2 = new City("Kiev");
        city2.setDistrict("Kiev");
        city2.setRegion("Kiev");
        city2.addStation(TransportType.AUTO);
        service.saveCity(city2);

        List<Station> stations = service.searchStations(new StationCriteria(TransportType.AUTO), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertEquals(stations.size(), 3);
    }

    @Test
    public void testSearchStationsByTransportTypeNotFound() {
        City city = createCity();
        city.addStation(TransportType.AUTO);
        service.saveCity(city);
        City city2 = new City("Kiev");
        city2.setId(2);
        city2.addStation(TransportType.RAILWAY);
        service.saveCity(city2);

        List<Station> stations = service.searchStations(new StationCriteria(TransportType.AVIA), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertTrue(stations.isEmpty());
    }


    private City createCity() {
        City city = new City("Odessa");
        city.setDistrict("Odessa");
        city.setRegion("Odessa");

        return city;
    }
}