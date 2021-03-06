package app.model.entity.geography;

import app.infra.util.CommonUtil;
import app.model.entity.base.AbstractEntity;
import app.model.entity.transport.TransportType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Any locality that district where city is placed
 * @author Plotnyk
 * */
@Table(name = "CITY")
@Entity
public class City extends AbstractEntity {
    public static final String FIELD_NAME = "name";

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
        Objects.requireNonNull(transportType, "transportType parameter is not initialized");
        if(stations == null) {
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
    public void removeStation(Station station) {
        Objects.requireNonNull(station, "station parameter is not initialized");
        if (stations == null) {
            return;
        }
        stations.remove(station);
    }

    @Column(name = "NAME", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DISTRICT", nullable = false, length = 32)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(name = "REGION", nullable = false, length = 32)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city", orphanRemoval = true)
    public Set<Station> getStations() {
        return CommonUtil.getSafeSet(stations);
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }
}
