package dataAccess;

import exception.*;
import model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.SupplierDataAccess;

public class SupplierDataBaseAccess implements SupplierDataAccess{

    @Override
    public void createSupplier(Supplier supplier) throws SupplierException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "INSERT INTO supplier VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
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

    //read
    @Override
    public Supplier readSupplier(String legalNameSupplier) throws SupplierException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM supplier WHERE legalName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, legalNameSupplier);
            ResultSet data = statement.executeQuery();
            data.next();
            String legalName = data.getString("legalName");
            String streetAndNumber = data.getString("streetAndNumber");
            String city = data.getString("city");
            String bankingInformation = data.getString("bankingInformation");
            Supplier supplier = new Supplier(legalName, streetAndNumber, city, bankingInformation);
            Integer postalCode = data.getInt("postalCode");
            if (!data.wasNull()) {
                supplier.setPostalCode(postalCode);
            }
            String vATNumber = data.getString("vATNumber");
            if (!data.wasNull()) {
                supplier.setVatNumber(vATNumber);
            }

            return supplier;

        } catch (Exception exception) {
            throw new SupplierException(exception.getMessage(), new OneException(), new ReadException());
        }
    }

    //update
    @Override
    public void updateSupplier(Supplier supplier) throws SupplierException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "UPDATE supplier SET streetAndNumber = ?, city = ?, postalCode = ?, bankingInformation = ?, vATNumber = ? WHERE legalName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, supplier.getStreetAndNumber());
            statement.setString(2, supplier.getLocation());
            Integer postalCode = supplier.getPostalCode();
            if (postalCode != null) {
                statement.setInt(3, postalCode);
            } else {
                statement.setNull(3, Types.NULL);
            }
            statement.setString(4, supplier.getBankingInformation());
            String vATNumber = supplier.getVatNumber();
            if (vATNumber != null) {
                statement.setString(5, vATNumber);
            } else {
                statement.setNull(5, Types.NULL);
            }
            statement.setString(6, supplier.getLegalName());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new SupplierException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }


    @Override
    public void deleteSupplier(String legalNameSupplier) throws SupplierException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "DELETE FROM supplier WHERE legalName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, legalNameSupplier);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new SupplierException(exception.getMessage(), new OneException(), new DeleteException());
        }
    }

    //readAll
    @Override
    public ArrayList<Supplier> readAllSupplier() throws SupplierException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM supplier;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
            while (data.next()) {
                String legalName = data.getString("legalName");
                String streetAndNumber = data.getString("streetAndNumber");
                String city = data.getString("city");
                String bankingInformation = data.getString("bankingInformation");
                Supplier supplier = new Supplier(legalName, streetAndNumber, city, bankingInformation);

                Integer postalCode = data.getInt("postalCode");
                if (!data.wasNull()) {
                    supplier.setPostalCode(postalCode);
                }
                String vATNumber = data.getString("vATNumber");
                if (!data.wasNull()) {
                    supplier.setVatNumber(vATNumber);
                }
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (SQLException exception) {
            throw new SupplierException(exception.getMessage(), new AllException(), new ReadException());
        }
    }
}



