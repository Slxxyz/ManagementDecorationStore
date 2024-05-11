package userInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel currentPanel;
    private static final int SCREEN_RESOLUTION_X = 1920;
    private static final int SCREEN_RESOLUTION_Y = 1080;
    private static final int WINDOWS_RESOLUTION_X = 700;
    private static final int WINDOWS_RESOLUTION_Y = 500;

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

        JMenu colorThread = new JMenu("LED multicouleur");
        JMenuItem colorLED = new JMenuItem("LED multicouleur");

        colorThread.addActionListener(this);
        colorLED.addActionListener(this);

        colorThread.add(colorLED);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(crud);
        menuBar.add(search);
        menuBar.add(jobTask);
        menuBar.add(colorThread);

        this.setJMenuBar(menuBar);
        this.addWindowListener(new MainFrameListener());

        this.setBounds(
                (SCREEN_RESOLUTION_X / 2) - (WINDOWS_RESOLUTION_X / 2),
                (SCREEN_RESOLUTION_Y / 2) - (WINDOWS_RESOLUTION_Y / 2),
                WINDOWS_RESOLUTION_X,
                WINDOWS_RESOLUTION_Y
        );

        setVisible(true);
    }

    private JPanel gePanel(String name) {
        switch (name) {
            case "Ajout d'un client" -> {
                return new
            }
            case "Affichage des clients" -> {
                return new
            }
            case "Modification d'un client" -> {
                return new
            }
            case "Suppression d'un client" -> {
                return new
            }
            case "Commandes" -> {
                return new
            }
            case "Produits" -> {
                return new
            }
            case "Fournisseurs" -> {
                return new
            }
            case "Afficher les informations de stock" -> {
                return new
            }
            case "LED multicouleur" -> {
                LEDPanel ledPanel = new LEDPanel();
                LEDMovingThread multicolorThread = new LEDMovingThread(ledPanel);
                multicolorThread.start();

                return ledPanel;
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
        currentPanel = this.gePanel(action);
        this.add(currentPanel);
        this.revalidate();
        this.repaint();
    }
}
