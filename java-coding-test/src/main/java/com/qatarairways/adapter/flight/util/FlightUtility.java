package com.qatarairways.adapter.flight.util;

import com.qatarairways.adapter.flight.FlightAvailabilityRequest;
import com.qatarairways.adapter.flight.FlightSummary;
import com.qatarairways.flights.FlightDetails;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class to carry out all required operations to search for flights.
 */
public class FlightUtility {
    public static Date getDateTime(int year,int month,int dayOfMonth,int hourOfDay,int minute,int sec){
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, sec);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return cal.getTime();
    }

    public static List<FlightDetails> loadFlights(){
        List<FlightDetails> flightDetails = new ArrayList<>();

        FlightDetails blrDelhi = FlightDetails.builder().noOfSeats(15).origin("BLR").destination("DELHI")
                .arrivalTime(getDateTime(2022,07,02,10,10,5))
                .departureTime(getDateTime(2022,07,02,10,30,5))
                .airLineCode(Airline.AIRINDIA.name()).build();

        FlightDetails blrPune = FlightDetails.builder().noOfSeats(4).origin("BLR").destination("PUNE")
                .arrivalTime(getDateTime(2022,07,02,11,10,5))
                .departureTime(getDateTime(2022,07,02,11,50,5))
                .airLineCode(Airline.INDIGO.name()).build();

        FlightDetails puneDelhi = FlightDetails.builder().noOfSeats(4).origin("BLR").destination("PUNE")
                .arrivalTime(getDateTime(2022,07,05,10,0,0))
                .departureTime(getDateTime(2022,07,05,10,50,5))
                .airLineCode(Airline.SPICEJET.name()).build();

        FlightDetails hydBlr = FlightDetails.builder().noOfSeats(4).origin("BLR").destination("PUNE")
                .arrivalTime(getDateTime(2022,07,06,8,0,0))
                .departureTime(getDateTime(2022,07,06,8,40,0))
                .airLineCode(Airline.JETAIRWAYS.name()).build();

        flightDetails.add(blrDelhi);
        flightDetails.add(blrPune);
        flightDetails.add(puneDelhi);
        flightDetails.add(hydBlr);

    return flightDetails;
    }

    public static List<FlightSummary> searchFlights(FlightAvailabilityRequest request) {

        List<FlightDetails> flightDetails = loadFlights();
        List<FlightSummary> flightSummaryList = new ArrayList<>();
        List<FlightDetails> list  =  flightDetails.stream().filter(s -> s.getOrigin().equalsIgnoreCase(request.getOrigin())
        && s.getDestination().equalsIgnoreCase(request.getDestination()) && s.getNoOfSeats() >= request.getNumberOfTravellers()
        && (s.getDepartureTime().toString()).equals(request.getDepartureDate().toString())).collect(Collectors.toList());

        if (list.size() > 0){
            list.stream().forEach(s -> {
                flightSummaryList.add(FlightSummary.builder().airlineCode(s.getAirLineCode())
                        .departureTime(s.getDepartureTime()).arrivalTime(s.getArrivalTime()).
                        averagePriceInUsd(25).cancellationPossible(Boolean.FALSE)
                        .build());
            });
        }
        return flightSummaryList;
    }
}
