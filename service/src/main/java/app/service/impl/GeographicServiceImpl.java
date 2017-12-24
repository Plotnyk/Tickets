package app.service.impl;


import app.infra.util.CommonUtil;
import app.model.entity.geography.City;
import app.model.entity.geography.Station;
import app.model.search.criteria.StationCriteria;
import app.model.search.criteria.range.RangeCriteria;
import app.persistence.repository.CityRepository;
import app.persistence.repository.StationRepository;
import app.persistence.repository.inmemory.InMemoryCityRepository;
import app.service.GeographicService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *Default implementation of the {@link GeographicService}
 * @author Plotnyk
 * */
public class GeographicServiceImpl implements GeographicService {
    private final CityRepository cityRepository;

    private final StationRepository stationRepository;

    @Inject
    public GeographicServiceImpl(CityRepository cityRepository,
                                 StationRepository stationRepository) {
        this.cityRepository = cityRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public List<City> findCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findCityById(int id) {
        return Optional.ofNullable(cityRepository.findById(id));
    }

    @Override
    public List<Station> searchStations(final StationCriteria criteria, final RangeCriteria rangeCriteria) {
        return stationRepository.findAllByCriteria(criteria);
    }

    @Override
    public void saveCity(City city) {
        cityRepository.save(city);
    }
}

