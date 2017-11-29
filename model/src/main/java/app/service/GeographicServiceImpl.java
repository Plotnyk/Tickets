package app.service;

import app.infra.util.CommonUtil;
import app.model.entity.geography.City;

import java.util.ArrayList;
import java.util.List;

/**
 *Default implementation of the {@link GeographicService}
 * @author Plotnyk
 * */
public class GeographicServiceImpl implements GeographicService {
    /**
     * Internal list of cities
     * */
    private final List<City> cities;

    public GeographicServiceImpl() {
        this.cities = new ArrayList<City>();
    }

    @Override
    public List<City> findCities() {
        return CommonUtil.getSafeList(cities);
    }

    @Override
    public void saveCity(City city) {
        if (!cities.contains(city)) {
            cities.add(city);
        }
    }
}
