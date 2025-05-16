package com.pdcgame.supermarketsimulatorfinal.Finders;

import com.pdcgame.supermarketsimulatorfinal.Enums.BoardCell;
import com.pdcgame.supermarketsimulatorfinal.Interfaces.ObjectFinder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author prisha, sujal
 */
public class FindBoardCell implements ObjectFinder<BoardCell> {
    @Override
    public BoardCell find(String input) {
        BoardCell bestMatch = BoardCell.EMPTY;
        double highScore = 0;
        input = input.toLowerCase().trim()
                .replaceAll("\\s+", "")
                .replaceAll("[^a-zA-Z0-9]", "");

        // convert input to a set of characters
        Set<Character> searchChars = new HashSet<>();
        for(char c : input.toCharArray()){
            searchChars.add(c);
        }
        for(BoardCell cell : BoardCell.values()){
            double score = 0;
            int matchCount = 0;
            String currentCell = cell.name().toLowerCase().trim()
                    .replaceAll("\\s+", "")
                    .replaceAll("_", "");

            if (isExactMatch(cell, input)) return cell;

            // weighted scoring if part of the input is in cell, or vice versa
            if (currentCell.contains(input)) score += 12;
            if (input.contains(currentCell)) score += 10;

            // convert current cell to a set of characters
            Set<Character> cellChars = new HashSet<>();
            for (char c : currentCell.toCharArray()){
                cellChars.add(c);
            }
            // see how many characters in the cell match with input
            for (char c : searchChars){
                if (cellChars.contains(c)) matchCount++;
            }

            // calculate match score
            double matchPercent = (double) matchCount / Math.max(input.length(), currentCell.length());
            score += Math.min(input.length(), currentCell.length()) * matchPercent;
            score -= 0.9 * Math.abs(input.length() - currentCell.length()); // reduce score by difference in input string length and current cell name

            // check for new high score
            if (score > highScore) {
                highScore = score;
                bestMatch = cell;
            }
        }
        return bestMatch;
    }

    // returns true if the provided string matches exactly with the name of a BoardCell or the icon of one without special characters
    public boolean isExactMatch(BoardCell cell, String input) {
        String cleanSearch = input
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "")
                .replaceAll("_", "");

        String cleanCell = cell.name()
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "")
                .replaceAll("_", "");

        boolean iconMatch = cell.getIcon()
                .replaceAll("\\s+", "")
                .replaceAll("[^a-zA-Z0-9]", "").equalsIgnoreCase(input);

        boolean nameMatch = cleanSearch.equals(cleanCell);

        return iconMatch || nameMatch;
    }
}
