package app.service.impl;


import app.infra.util.CommonUtil;
import app.model.entity.geography.City;
import app.model.entity.geography.Station;
import app.model.search.criteria.StationCriteria;
import app.model.search.criteria.range.RangeCriteria;
import app.service.GeographicService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *Default implementation of the {@link GeographicService}
 * @author Plotnyk
 * */
public class GeographicServiceImpl implements GeographicService {
    /**
     * Internal list of cities
     * */
    private final List<City> cities;

    /**
     * Auto-increment counter for entity id generation
     * */
    private int counter = 0;

    private int stationCounter = 0;

    public GeographicServiceImpl() {
        this.cities = new ArrayList<City>();
    }

    @Override
    public List<City> findCities() {
        return CommonUtil.getSafeList(cities);
    }

    @Override
    public Optional<City> findCityById(int id) {
        return cities.stream().filter((city) -> city.getId() == id).findFirst();
    }

    @Override
    public List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria) {
        Set<Station> stations = new HashSet<>();
        //получаем список всех станций в городах
        for (City city : cities) {
            stations.addAll(city.getStations());
        }

        return stations.stream().filter((station) -> station.match(criteria)).collect(Collectors.toList());
    }

    @Override
    public void saveCity(City city) {
        if (!cities.contains(city)) {
            city.setId(++counter);
            cities.add(city);
        }
        city.getStations().forEach((station) ->{
            if (station.getId() == 0) {
                station.setId(++stationCounter);
            }
        });
    }
}

