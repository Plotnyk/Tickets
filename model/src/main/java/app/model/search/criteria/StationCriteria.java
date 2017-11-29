package app.model.search.criteria;

import app.model.entity.transport.TransportType;

/**
 * Filtering criteria for search station operation
 * @author Plotnyk
 * */
public class StationCriteria {
    /**
     * City's name
     * */
    private String name;

    private TransportType transportType;

    /**
     * Station's address: street,zipCode, building number
     * */
    private String address;



}
