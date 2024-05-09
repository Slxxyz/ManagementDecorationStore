package controller;

import business.CityManager;
import exception.CityException;
import model.City;
import java.util.ArrayList;

public class CityController {
private CityManager manager;
    public CityController() {
        setManager(new CityManager());
    }
    public void setManager(CityManager manager) {
        this.manager = manager;
    }
    //read
    public City readCity(int city)throws CityException {
        return this.manager.readCity(city);
    }
    //readAll
    public ArrayList<City> readAllCities() throws CityException {
        return this.manager.readAllCities();
    }
}
