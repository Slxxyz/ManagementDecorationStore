package controller;

import business.SupplierManager;
import exception.SupplierException;
import model.Supplier;
import java.util.ArrayList;
public class SupplierController {
    private SupplierManager manager;
    public SupplierController() {
        setManager(new SupplierManager());
    }
    public void setManager(SupplierManager manager) {
        this.manager = manager;
    }
    //create
    public void createSupplier(Supplier supplier) throws SupplierException {
        this.manager.createSupplier(supplier);
    }
    //read
    public Supplier readSupplier(String legalNameSupplier) throws SupplierException {
        return this.manager.readSupplier(legalNameSupplier);
    }
    //update
    public void updateSupplier(Supplier supplier) throws SupplierException {
        this.manager.updateSupplier(supplier);
    }
    //delete
    public void deleteSupplier(String legalNameSupplier) throws SupplierException {
        this.manager.deleteSupplier(legalNameSupplier);
    }
    //readAll
    public ArrayList<Supplier> readAllSupplier() throws SupplierException {
        return this.manager.readAllSupplier();
    }
}
