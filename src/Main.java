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
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    private static AirportService airportService = new AirportServiceImpl();
    private static CompanyService companyService = new CompanyServiceImpl();
    private static FlyService flyService = new FlyServiceImpl();

    public static void main(String[] args) {

        Scanner sc1 = new Scanner(System.in);

         //Créer et remplir 3 listes : companies , airports , flies
        ArrayList<Airport> airports = airportService.getAirports();
        ArrayList<Company> companies = companyService.getCompanies();
        ArrayList<Fly> flies = flyService.getFlies();

        airportService.addAirport("Roissy Charles de Gaulle");
        airportService.addAirport("Heatrhow");
        airportService.addAirport("Mérignac Bordeaux");
        companyService.addCompany("Air France");
        companyService.addCompany("RyanAir");
        companyService.addCompany("easyJet");
        System.out.println(airports);
        System.out.println(companies);

        int choiceInt = 1;

        //tant que différent de 5, on continue le programme
        do {
            //Creer affichage menu
            System.out.println("MENU");
            System.out.println("1. Ajouter une compagnie aérienne");
            System.out.println("2. Voir toutes les compagnies");
            System.out.println("3. Ajouter un vol");
            System.out.println("4. Voir les vols triés par prix croissant");
            System.out.println("5. Quitter");
            System.out.print("Entrez votre choix : ");
            String choice = sc1.next();
            // on redemande tant que ce n'est pas un chiffre entre 1 et 5
            while (true){
                try {
                    choiceInt = Integer.parseInt(choice);

                    if (choiceInt > 5 || choiceInt < 1){
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
                    System.out.println(companies);
                    break;
                case 2:
                    System.out.println("Liste des compagnies: ");
                    System.out.println(companies);
                    break;
                case 3:
                    userAddFly();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    break;

            }

        }while (choiceInt != 5);

        //creer les methodes pour répondre au menu


        System.out.println("FIN");

    }


    /**
     * METHOD
     * Ajouter une compagnie
     */
    public static void userAddCompany(){

        Scanner addSc = new Scanner(System.in);

        System.out.println("AJOUTER UNE COMPAGNIE");
        System.out.print("Nom: ");
        String companyName = addSc.next();

        companyService.addCompany(companyName);
        addSc.close();

    }

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


        System.out.println("AJOUTER UN VOL");

        // COMPANY
        System.out.println("Choisissez une compagnie: ");
        for (Company company :
                companies) {
            System.out.println(company.getId() + "-" + company);
        }
        System.out.print("Taper son Id : ");
        String companyIdString = addSc.next();
        boolean isInArray = false;
        while (!isInArray){
            try {
                for (Company comp :
                        companies) {
                        if (comp.getId().equals(companyIdString)) {
                            isInArray = true;
                        }
                        else {
                            System.err.println("Entrez un Id valable svp.");
                            companyIdString = addSc.next(); // clear scanner wrong input
                            continue; // continues to loop if exception is found
                        }
                    }

                break;

            } catch (NumberFormatException e) {
                //throw new RuntimeException(e);
                System.err.println("Entrez un Id valable svp.");
                companyIdString = addSc.next(); // clear scanner wrong input
                continue; // continues to loop if exception is found
            }
        }
        companyId = companyIdString;


        // PRICE
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
        price = priceInt;

        // AIRPORT DEPARTURE

        System.out.println("companyId = " + companyId);
        System.out.println("price = " + price);

    }

}

