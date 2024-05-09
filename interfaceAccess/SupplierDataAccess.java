package interfaceAccess;

import exception.SupplierException;
import model.Supplier;
import java.util.ArrayList;


public interface SupplierDataAccess {
    //create
    public void createSupplier(Supplier supplier) throws SupplierException;
    //read
    public Supplier readSupplier(String legalNameSupplier) throws SupplierException;
    //update
    public void updateSupplier(Supplier supplier) throws SupplierException;
    //delete
    public void deleteSupplier(String legalNameSupplier) throws SupplierException;
    //read all
    public ArrayList<Supplier> readAllSupplier() throws SupplierException;
}
