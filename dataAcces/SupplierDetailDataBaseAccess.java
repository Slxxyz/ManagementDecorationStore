package dataAccess;

import exception.*;
import model.SupplierDetail;
import java.sql.*;
import java.time.LocalDate;
import interfaceAccess.SupplierDetailDataAccess;


public class SupplierDetailDataBaseAccess {
    //create
    @Override
    public void createSupplierDetail(SupplierDetail supplierDetail) throws SupplierDetailException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query =
                    """
                    INSERT INTO supplierDetail
                    VALUES (?,?,?,?,?,?);
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,supplierDetail.getProduct());
            statement.setString(2,supplierDetail.getSupplier());
            //Trouver solution pour LocalDateTime
            statement.setLocalDateTime(3,supplierDetail.getDateAndTime());
            statement.setInt(4,supplierDetail.getQuantity());
            statement.setDouble(5,supplierDetail.getUnitPrice());
            Integer minimumQuantity = supplierDetail.getMinimumQuantity();
            if(minimumQuantity != null){
                statement.setInt(6,supplierDetail.getMinimumQuantity());
            } else {
                statement.setNull(6, Types.NULL);
            }
            statement.executeUpdate();

        }
        catch (SQLException exception) {
            throw new SupplierDetailException(exception.getMessage(), new OneException(), new CreateException());
        }
    }
    //read
    @Override
    public SupplierDetail readSupplierDetail(int productCode, String supplierName, LocalDate dateAndTimeId) throws SupplierDetailException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM supplierDetail WHERE product = ? AND supplier=? AND dateAndTime= ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productCode);
            statement.setString(2, supplierName);
            //Trouver solution pour LocalDateTime
            statement.setLocalDate(3, dateAndTimeId);
            ResultSet data = statement.executeQuery();
            data.next();
            int product = data.getInt("product");
            String supplier = data.getString("supplier");
            LocalDateTime dateAndTime = data.getLocalDateTime("dateAndTime");
            int quantity = data.getInt("quantity");
            double unitPrice = data.getDouble("unitPrice");
            Integer minimumQuantity = data.getInt("minimumQuantity");
            return new SupplierDetail(product, supplier, dateAndTime, quantity, unitPrice, minimumQuantity);
        } catch (SQLException exception) {
            throw new SupplierDetailException(exception.getMessage(), new OneException(), new ReadException());
        }
    }

    //update
    @Override
    public void updateSupplierDetail(SupplierDetail supplierDetail) throws SupplierDetailException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query =
                    """
                    UPDATE supplierDetail
                    SET supplier = ?, dateAndTime = ?, quantity = ?, unitPrice = ?, minimumQuantity = ?
                    WHERE product = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,supplierDetail.getSupplier());
            //Trouver solution pour LocalDateTime
            statement.setLocalDateTime(2,supplierDetail.getDateAndTime());
            statement.setInt(3,supplierDetail.getQuantity());
            statement.setDouble(4,supplierDetail.getUnitPrice());
            Integer minimumQuantity = supplierDetail.getMinimumQuantity();
            if(minimumQuantity != null){
                statement.setInt(5,supplierDetail.getMinimumQuantity());
            } else {
                statement.setNull(5, Types.NULL);
            }
            statement.setInt(6,supplierDetail.getProduct());
            statement.executeUpdate();

        }
        catch (SQLException exception) {
            throw new SupplierDetailException(exception.getMessage(), new OneException(), new UpdateException());
        }
    }


}
