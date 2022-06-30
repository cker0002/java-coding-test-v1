package com.qatarairways.adapter.flight;

import com.qatarairways.adapter.flight.util.FlightUtility;

import java.util.Collection;
import java.util.List;

/**
 * Provides implementation for FlightAvailabilityService interface to search flights based on the request.
 */
public class FlightAvailabilityServiceImpl implements FlightAvailabilityService{
    @Override
    public Collection<FlightSummary> getAvailableFlights(FlightAvailabilityRequest request) {
        List<FlightSummary> flightSummaryList =  FlightUtility.searchFlights(request);



        return flightSummaryList;
    }
}
