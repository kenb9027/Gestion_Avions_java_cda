package fr.kenb9027.business;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class Fly {

    private static long counter = 0L;
    private long number;
    private float price ;
    private Company company;

    private LocalDateTime hourDeparture;
    private LocalDateTime hourArrival;
    private Airport airportDeparture;
    private Airport airportArrival;

    public Fly() {
        super();
        this.number = ++counter;

    }

    public Fly(
            float price,
            Company company,
            LocalDateTime hourDeparture,
            LocalDateTime hourArrival,
            Airport airportDeparture,
            Airport airportArrival) {
        this();
        this.price = price;
        this.company = company;
        this.hourDeparture = hourDeparture;
        this.hourArrival = hourArrival;
        this.airportDeparture = airportDeparture;
        this.airportArrival = airportArrival;

        this.company.getFlies().add(this);
        this.airportArrival.getFlies().add(this);
        this.airportDeparture.getFlies().add(this);
    }


    @Override
    public String toString() {
        return "Fly{" +
                "number=" + number +
                ", price=" + price +
                ", company=" + company +
                ", hour Departure=" + hourDeparture +
                ", hour Arrival=" + hourArrival +
                ", duration=" + getDuration() +
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

    public String getDuration() {
        long minutes = ChronoUnit.MINUTES.between( this.getHourDeparture(), this.getHourArrival());
        long hours = ChronoUnit.HOURS.between(this.getHourDeparture(),this.getHourArrival());

        long modulo = minutes % 60 ;
        if (modulo == 0){
            return hours + "h";
        }
        else {
            if (hours == 0){
                return modulo + "min";
            }
            if (modulo < 10){
                return  hours + "h0" + modulo ;
            }

            return  hours + "h" + modulo ;
        }

    }

    /*
     * Comparator pour le tri des vols par prix
     */
    public static Comparator<Fly> ComparatorPrice = new Comparator<Fly>() {

        @Override
        public int compare(Fly e1, Fly e2) {
            return (int) (e1.getPrice() - e2.getPrice());
        }
    };
    /*
     * Comparator pour le tri des vols par durée
     */
    public static Comparator<Fly> ComparatorDuration = new Comparator<Fly>() {

        @Override
        public int compare(Fly e1, Fly e2) {

            long minutesE1 = ChronoUnit.MINUTES.between( e1.getHourDeparture(), e1.getHourArrival());
            long minutesE2 = ChronoUnit.MINUTES.between( e2.getHourDeparture(), e2.getHourArrival());

            return (int) (minutesE1 - minutesE2);
        }
    };

}
