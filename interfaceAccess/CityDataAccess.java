package interfaceAccess;

import exception.CityException;
import model.City;
import java.util.ArrayList;

public interface CityDataAccess {
    //read
    public City readCity(int city) throws CityException;
    //readAll
    public ArrayList<City> readAllCities() throws CityException;
}
