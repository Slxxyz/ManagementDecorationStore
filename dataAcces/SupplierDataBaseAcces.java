package dataAccess;

import exception.*;
import model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.SupplierDataAccess;

public class SupplierDataBaseAccess {

    @Override
    public void createSupplier(Supplier supplier) throws SupplierException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "INSERT INTO supplier VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1, supplier.getLegalName());
            statement.setString(2, supplier.getStreetAndNumber());
            statement.setString(3, supplier.getLocation());
            Integer postalCode = supplier.getPostalCode();
            if (postalCode != null) {
                statement.setInt(4, postalCode);
            } else {
                statement.setNull(4, Types.NULL);
            }


            statement.setString(5, supplier.getBankingInformation());

            String vATNumber = supplier.getVatNumber();
            if (vATNumber != null) {
                statement.setString(6, vATNumber);
            } else {
                statement.setNull(6, Types.NULL);
            }

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new SupplierException(exception.getMessage(), new OneException(), new CreateException());
        }
    }


    @Override
    public void deleteSupplier(String legalNameSupplier) throws SupplierException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "DELETE FROM supplier WHERE legalName = ?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1, legalNameSupplier);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new SupplierException(exception.getMessage(), new OneException(), new DeleteException());
        }
    }



    @Override// lis TOUTES les orderCustomer de la BD
    public ArrayList<Supplier> readAllSupplier() throws SupplierException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM supplier;";
            PreparedStatement statement = connexion.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
            while (data.next()) {

                String legalName = data.getString("legalName");
                String streetAndNumber = data.getString("streetAndNumber");
                String city = data.getString("city");
                Integer postalCode = data.getInt("postalCode");
                String bankingInformation = data.getString("bankingInformation");
                String vATNumber = data.getString("vATNumber");

                Supplier supplier = new Supplier(legalName, streetAndNumber, city, postalCode, bankingInformation, vATNumber);
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (SQLException exception) {
            throw new SupplierException(exception.getMessage(), new AllException(), new ReadException());
        }
    }





    @Override
    public Supplier readSupplier(String legalNameSupplier) throws SupplierException {
        try {
            Connection connexion = SingletonConnection.getInstance();
            String query = "SELECT * FROM supplier WHERE legalName = ?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1, legalNameSupplier);
            ResultSet data = statement.executeQuery();
            data.next();

            String legalName = data.getString("legalName");
            String streetAndNumber = data.getString("streetAndNumber");
            String city = data.getString("city");
            Integer postalCode = data.getInt("postalCode");
            String bankingInformation = data.getString("bankingInformation");
            String vATNumber = data.getString("vATNumber");

            return new Supplier(legalName, streetAndNumber, city, postalCode, bankingInformation, vATNumber);

        } catch (Exception exception) {
            throw new SupplierException(exception.getMessage(), new OneException(), new ReadException());
        }
    }


}



