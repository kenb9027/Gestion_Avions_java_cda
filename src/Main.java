import fr.kenb9027.business.Airport;
import fr.kenb9027.business.Company;
import fr.kenb9027.business.Fly;
import fr.kenb9027.service.AirportService;
import fr.kenb9027.service.CompanyService;
import fr.kenb9027.service.FlyService;
import fr.kenb9027.serviceImpl.AirportServiceImpl;
import fr.kenb9027.serviceImpl.CompanyServiceImpl;
import fr.kenb9027.serviceImpl.FlyServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    private static final AirportService airportService = new AirportServiceImpl();
    private static final CompanyService companyService = new CompanyServiceImpl();
    private static final FlyService flyService = new FlyServiceImpl();

    public static void main(String[] args) {

        Scanner sc1 = new Scanner(System.in);
    /*
    1. Créer et remplir 3 listes : companies , airports , flies
     */

        // On crée des fixtures pour Airports, Companies & Flies (l'ordre est important)
        createFixturesAirports(airportService);
        createFixturesCompanies(companyService);
        createFixturesFlies(flyService);


    /*
    2. Affichage du menu, loop sur le programme
     */
        int choiceInt = 1;
        //tant que le choix est différent de 5, on continue le programme
        do {
            ArrayList<Airport> airports = airportService.getAirports();
            ArrayList<Company> companies = companyService.getCompanies();
            displayMenu();
            String choice = sc1.next();
            // on redemande tant que ce n'est pas un chiffre entre 1 et 8 //TODO:make limit dynamic in loop
            while (true){
                try {
                    choiceInt = Integer.parseInt(choice);
                    if (choiceInt > 8 || choiceInt < 1){
                        System.err.println("Entrez un nombre entre 1 et 5 svp! ");
                        choice = sc1.next(); // clear scanner wrong input
                        continue; // continues to loop if exception is found
                    }
                    break;

                } catch (NumberFormatException e) {
                    //throw new RuntimeException(e);
                    System.err.println("Entrez un nombre svp! " + e.getMessage());
                    choice = sc1.next(); // clear scanner wrong input
                    continue; // continues to loop if exception is found
                }
            }

            switch (choiceInt){
                case 1:
                    userAddCompany();
                    System.out.println("Liste des compagnies actualisée: ");
                    displayCompaniesList(companies);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Liste des compagnies: ");
                    displayCompaniesList(companies);
                    System.out.println();
                    break;
                case 3:
                    userAddFly();
                    System.out.println();
                    break;
                case 4:
                    sortFliesByPrice();
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Au revoir !");
                    break;
                case 6:
                    sortFliesByDuration();
                    System.out.println();
                    break;
                case 7:
                    userAddAirport();
                    System.out.println("Liste des aéroports actualisée: ");
                    displayAirportsList(airports);
                    System.out.println();
                    break;
                case 8:
                    System.out.println("Liste des aéroports: ");
                    displayAirportsList(airports);
                    System.out.println();
                    break;
                default:
                    break;

            }

        }while (choiceInt != 5);
        System.out.println("FIN DU PROGRAMME.");
    }


    /**
     * ASK METHODS
     */

    //Ajouter une Compagnie
    public static void userAddCompany(){

        Scanner addSc = new Scanner(System.in);

        System.out.println("AJOUTER UNE COMPAGNIE : (pas d'espace dans le nom)");
        System.out.print("Nom: ");
        String companyName = addSc.next();

        companyService.addCompany(companyName);

    }

    //Ajouter un Aéroport
    public static void userAddAirport(){
        Scanner addSc = new Scanner(System.in);

        System.out.println("AJOUTER UN AÉROPORT : (pas d'espace dans le nom)");
        System.out.print("Nom: ");
        String airportName = addSc.next();

        airportService.addAirport(airportName);
    }

    //Ajouter un Vol
    public static void userAddFly(){
        Scanner addSc = new Scanner(System.in);
        int price = 0;
        String companyId = "";
        LocalDateTime hourDeparture = LocalDateTime.now();
        LocalDateTime hourArrival = LocalDateTime.now();
        String airportDepartureId = "";
        String airportArrivalId = "";
        ArrayList<Company> companies = companyService.getCompanies();
        ArrayList<Airport> airports = airportService.getAirports();
        System.out.println("AJOUTER UN VOL : ");

        // COMPANY
        companyId = askForCompany(companies, addSc);

        // PRICE
        price = askForPrice(addSc);

        // AIRPORT DEPARTURE
        System.out.println("Choisissez un aéroport de départ : ");
        airportDepartureId = askForAirport(airports, addSc);

        // HOUR DEPARTURE LOCALDATETIME
        hourDeparture = askForDateTime(addSc);

        // AIRPORT ARRIVAL
        System.out.println("Choisissez un aéroport d'arrivée : ");
        airportArrivalId = askForAirport(airports, addSc);

        // HOUR ARRIVAL LOCALDATETIME
        hourArrival = askForDateTime(addSc) ;

        System.out.println("Vol ajouté : ");
        System.out.println("Compagnie = " + companyService.getCompany(companyId));
        System.out.println("Prix = " + price +"€");
        System.out.println("Départ de " + airportService.getAirport(airportDepartureId));
        System.out.println("Arrivée à " + airportService.getAirport(airportArrivalId));

        flyService.addFly(price, companyId, hourDeparture, hourArrival, airportDepartureId, airportArrivalId);
    }


    /**
     *  ASK METHODS - for useAddFly()
     */

    /**
     * Demande à l'utilisateur une date et une heure en 2 temps
     * @param addSc Scanner
     * @return LocalDateTime
     */
    public static LocalDateTime askForDateTime(Scanner addSc) {
        boolean arrivalDateTimeOk = false;
        LocalDateTime dateTime = LocalDateTime.now();
        while (!arrivalDateTimeOk){
            System.out.println("Choississez votre jour de départ : ( au format dd-MM-yyyy)");
            String arrivalDate = addSc.next();
            System.out.println("Choississez votre heure de départ : ( au format HH:mm)");
            String arrivalHour = addSc.next();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String arrivalReal = arrivalDate + " " + arrivalHour;
            try {
                dateTime = LocalDateTime.parse(arrivalReal,formatter);
                arrivalDateTimeOk = true;
                break;

            } catch (Exception e) {
                // throw new RuntimeException(e);
                System.out.println("Date invalide.");
            }
        }
        return dateTime;
    }

    /**
     * Demande à l'utilisateur de choisir un aéroport dans la liste
     * @param airports liste des aéroports disponibles
     * @param addSc Scanner
     * @return id de l'aéroport choisis
     */
    public static String askForAirport( ArrayList<Airport> airports, Scanner addSc) {
        displayAirportsList(airports);
        boolean airportIsInArray = false;
        String airportIdString = "";
        while (!airportIsInArray){
            System.out.print("Taper son Id : ");
            airportIdString = addSc.next();

            for (Airport airp :
                    airports) {
                if (airp.getId().equals(airportIdString)) {
                    airportIsInArray = true;
                    break;
                }
            }
        }
        return airportIdString;
    }

    /**
     * Demande un prix à l'utilisateur
     * @param addSc Scanner
     * @return prix en euros
     */
    public static int askForPrice(Scanner addSc) {
        System.out.print("Prix en € : ");
        String priceString = addSc.next();
        int priceInt = 0;
        while (true){
            try {
                priceInt = Integer.parseInt(priceString);

                if (priceInt > 10000 || priceInt < 1){
                    System.err.println("Entrez un nombre entre 1 et 10000 svp! ");
                    priceString = addSc.next(); // clear scanner wrong input
                    continue; // continues to loop if exception is found
                }

                break;

            } catch (NumberFormatException e) {
                //throw new RuntimeException(e);
                System.err.println("Entrez un nombre entre 1 et 10000 svp! " + e.getMessage());
                priceString = addSc.next(); // clear scanner wrong input
                continue; // continues to loop if exception is found
            }
        }
        return priceInt;
    }

    /**
     * Demande à l'utilisateur de choisir une compagnie
     * @param companies liste des compagnies
     * @param addSc Scanner
     * @return id de la compagnie
     */
    public static String askForCompany(ArrayList<Company> companies , Scanner addSc) {
        System.out.println("Choisissez une compagnie: ");
        displayCompaniesList(companies);
        boolean companyIsInArray = false;
        String companyIdString = "";
        while (!companyIsInArray){
            System.out.print("Taper l'Id d'une compagnie : ");
            companyIdString = addSc.next();
            for (Company comp :
                    companies) {
                if (comp.getId().equals(companyIdString)) {
                    companyIsInArray = true;
                    break;
                }

            }

        }

        return companyIdString;
    }

    // SORT FLIES METHODS

    /**
     * Tri les vols par ordre de prix croissant
     */
    public static void sortFliesByPrice() {
        flyService.getFlies().sort(Fly.ComparatorPrice);
        System.out.println("Liste des Vols triés par prix croissant : ");
        displaySortedFlyList(flyService.getFlies());
    }

    /**
     * Tri les vols par durée croissante
     */
    public static void sortFliesByDuration() {
        flyService.getFlies().sort(Fly.ComparatorDuration);
        System.out.println("Liste des Vols triés par durée croissante : ");
        displaySortedFlyList(flyService.getFlies());
    }

    // DISPLAY LIST METHODS

    /**
     * Affiche la liste des vols
     * @param flylist liste des vols
     */
    public static void displaySortedFlyList(ArrayList<Fly> flylist) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm");
        for (Fly fly :
                flylist) {
            String displayFly = "";
            displayFly += "Vol #" + fly.getNumber();
            displayFly += " - Prix : " + fly.getPrice() + "€";
            displayFly += " - Compagnie : " + fly.getCompany().getName();
            displayFly += " - Départ de " + fly.getAirportDeparture();
            displayFly += " le " + fly.getHourDeparture().format(formatterDate);
            displayFly += " à " + fly.getHourDeparture().format(formatterHour);
            displayFly += " - Arrivée à " + fly.getAirportArrival();
            displayFly += " le " + fly.getHourArrival().format(formatterDate);
            displayFly += " à " + fly.getHourArrival().format(formatterHour);
            displayFly += " - Durée : " + fly.getDuration();

            System.out.println(displayFly);
        }
    }

    /**
     * Affiche la liste des aéroports
     * @param airports liste des aéroports
     */
    public static void displayAirportsList(ArrayList<Airport> airports) {
        for (Airport airport :
                airports) {
            System.out.println(airport.getId() + " - " + airport);
        }
    }

    /**
     * Affiche la liste des compagnies
     * @param companies liste des compagnies
     */
    public static void displayCompaniesList(ArrayList<Company> companies) {
        for (Company company :
                companies) {
            System.out.println(company.getId() + " - " + company);
        }
    }

    /**
     * Display Menu
     */
    public static void displayMenu() {
        System.out.println("MENU");
        System.out.println("1. Ajouter une compagnie aérienne");
        System.out.println("2. Voir toutes les compagnies");
        System.out.println("3. Ajouter un vol");
        System.out.println("4. Voir les vols triés par prix croissant");
        System.out.println("5. Quitter");
        System.out.println("6. Voir les vols triés par durée croissante");
        System.out.println("7. Ajouter un aéroport");
        System.out.println("8. Voir tout les aéroports");
        System.out.print("Entrez votre choix : ");
    }

    // FIXTURES

    public static void createFixturesFlies(FlyService flyService) {
        flyService.addFly(
                1200 ,
                "1",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(98),
                "1",
                "2");
        flyService.addFly(
                450 ,
                "2",
                LocalDateTime.now().plusHours(5),
                LocalDateTime.now().plusHours(7),
                "3",
                "4");
        flyService.addFly(
                2000 ,
                "5",
                LocalDateTime.now().plusMinutes(90),
                LocalDateTime.now().plusMinutes(180),
                "1",
                "4");
        flyService.addFly(
                350 ,
                "4",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(122),
                "4",
                "2");
        flyService.addFly(
                395 ,
                "2",
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(5),
                "3",
                "4");
        flyService.addFly(
                3300 ,
                "1",
                LocalDateTime.now().plusMinutes(180),
                LocalDateTime.now().plusMinutes(209),
                "1",
                "4");
        flyService.addFly(
                3300 ,
                "5",
                LocalDateTime.now().plusMinutes(180),
                LocalDateTime.now().plusMinutes(226),
                "1",
                "4");
        flyService.addFly(
                810 ,
                "4",
                LocalDateTime.now().plusMinutes(180),
                LocalDateTime.now().plusMinutes(209),
                "3",
                "2");
    }

    public static void createFixturesAirports(AirportService airportService) {
        airportService.addAirport("Roissy_Charles_de_Gaulle");
        airportService.addAirport("Heatrhow");
        airportService.addAirport("Orly");
        airportService.addAirport("St-Jacques/Rennes");
        airportService.addAirport("Mérignac/Bordeaux");
    }

    public static void createFixturesCompanies(CompanyService companyService) {
        companyService.addCompany("Air_France");
        companyService.addCompany("RyanAir");
        companyService.addCompany("easyJet");
        companyService.addCompany("KLM");
        companyService.addCompany("British_Airways");
    }
}

