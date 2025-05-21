package main.java.Finders;

import main.java.GameState;
import main.java.Interfaces.ObjectFinder;
import main.java.ProductTypes.Product;
import main.java.ProductTypes.PurchasableProduct;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author prisha, sujal
 */
public class FindProductName implements ObjectFinder<String> {
    private final GameState gameInstance = GameState.instance();
    private Collection<PurchasableProduct> searchCollection = gameInstance.getProductManager().getPurchasableProducts();

    public void changeSearchCollection(Collection<PurchasableProduct> searchCollection) {
        this.searchCollection = searchCollection;
    }

    @Override
    public String find(String input) {
        String bestMatch = null;
        double highScore = 0;
        input = input.toLowerCase().trim()
                .replaceAll("\\s+", "")
                .replaceAll("[^a-zA-Z0-9]", "");

        // convert input to a set of characters
        Set<Character> searchChars = new HashSet<>();
        for (char c : input.toCharArray()) {
            searchChars.add(c);
        }

        for (PurchasableProduct product : searchCollection) {
            String productName = product.getName();
            String currentName = productName.toLowerCase().trim()
                    .replaceAll("\\s+", "")
                    .replaceAll("[^a-zA-Z0-9]", "");

            if (isExactMatch(productName, input)) return productName;


            double score = 0;
            int matchCount = 0;

            // weighted scoring if part of the input is in product name, or vice versa
            if (currentName.contains(input)) score += 12;
            if (input.contains(currentName)) score += 10;

            // convert current product name to a set of characters
            Set<Character> nameChars = new HashSet<>();
            for (char c : currentName.toCharArray()) {
                nameChars.add(c);
            }
            // see how many characters in the product name match with input
            for (char c : searchChars) {
                if (nameChars.contains(c)) matchCount = matchCount + 5;
            }

            // calculate match score
            double matchPercent = (double) matchCount / Math.max(input.length(), currentName.length());
            score += Math.min(input.length(), currentName.length()) * matchPercent;
            score -= 0.3 * Math.abs(input.length() - currentName.length()); // reduce score by difference in input string and current product selected

            // check for new high score
            if (score > highScore) {
                highScore = score;
                bestMatch = productName;
            }
        }
        return bestMatch;
    }

    // returns true if the input string matches exactly with the provided product name
    public boolean isExactMatch(String productName, String input) {
        String cleanSearch = input
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "")
                .replaceAll("[^a-zA-Z0-9]", "");

        String cleanProductName = productName
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "")
                .replaceAll("[^a-zA-Z0-9]", "");

        return cleanSearch.equals(cleanProductName);
    }
}
