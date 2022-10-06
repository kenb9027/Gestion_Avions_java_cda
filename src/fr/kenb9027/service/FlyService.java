package fr.kenb9027.service;

import fr.kenb9027.business.Fly;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface FlyService {

    Fly addFly(
            float price,
            String companyId,
            LocalDateTime hourDeparture,
            LocalDateTime hourArrival,
            String airportDepartureId,
            String airportArrivalId
    );

    Fly getFly(long number);

    ArrayList<Fly> getFlies();
}
