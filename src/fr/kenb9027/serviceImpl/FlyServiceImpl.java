package fr.kenb9027.serviceImpl;

import fr.kenb9027.buisiness.Airport;
import fr.kenb9027.buisiness.Company;
import fr.kenb9027.buisiness.Fly;
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
    public Fly addFly(float price, Company company, LocalDateTime hourDeparture, LocalDateTime hourArrival, Airport airportDeparture, Airport airportArrival) {

        Fly newFly = new Fly();

        newFly.setPrice(price);
        newFly.setCompany(companyService.getCompany(company.getId()));
        newFly.setAirportDeparture(airportService.getAirport(airportDeparture.getId()));
        newFly.setAirportArrival(airportService.getAirport(airportArrival.getId()));
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
