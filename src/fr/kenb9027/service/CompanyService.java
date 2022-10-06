package fr.kenb9027.service;

import fr.kenb9027.business.Company;

import java.util.ArrayList;

public interface CompanyService {

    Company getCompany(String id);

    Company addCompany(String name);

    ArrayList<Company> getCompanies();
}
