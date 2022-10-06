package fr.kenb9027.serviceImpl;

import fr.kenb9027.business.Fly;
import fr.kenb9027.service.AirportService;
import fr.kenb9027.service.CompanyService;
import fr.kenb9027.service.FlyService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlyServiceImpl implements FlyService {

    private static ArrayList<Fly> flies = new ArrayList<Fly>() ;

    AirportService airportService = new AirportServiceImpl() ;
    CompanyService companyService = new CompanyServiceImpl() ;


    @Override
    public Fly addFly(float price, String companyId, LocalDateTime hourDeparture, LocalDateTime hourArrival, String airportDepartureId, String airportArrivalId) {

        Fly newFly = new Fly();
        newFly.setPrice(price);
        newFly.setCompany(companyService.getCompany(companyId));
        newFly.setAirportDeparture(airportService.getAirport(airportDepartureId));
        newFly.setAirportArrival(airportService.getAirport(airportArrivalId));
        newFly.setHourDeparture(hourDeparture);
        newFly.setHourArrival(hourArrival);

        flies.add(newFly);
        return newFly;
    }

    @Override
    public Fly getFly(long number) {
        for (Fly fly :
                flies) {
            if (fly.getNumber() == number) {
                return fly;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Fly> getFlies() {
        return flies;
    }
}
