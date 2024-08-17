package userInterface;

import javax.swing.*;


class ProductDisplayThread extends Thread {
    private final ProductPanel productPanel;
    private final CoverPageManager[] products;
    private boolean running = true;
    private int currentIndex = 0;

    public ProductDisplayThread(ProductPanel productPanel, CoverPageManager[] products) {
        this.productPanel = productPanel;
        this.products = products;
    }

    @Override
    public void run() {
        try {
            while (running) {
                SwingUtilities.invokeLater(() -> {
                    productPanel.setProduct(products[currentIndex]);
                    currentIndex = (currentIndex + 1) % products.length; // Mettre à jour l'indice du produit
                });
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stopThread() {
        running = false;
    }
}