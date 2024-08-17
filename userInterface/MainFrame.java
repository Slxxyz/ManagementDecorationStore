package userInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel currentPanel;
    private static final int SCREEN_RESOLUTION_X = 1920;
    private static final int SCREEN_RESOLUTION_Y = 1080;
    private static final int WINDOWS_RESOLUTION_X = 1280;
    private static final int WINDOWS_RESOLUTION_Y = 730;

    public MainFrame() {
        this.currentPanel = new WelcomePanel();
        this.add(currentPanel);

        JMenu crud = new JMenu("CRUD");
        JMenuItem newCustomer = new JMenuItem("Ajout d'un client");
        JMenuItem readCustomer = new JMenuItem("Affichage des clients");
        JMenuItem editCustomer = new JMenuItem("Modification d'un client");
        JMenuItem deleteCustomer = new JMenuItem("Suppression d'un client");

        newCustomer.addActionListener(this);
        readCustomer.addActionListener(this);
        editCustomer.addActionListener(this);
        deleteCustomer.addActionListener(this);

        crud.add(newCustomer);
        crud.add(readCustomer);
        crud.add(editCustomer);
        crud.add(deleteCustomer);

        JMenu search = new JMenu("Recherche");
        JMenuItem customerOrder = new JMenuItem("Commandes"); //SearchEachOrderOfCustomer
        JMenuItem productOrder = new JMenuItem("Produits"); //SearchEachOrderOfProduct
        JMenuItem supplier = new JMenuItem("Fournisseurs"); //SearchSupplierHistory

        search.addActionListener(this);
        customerOrder.addActionListener(this);
        productOrder.addActionListener(this);
        supplier.addActionListener(this);

        search.add(customerOrder);
        search.add(productOrder);
        search.add(supplier);

        JMenu jobTask = new JMenu("Tâche Métier");
        JMenuItem stockInformation = new JMenuItem("Afficher les informations de stock");

        jobTask.addActionListener(this);
        stockInformation.addActionListener(this);

        jobTask.add(stockInformation);

        JMenu newProductThread = new JMenu("Nouveaux produits");
        JMenuItem newProduct = new JMenuItem("Nouveaux produits");
        JMenuItem stopProduct = new JMenuItem("Arrêter");

        newProductThread.addActionListener(this);
        newProduct.addActionListener(this);
        stopProduct.addActionListener(this);

        newProductThread.add(newProduct);
        newProductThread.add(stopProduct);

        JMenu leave = new JMenu("Quitter");
        JMenuItem exit = new JMenuItem("Quitter");
        leave.addActionListener(this);
        exit.addActionListener(this);
        leave.add(exit);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(crud);
        menuBar.add(search);
        menuBar.add(jobTask);
        menuBar.add(newProductThread);
        menuBar.add(leave);

        this.setJMenuBar(menuBar);
        this.addWindowListener(new MainFrameListener());

        this.setBounds(
                (SCREEN_RESOLUTION_X - WINDOWS_RESOLUTION_X) / 5,
                (SCREEN_RESOLUTION_Y - WINDOWS_RESOLUTION_Y) / 5,
                WINDOWS_RESOLUTION_X,
                WINDOWS_RESOLUTION_Y
        );

        setVisible(true);
    }

    //TODO: Création d'objet peut être redondante (fix: avec un State Pattern)

    private JPanel getPanel(String name) {
        switch (name) {
            case "Ajout d'un client" -> {
                return new AddCustomerPanel();
            }
            case "Affichage des clients" -> {
                return new ListingCustomerPanel();
            }
            case "Modification d'un client" -> {
                return new EditCustomerPanel();
            }
            case "Suppression d'un client" -> {
                return new DeleteCustomerPanel();
            }
            case "Commandes" -> {
                return new SearchOrderCustomerPanel();
            }
            case "Produits" -> {
                return new SearchOrderProductPanel();
            }
            case "Fournisseurs" -> {
                return new SearchSupplierHistoryPanel();
            }
            case "Afficher les informations de stock" -> {
                return new SalesSearchPanel();
            }
            case "Nouveaux produits" -> {
                CoverPageManager[] products = {
                        new CoverPageManager("Montagne Enneigée", "../tableau.jpg","Voyager sans bouger, c'est possible désormais !"),
                        new CoverPageManager("PapyMamy Lampe", "../lampe.jpeg",    "Retour vers le passé, pour un futur lumineux !"),
                        new CoverPageManager("Moderna Blocusa", "../chair.jpeg",   "Chaise moderne pour un blocus plus confortable !")
                };
                ProductPanel productPanel = new ProductPanel(products[0]);
                ProductDisplayThread displayThread = new ProductDisplayThread(productPanel, products);
                displayThread.start();
                return productPanel;
            }
            case "Arrêter" -> {
                if(currentPanel instanceof ProductPanel productPanel){
                    ProductDisplayThread displayThread = productPanel.getDisplayThread();
                    if(displayThread != null){
                        displayThread.stopThread();
                    }
                }
                this.currentPanel = new WelcomePanel();
                this.add(currentPanel);
                this.revalidate();
                this.repaint();
                return currentPanel;
            }
            case "Quitter" -> {
                System.exit(0);
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String action = event.getActionCommand();
        if (currentPanel != null) {
            this.remove(currentPanel);
        }
        currentPanel = this.getPanel(action);
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
    }
}