package dataAcces;

import exception.*;
import interfaceAccess.City;
import model.City;
import java.sql.Date;

import java.sql.*;
import java.util.ArrayList;



public class CityDataBaseAcces {
    @Override
    public ArrayList<City> readAllCity() throws CityException {
        try {
            Connection connexion = SingletonConnexion.getInstance();
            String query = "SELECT * FROM city;";
            PreparedStatement statement = connexion.prepareStatement(query);
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





    @Override
    public City readCity(int id) throws CityException {
        try {
            Connection connexion = SingletonConnexion.getInstance();
            String query = "SELECT * FROM city WHERE cityId = ?;";

            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet data = statement.executeQuery();
            data.next();

            int cityId = data.getInt("cityId");
            String cityName = data.getString("cityName");

            return new City(cityId,cityName);
        } catch (SQLException exception) {
            throw new CityException(exception.getMessage(), new OneException(), new ReadException());
        }
    }



}
