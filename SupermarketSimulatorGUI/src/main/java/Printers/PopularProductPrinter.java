package main.java.Printers;

import main.java.GameState;
import main.java.ProductTypes.PurchasableProduct;

import java.util.Collection;

/**
 * @author prisha, sujal
 */
public class PopularProductPrinter extends Printer {
    private final GameState gameInstance = GameState.instance();
    @Override
    // get the popularProduct collection and print each product in it
    public void printBody() {
        Collection<PurchasableProduct> popularProducts = gameInstance.getProductManager().getPopularProducts();
        System.out.printf("| %-20s |\n", "PRODUCT NAME");

        for(PurchasableProduct purchasableProduct : popularProducts){
            System.out.printf("| %-20s |\n", purchasableProduct.getName());
        }
    }
}
