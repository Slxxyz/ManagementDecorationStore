package dataAccess;

import exception.*;
import model.City;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.CityDataAccess;

public class CityDataBaseAccess implements CityDataAccess{
    @Override
    public City readCity(int city) throws CityException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM city WHERE cityId = ?;";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,city);
            ResultSet data = statement.executeQuery();
            data.next();

            int cityId = data.getInt("cityId");
            String cityName = data.getString("cityName");

            return new City(cityId,cityName);
        } catch (SQLException exception) {
            throw new CityException(exception.getMessage(), new OneException(), new ReadException());
        }
    }
    @Override
    public ArrayList<City> readAllCities() throws CityException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM city;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<City> cities = new ArrayList<City>();
            while (data.next()) {
                int cityId = data.getInt("cityId");
                String cityName = data.getString("cityName");

                City city = new City(cityId,cityName);
                cities.add(city);
            }
            return cities;
        } catch (SQLException exception) {
            throw new CityException(exception.getMessage(), new AllException(), new ReadException());
        }
    }
}