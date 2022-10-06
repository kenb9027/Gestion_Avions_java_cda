package fr.kenb9027.buisiness;

import java.util.ArrayList;

public class Airport {


    private String id;
    private String name;

    private ArrayList<Fly> flies;

    public Airport() {
        super();
        this.flies = new ArrayList<Fly>();
    }

    public Airport(String name) {
        this();
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " Airport";
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
