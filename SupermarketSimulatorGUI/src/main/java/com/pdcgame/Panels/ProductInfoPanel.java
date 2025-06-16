    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.pdcgame.Panels;

    /**
     *
     * @author prish
     */
    import com.pdcgame.Enums.InternalCases;
    import com.pdcgame.GamePersistence;
    import com.pdcgame.GameState;
    import com.pdcgame.Managers.ActionManager;
    import javax.swing.*;
    import java.awt.*;

    import com.pdcgame.Managers.CartManager;
    import com.pdcgame.ProductTypes.Product;

    import java.util.Map;

    public class ProductInfoPanel extends JPanel {

        // main panel showing product details
        private final JPanel productInfoPanel;
        // panel to display cart items
        private final JPanel cartPanel;
        // button to add currently selected product to cart
        final JButton addToCartButton;
        // button to view the cart contents
        private final JButton viewCartButton;
        // panel holding buttons related to product actions
        private final JPanel productButtonsPanel;
        // panel holding buttons related to cart actions
        private final JPanel cartButtonsPanel;
        // scroll pane to hold the cart panel and allow scrolling
        private final JScrollPane cartScrollPane;
        // label showing the total cost of items in the cart
        private final JLabel cartTotalLabel = new JLabel();
        // button to finalize and purchase the cart contents
        private final JButton purchaseCartButton = new JButton("Purchase Cart");

        // button to go back from cart to product info view
        private JButton backButton;
        // currently selected product shown in the info panel
        private Product currentProduct;

        public ProductInfoPanel() {
            setLayout(new BorderLayout());  // use border layout for main panel
            setBackground(new Color(70, 63, 58));  // set dark background color
            setPreferredSize(new Dimension(400, 0));  // fixed width, height flexible

            // initialize product info panel with vertical box layout and padding
            productInfoPanel = new JPanel();
            productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));
            productInfoPanel.setBackground(new Color(70, 63, 58));
            productInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            productInfoPanel.add(createCenteredMessageLabel("Click on any product to start!")); // initial message

            // initialize cart panel with vertical box layout and padding
            cartPanel = new JPanel();
            cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
            cartPanel.setBackground(new Color(70, 63, 58));
            cartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // create scroll pane for the cart panel with vertical scroll bar always visible
            cartScrollPane = new JScrollPane(cartPanel);
            cartScrollPane.setBorder(null);  // remove border on scroll pane
            cartScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            cartScrollPane.getVerticalScrollBar().setUnitIncrement(16);  // scroll speed
            cartScrollPane.getViewport().setBackground(new Color(70, 63, 58));  // match background color

            // initialize buttons for product/cart actions
            addToCartButton = new JButton("Add to Cart");
            viewCartButton = new JButton("View Cart");
            backButton = new JButton("Back");

            // apply styling to buttons for consistent look
            styleButton(addToCartButton);
            styleButton(viewCartButton);
            styleButton(backButton);
            styleButton(purchaseCartButton);

            // add action listener to purchase button to handle checkout logic
            purchaseCartButton.addActionListener(e -> {
                if (!CartManager.instance().cartEmpty()) {  // only proceed if cart not empty
                    InternalCases result = CartManager.instance().canCheckoutCart();  // check if purchase allowed

                    JLabel messageLabel;
                    // show message based on checkout result
                    switch (result) {
                        case NO_ACTIONS:
                            messageLabel = createCenteredMessageLabel("NO ACTIONS REMAINING");
                            break;
                        case NO_FUNDS:
                            messageLabel = createCenteredMessageLabel("NOT ENOUGH FUNDS");
                            break;
                        case SUCCESS:
                            cartPanel.removeAll();  // clear cart display on success
                            messageLabel = createCenteredMessageLabel("Purchase complete! Thanks.");
                            break;
                        default:
                            messageLabel = createLabel("Unknown error occurred.", "");
                            break;
                    }

                    cartPanel.add(messageLabel);  // add feedback message to cart panel
                    revalidate();
                    repaint();
                }
            });

            // panel to hold add/view cart buttons centered with spacing
            productButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            productButtonsPanel.setBackground(new Color(70, 63, 58));
            productButtonsPanel.add(addToCartButton);
            productButtonsPanel.add(viewCartButton);

            // panel to hold back button for cart view
            cartButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            cartButtonsPanel.setBackground(new Color(70, 63, 58));
            cartButtonsPanel.add(backButton);

            // action for add to cart button
            addToCartButton.addActionListener(e -> {
                if (currentProduct != null) {
                    System.out.println("Adding to cart: " + currentProduct.getName());
                    CartManager.instance().addProduct(currentProduct.getName(), 1);  // add one unit of product to cart
                    productInfoPanel.add(createLabel("Added:", " 1 x " + currentProduct.getName() + " to cart"));  // show feedback
                    productInfoPanel.revalidate();
                    productInfoPanel.repaint();
                }
            });

            // action for view cart button to switch to cart panel
            viewCartButton.addActionListener(e -> {
                System.out.println("Viewing cart...cart is empty: " + CartManager.instance().cartEmpty());
                showCartPanel();
            });

            // action for back button to return to product info view and reset selection
            backButton.addActionListener(e -> {
                currentProduct = null;
                productInfoPanel.removeAll();
                productInfoPanel.add(createCenteredMessageLabel("Click a product"));
                showProductInfoPanel();
            });

            // initially show product info panel
            showProductInfoPanel();
        }

        // display product info panel with title and buttons
        private void showProductInfoPanel() {
            removeAll();  // clear all components

            JLabel titleLabel = new JLabel("Product Info", SwingConstants.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            add(titleLabel, BorderLayout.NORTH);

            add(productInfoPanel, BorderLayout.CENTER);  // add product info content
            add(productButtonsPanel, BorderLayout.SOUTH);  // add product buttons at bottom

            revalidate();
            repaint();
        }

        // display cart panel with list of items and controls
        private void showCartPanel() {
            removeAll();  // clear all components

            JLabel titleLabel = new JLabel("Cart", SwingConstants.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            add(titleLabel, BorderLayout.NORTH);

            cartPanel.removeAll();  // clear current cart panel items

            // get all products and quantities in cart
            Map<String, Integer> items = CartManager.instance().getStoredProducts();

            if (items.isEmpty()) {
                cartPanel.add(createCenteredMessageLabel("CART IS EMPTY"));  // show message if cart empty
            } else {
                // create UI elements for each product in the cart
                for (Map.Entry<String, Integer> entry : items.entrySet()) {
                    String productName = entry.getKey();
                    int currentQty = entry.getValue();

                    JPanel itemPanel = new JPanel();
                    itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                    itemPanel.setBackground(new Color(70, 63, 58));

                    JLabel nameLabel = new JLabel(productName + ": ");
                    nameLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
                    nameLabel.setForeground(Color.WHITE);

                    // spinner to allow changing quantity with limits from 0 to 100
                    JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(currentQty, 0, 100, 1));
                    qtySpinner.setPreferredSize(new Dimension(60, 25));

                    JButton updateButton = new JButton("Modify");
                    styleButton(updateButton);

                    // action to update or remove product quantity in cart when modify pressed
                    updateButton.addActionListener(e -> {
                        int newQty = (Integer) qtySpinner.getValue();
                        if (newQty <= 0) {
                            CartManager.instance().removeProduct(productName, currentQty);
                        } else {
                            CartManager.instance().removeProduct(productName, currentQty);
                            CartManager.instance().addProduct(productName, newQty);
                        }
                        showCartPanel();  // refresh cart display
                    });

                    // add components to the item panel
                    itemPanel.add(nameLabel);
                    itemPanel.add(qtySpinner);
                    itemPanel.add(updateButton);

                    cartPanel.add(itemPanel);  // add item panel to cart panel
                }
            }

            // update and display total cart value
            double total = CartManager.instance().cartTotalValue();
            cartTotalLabel.setText("Total: $" + String.format("%.2f", total));
            cartTotalLabel.setFont(new Font("Courier New", Font.BOLD, 18));
            cartTotalLabel.setForeground(Color.WHITE);
            cartTotalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
            cartTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cartPanel.add(cartTotalLabel);

            add(cartScrollPane, BorderLayout.CENTER);  // add scrollable cart panel

            cartButtonsPanel.removeAll();
            cartButtonsPanel.add(backButton);
            cartButtonsPanel.add(purchaseCartButton);
            add(cartButtonsPanel, BorderLayout.SOUTH);  // add cart action buttons at bottom

            revalidate();
            repaint();
        }

        // show details of a selected product on the info panel
        public void showProductInfo(Product product) {
            productInfoPanel.removeAll();

            productInfoPanel.add(createLabel("Name:", product.getName()));
            productInfoPanel.add(createWrappedLabel("Description:", product.getDescription()));
            productInfoPanel.add(createLabel("Price:", "$" + String.format("%.2f", product.getBulkPrice())));
            productInfoPanel.add(createLabel("Quantity:", String.valueOf(product.getQuantityInBox())));
            productInfoPanel.add(createLabel("Storage Type:", product.getStorageType().name()));

            productInfoPanel.revalidate();
            productInfoPanel.repaint();
            currentProduct = product;
            System.out.println("showing product info for: " + product.getName());

            showProductInfoPanel();
        }

        // helper method to create a label with bold key and value text
        private JLabel createLabel(String key, String value) {
            String html = "<html><span style='font-weight:bold'>" + key + "</span> " + value + "</html>";
            JLabel label = new JLabel(html);
            label.setFont(new Font("Courier New", Font.PLAIN, 18));
            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            return label;
        }

        // helper method to create a wrapped label with fixed width for long text
        private JLabel createWrappedLabel(String key, String value) {
            String html = "<html><body style='width:250px'><span style='font-weight:bold'>"
                    + key + "</span> " + value + "</body></html>";
            JLabel label = new JLabel(html);
            label.setFont(new Font("Courier New", Font.PLAIN, 18));
            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            return label;
        }

        // helper method to create a centered message label with white bold text
        private JLabel createCenteredMessageLabel(String message) {
            JLabel label = new JLabel(message, SwingConstants.CENTER);
            label.setFont(new Font("Courier New", Font.BOLD, 18));
            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            return label;
        }

        // helper method to style buttons with consistent font, color, padding, and border
        private void styleButton(JButton button) {
            button.setFont(new Font("Courier New", Font.BOLD, 16));
            button.setBackground(new Color(90, 80, 75));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        }

        // getter for the purchase button (possibly for external event handling)
        JButton getPurchaseCartButton() {
            return purchaseCartButton;
        }
    }
