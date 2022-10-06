package fr.kenb9027.serviceImpl;

import fr.kenb9027.buisiness.Company;
import fr.kenb9027.service.CompanyService;

import java.util.ArrayList;

public class CompanyServiceImpl implements CompanyService {
    private static ArrayList<Company> companies = new ArrayList<Company>() ;


    @Override
    public Company getCompany(String id) {
        for (Company company :
                companies) {
            if (company.getId().equals(id)) {
                return company;
            }
        }

        return null;
    }

    @Override
    public Company addCompany(String name) {
        Company newCompany = new Company(name);
        companies.add(newCompany);
        return newCompany;
    }

    @Override
    public ArrayList<Company> getCompanies() {
        return companies;
    }
}
