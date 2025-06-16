/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */
import com.pdcgame.Enums.ProductStorageType;
import com.pdcgame.GameState;
import com.pdcgame.ProductTypes.Product;
import com.pdcgame.ProductTypes.PurchasableProduct;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FridgePanel extends JPanel {

    // background image for the fridge panel
    private final Image backgroundImage;

    // list of shelf items displayed on the panel
    private final List<ShelfItem> shelfItems = new ArrayList<>();

    // buttons corresponding to each shelf item for interaction
    private final List<JButton> buttons = new ArrayList<>();

    // listener interface for product click events
    public interface ProductClickListener {
        void onProductClicked(Product product);
    }

    // instance of the listener to notify clicks
    private ProductClickListener listener;

    // set the listener to handle product clicks
    public void setProductClickListener(ProductClickListener listener) {
        this.listener = listener;
    }

    // constructor to initialize panel
    public FridgePanel() {
        backgroundImage = loadImage("/fridge.png"); // load fridge background image
        setPreferredSize(new Dimension(700, 500));  // set fixed size of panel
        setLayout(null);    // absolute positioning for buttons
        setOpaque(false);   // make panel transparent for custom painting

        populateShelf();    // create and add shelf items/buttons
    }

    // populate the fridge with purchasable products as shelf items with buttons
    private void populateShelf() {
        int x = 260, y = 70, w = 80, h = 80;    // initial button position and size
        int spacing = 80;   // horizontal spacing between buttons

        // loop through filtered products for fridge storage
        for (PurchasableProduct product : GameState.instance().getProductManager()
                .getFilteredPurchasableProducts(ProductStorageType.FRIDGE)) {

            String imagePath = "/" + product.getName().toLowerCase() + ".png";
            addShelfItem(imagePath, x, y, w, h, product);  // create shelf item with icon

            x += spacing;   // move right for next item
            if (x > 400) {  // if past max width, wrap to next row
                x = 260;
                y += 100;
            }
        }

        // create buttons for each shelf item and add to panel
        for (ShelfItem item : shelfItems) {
            JButton btn = new JButton(item.icon);
            btn.setBounds(item.x, item.y, item.width, item.height);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setOpaque(false);

            // add click listener to notify the product click
            btn.addActionListener(e -> onButtonClicked(item.product));

            add(btn);   // add button to panel UI
            buttons.add(btn);   // keep track of buttons
        }
    }

    // called when a product button is clicked
    private void onButtonClicked(Product product) {
        System.out.println("Clicked: " + product.getName());
        if (listener != null) {
            System.out.println("Listener is set. Calling...");
            listener.onProductClicked(product);
        } else {
            System.out.println("Listener is NULL!");
        }
    }

    // helper method to create and add a shelf item with scaled icon
    private void addShelfItem(String imagePath, int x, int y, int width, int height, Product product) {
        ImageIcon icon = loadAndScaleIcon(imagePath, width, height);
        shelfItems.add(new ShelfItem(icon, x, y, width, height, product));
    }

    // loads an image from resources
    private Image loadImage(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl == null) {
            System.err.println("Shelf image not found: " + path);
            return null;
        }
        return new ImageIcon(imgUrl).getImage();
    }

    // loads and scales an icon image for buttons
    private ImageIcon loadAndScaleIcon(String path, int width, int height) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl == null) {
            System.err.println("Button image not found: " + path);
            return new ImageIcon();
        }
        Image img = new ImageIcon(imgUrl).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    // custom paint to draw the fridge background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
