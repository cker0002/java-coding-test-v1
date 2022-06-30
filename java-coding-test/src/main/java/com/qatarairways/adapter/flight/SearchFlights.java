package com.qatarairways.adapter.flight;

import com.qatarairways.adapter.flight.util.FlightUtility;

import java.util.List;
import java.util.stream.Collectors;

public class SearchFlights {
    public static void main(String[] args) {

        FlightAvailabilityRequest request = new FlightAvailabilityRequest
                ("BLR", "DELHI", FlightUtility.getDateTime
                        (2022, 07, 02, 10, 30, 5),
                        10);
        List<FlightSummary> flightSummaryList = FlightUtility.searchFlights(request);

        List<FlightSummary> isCancellationPossibleList = flightSummaryList.stream().
                filter(s -> s.isCancellationPossible() == Boolean.TRUE).collect(Collectors.toList());
        System.out.println(isCancellationPossibleList);

        List<FlightSummary> isCancellationNotPossibleList = flightSummaryList.stream().
                filter(s -> s.isCancellationPossible() == Boolean.FALSE).collect(Collectors.toList());
        System.out.println(isCancellationNotPossibleList);

    }
}