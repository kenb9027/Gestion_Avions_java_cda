package fr.kenb9027.business;

import java.util.ArrayList;

public class Company {

    private String id;
    private String name;

    private ArrayList<Fly> flies ;

    public Company() {
        super();
        this.flies = new ArrayList<Fly>();
    }

    public Company(String name) {
        this();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company " + name ;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Fly> getFlies() {
        return flies;
    }

    public void setFlies(ArrayList<Fly> flies) {
        this.flies = flies;
    }
}
