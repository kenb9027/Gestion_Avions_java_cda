package fr.kenb9027.service;

import fr.kenb9027.buisiness.Airport;
import fr.kenb9027.buisiness.Company;
import fr.kenb9027.buisiness.Fly;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface FlyService {

    Fly addFly(
            float price,
            Company company,
            LocalDateTime hourDeparture,
            LocalDateTime hourArrival,
            Airport airportDeparture,
            Airport airportArrival
    );

    Fly getFly(long number);

    ArrayList<Fly> getFlies();
}
