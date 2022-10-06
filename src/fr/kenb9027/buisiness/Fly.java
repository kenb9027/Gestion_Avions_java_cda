package fr.kenb9027.buisiness;

import java.time.LocalDateTime;

public class Fly {

    private long counter = 0L;
    private long number;
    private float price ;
    private Company company;

    private LocalDateTime hourDeparture;
    private LocalDateTime hourArrival;
    private Airport airportDeparture;
    private Airport airportArrival;

    public Fly(long counter) {
        super();
        this.number = counter;
        ++counter;
    }

    public Fly(
            float price,
            Company company,
            LocalDateTime hourDeparture,
            LocalDateTime hourArrival,
            Airport airportDeparture,
            Airport airportArrival) {
        //this();
        this.price = price;
        this.company = company;
        this.hourDeparture = hourDeparture;
        this.hourArrival = hourArrival;
        this.airportDeparture = airportDeparture;
        this.airportArrival = airportArrival;
    }

    @Override
    public String toString() {
        return "Fly{" +
                "number=" + number +
                ", price=" + price +
                ", company=" + company +
                ", hour Departure=" + hourDeparture +
                ", hour Arrival=" + hourArrival +
                ", airport Departure=" + airportDeparture +
                ", airport Arrival=" + airportArrival +
                '}';
    }

    public long getNumber() {
        return number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDateTime getHourDeparture() {
        return hourDeparture;
    }

    public void setHourDeparture(LocalDateTime hourDeparture) {
        this.hourDeparture = hourDeparture;
    }

    public LocalDateTime getHourArrival() {
        return hourArrival;
    }

    public void setHourArrival(LocalDateTime hourArrival) {
        this.hourArrival = hourArrival;
    }

    public Airport getAirportDeparture() {
        return airportDeparture;
    }

    public void setAirportDeparture(Airport airportDeparture) {
        this.airportDeparture = airportDeparture;
    }

    public Airport getAirportArrival() {
        return airportArrival;
    }

    public void setAirportArrival(Airport airportArrival) {
        this.airportArrival = airportArrival;
    }
}
