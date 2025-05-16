package com.pdcgame.supermarketsimulatorfinal.Printers;

/**
 * @author prisha, sujal
 */
public class StoreRatingPrinter {

    // five-star rating
    public void fiveStars() {
        String[] starLines = {
                "   __/\\__   __/\\__   __/\\__   __/\\__   __/\\__",
                "   \\    /   \\    /   \\    /   \\    /   \\    /",
                "   /_  _\\   /_  _\\   /_  _\\   /_  _\\   /_  _\\"
        };

        for (String line : starLines) {
            System.out.println(line);
        }
    }

    // individual star
    private String[] star() {
        return new String[] {
                "   __/\\__",
                "   \\    /",
                "   /_  _\\"
        };
    }

    // half a star
    private String[] halfStar() {
        return new String[] {
                "   __/",
                "   \\",
                "   /_"
        };
    }

    // prints the stars representing store rating
    public void printRating(double rating) {
        int fullStars = (int) rating;
        boolean hasHalfStar = (rating - fullStars) >= 0.5;

        String[][] stars = new String[fullStars + (hasHalfStar ? 1 : 0)][];
        for (int i = 0; i < fullStars; i++) {
            stars[i] = star();
        }
        if (hasHalfStar) {
            stars[fullStars] = halfStar();
        }

        for (int line = 0; line < 3; line++) {
            for (String[] s : stars) {
                System.out.print(s[line]);
            }
            System.out.println();
        }
    }

}
