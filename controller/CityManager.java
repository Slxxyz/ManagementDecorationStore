package business;

import dataAccess.CityDataBaseAccess;
import interfaceAccess.CityDataAccess;
import exception.CityException;
import model.City;
import java.util.ArrayList;


public class CityManager {
    private CityDataAccess cityAccess;
    public CityManager(){
        setCityManager(new CityDataBaseAccess());
    }
    public void setCityManager(CityDataAccess cityAccess){
        this.cityAccess = cityAccess;
    }
    //read
    public City readCity(int city) throws CityException{
        return this.cityAccess.readCity(city);
    }
    //readAll
    public ArrayList<City> readAllCities() throws CityException{
        return this.cityAccess.readAllCities();
    }
}
