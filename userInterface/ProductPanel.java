package userInterface;

import javax.swing.*;
import java.awt.*;

class ProductPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel imageLabel;

    public ProductPanel(CoverPageManager product) {
        setLayout(new BorderLayout());

        // Créer un JLayeredPane pour superposer les composants
        JLayeredPane layeredPane = new JLayeredPane();

        // Créer le JLabel pour afficher l'image
        imageLabel = new JLabel(new ImageIcon(product.getImage()));
        imageLabel.setBounds(0, 0, product.getImage().getWidth(null), product.getImage().getHeight(null));
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        // Créer le JLabel pour afficher le nom du produit
        nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        nameLabel.setBounds(20, 20, 200, 30);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(nameLabel, JLayeredPane.PALETTE_LAYER);



        // Créer le JLabel pour afficher la description du produit
        descriptionLabel = new JLabel(product.getDescription());
        descriptionLabel.setFont(new Font("Roboto", Font.ITALIC, 13));
        descriptionLabel.setBounds(20, 60, 300, 60);
        descriptionLabel.setOpaque(true);
        descriptionLabel.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(descriptionLabel, JLayeredPane.PALETTE_LAYER);

        // Ajouter le JLayeredPane au panneau principal
        add(layeredPane, BorderLayout.CENTER);
    }

    public void setProduct(CoverPageManager product) {
        nameLabel.setText(product.getName());
        descriptionLabel.setText(product.getDescription());
        imageLabel.setIcon(new ImageIcon(product.getImage()));
    }
}
