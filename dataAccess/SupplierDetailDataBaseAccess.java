package dataAccess;

import exception.*;
import model.SupplierDetail;
import java.sql.*;
import java.util.ArrayList;
import interfaceAccess.SupplierDetailDataAccess;


public class SupplierDetailDataBaseAccess implements SupplierDetailDataAccess{
    //create
    @Override
    public void createSupplierDetail(SupplierDetail supplierDetail) throws SupplierDetailException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query =
                    """
                    INSERT INTO supplierdetail
                    VALUES (?,?,?,?,?,?);
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,supplierDetail.getProduct());
            statement.setString(2,supplierDetail.getSupplier());
            statement.setTimestamp(3,supplierDetail.getDateAndTime());
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
    public SupplierDetail readSupplierDetail(int productCode, String supplierName, Timestamp dateAndTimeId) throws SupplierDetailException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM supplierdetail WHERE product = ? AND supplier=? AND dateAndTime= ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productCode);
            statement.setString(2, supplierName);
            statement.setTimestamp(3, dateAndTimeId);
            ResultSet data = statement.executeQuery();
            data.next();
            int product = data.getInt("product");
            String supplier = data.getString("supplier");
            Timestamp dateAndTime = data.getTimestamp("dateAndTime");
            int quantity = data.getInt("quantity");
            double unitPrice = data.getDouble("unitPrice");
            SupplierDetail supplierDetail = new SupplierDetail(product, supplier, dateAndTime, quantity, unitPrice);
            Integer minimumQuantity = data.getInt("minimumQuantity");
            if (!data.wasNull()){
                supplierDetail.setMinimumQuantity(minimumQuantity);
            }
            return supplierDetail;
        } catch (SQLException exception) {
            throw new SupplierDetailException(exception.getMessage(), new OneException(), new ReadException());
        }
    }

    //readAll
    @Override
    public ArrayList<SupplierDetail> readAllSupplierDetails() throws SupplierDetailException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String query = "SELECT * FROM supplierdetail;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet data = statement.executeQuery();
            ArrayList<SupplierDetail> supplierDetails = new ArrayList<SupplierDetail>();
            while (data.next()) {
                int product = data.getInt("product");
                String supplier = data.getString("supplier");
                Timestamp dateAndTime = data.getTimestamp("dateAndTime");
                int quantity = data.getInt("quantity");
                double unitPrice = data.getDouble("unitPrice");
                SupplierDetail supplierDetail = new SupplierDetail(product, supplier, dateAndTime, quantity, unitPrice);

                Integer minimumQuantity = data.getInt("minimumQuantity");
                if (!data.wasNull()){
                    supplierDetail.setMinimumQuantity(minimumQuantity);
                }
                supplierDetails.add(supplierDetail);
            }
            return supplierDetails;
        } catch (SQLException exception) {
            throw new SupplierDetailException(exception.getMessage(), new AllException(), new ReadException());
        }
    }

}
