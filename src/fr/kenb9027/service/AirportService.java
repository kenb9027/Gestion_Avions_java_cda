package fr.kenb9027.service;

import fr.kenb9027.business.Airport;

import java.util.ArrayList;

public interface AirportService {

    Airport getAirport(String id);
    Airport addAirport(String name);

    ArrayList<Airport> getAirports();
}
