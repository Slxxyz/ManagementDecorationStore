package userInterface;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

class CoverPageManager {
    private String name;
    private Image image;
    private String description;

    public CoverPageManager(String name, String imagePath, String description) {
        this.name = name;
        loadImage(imagePath);
        this.description = description;
    }

    private void loadImage(String imagePath) {
        try {
            // Charger l'image depuis le fichier
            URL url = getClass().getResource(imagePath);
            if (url != null) {
                image = new ImageIcon(url).getImage();
            } else {
                System.out.println("Impossible de charger l'image à partir du chemin : " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
