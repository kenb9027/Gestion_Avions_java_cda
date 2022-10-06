package fr.kenb9027.serviceImpl;

import fr.kenb9027.buisiness.Airport;
import fr.kenb9027.service.AirportService;

import java.util.ArrayList;

public class AirportServiceImpl implements AirportService {

    private static ArrayList<Airport> airports = new ArrayList<Airport>() ;


    @Override
    public Airport getAirport(String id) {
        for (Airport airport :
                airports) {
            if (airport.getId().equals(id)) {
                return airport;
            }
        }

        return null;
    }


    @Override
    public Airport addAirport(String name) {

        Airport newAirport = new Airport(name);
        airports.add(newAirport);

        return newAirport ;
    }

    @Override
    public ArrayList<Airport> getAirports() {
        return airports;
    }

}
