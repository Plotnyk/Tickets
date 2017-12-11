package app.model.entity.geography;

import app.infra.util.CommonUtil;
import app.model.entity.base.AbstractEntity;
import app.model.entity.transport.TransportType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Any locality that district where city is placed
 * @author Plotnyk
 * */
public class City extends AbstractEntity {
    private String name;

    /**
     * Name of the district where city is placed
     * */
    private String district;

    /**
     * Name of the region where district is located.
     * Region is top-level area in the country
     * */
    private String region;

    /**
     * Set of transport stations that is linked to this
     * location
     * */
    private Set<Station> stations;

    public City() {
    }

    public City(final String name) {
        this.name = name;
    }

    /**
     * Adds specified to the city station list
     * @param transportType
     * */
    public Station addStation(final TransportType transportType) {
        Objects.requireNonNull(transportType, "transport type parameter is not initialized");
        if (stations == null) {
            stations = new HashSet<>();
        }
        Station station = new Station(this, transportType);
        stations.add(station);
        return station;
    }

    /**
     * Removes specified station from city station list
     *
     * @param station
     */
    public void removeStatioon(Station station) {
        Objects.requireNonNull(station, "station parameter is not initialized");
        if (stations == null) {
            return;
        }
        stations.remove(station);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Set<Station> getStations() {
        return CommonUtil.getSafeSet(stations);
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }
}
