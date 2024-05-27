package business;

import dataAccess.SupplierDataBaseAccess;
import interfaceAccess.SupplierDataAccess;
import exception.SupplierException;
import model.Supplier;
import java.util.ArrayList;
public class SupplierManager {
    private SupplierDataAccess supplierAccess;
    public SupplierManager() {
        setSupplierManager(new SupplierDataBaseAccess());
    }

    public void setSupplierManager(SupplierDataAccess supplierAccess) {
        this.supplierAccess = supplierAccess;
    }
    //create
    public void createSupplier(Supplier supplier) throws SupplierException {
        supplierAccess.createSupplier(supplier);
    }
    //read
    public Supplier readSupplier(String legalNameSupplier) throws SupplierException {
        return supplierAccess.readSupplier(legalNameSupplier);
    }
    //update
    public void updateSupplier(Supplier supplier) throws SupplierException {
        supplierAccess.updateSupplier(supplier);
    }
    //delete
    public void deleteSupplier(String legalNameSupplier) throws SupplierException {
        supplierAccess.deleteSupplier(legalNameSupplier);
    }
    //readAll
    public ArrayList<Supplier> readAllSupplier() throws SupplierException {
        return supplierAccess.readAllSupplier();
    }

}
