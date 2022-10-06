import fr.kenb9027.buisiness.Airport;
import fr.kenb9027.buisiness.Company;
import fr.kenb9027.buisiness.Fly;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Main {

    public static ArrayList<Company> fixt_companies;
    public static ArrayList<Airport> fixt_airports;


    public static void main(String[] args) {

    createEnvironment();


    }

    private static void createEnvironment(){
        fixt_companies = createCompanies();
        System.out.println(fixt_companies);
        fixt_airports = createAirports();
        System.out.println(fixt_airports);

        ArrayList<Fly> flies = new ArrayList<Fly>();
        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now().plusMinutes(30).plusHours(2);
        LocalDateTime date3 = LocalDateTime.now().plusHours(12);
        LocalDateTime date4 = LocalDateTime.now().plusHours(19).plusMinutes(40);
        flies.add(new Fly(
                1000,
                fixt_companies.get(0),
                date1,
                date2,
                fixt_airports.get(0),
                fixt_airports.get(1)
        ));
        flies.add(new Fly(
                1200,
                fixt_companies.get(2),
                date3,
                date4,
                fixt_airports.get(2),
                fixt_airports.get(0)
        ));
        System.out.println(flies);
    }
    public static ArrayList<Airport> createAirports() {

        ArrayList<Airport> airports = new ArrayList<>();

        airports.add(new Airport("Roissy Charles de Gaulle")) ;
        airports.add(new Airport("Schipol Amsterdam")) ;
        airports.add(new Airport("Heathrow London")) ;

        return airports;
    }
    public static ArrayList<Company> createCompanies(){

        ArrayList<Company> companies = new ArrayList<>();

        companies.add(new Company("Iberia")) ;
        companies.add(new Company("KLM")) ;
        companies.add(new Company("British Airways")) ;

        return companies;

    }
}

