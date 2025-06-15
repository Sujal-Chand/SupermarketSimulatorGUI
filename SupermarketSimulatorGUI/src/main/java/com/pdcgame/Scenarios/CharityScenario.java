package com.pdcgame.Scenarios;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author prisha, sujal
 */
public class CharityScenario implements Scenario {
    private final List<String> messages = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public void execute(String product) {
        messages.clear();

        List<String> donationReasons = Arrays.asList(
            "they wanted to feel good about themselves before buying 12 packets of chocolate",
            "their partner told them they're selfish and customer got insecure",
            "they misread the promotion and thought donations came with a prize",
            "they thought it was expired but it wasn’t",
            "they watched a charity ad on YouTube 5 minutes ago",
            "they were guilt-tripped by a kid with a donation box at the entrance",
            "they said 'tax write-off' and walked off",
            "they didn’t like the new packaging design"
        );

        List<String> sneakyDonation = Arrays.asList(
            "The store kindly accepts the donation... back into its own shelves.",
            "Management decided the best charity is boosting stock levels.",
            "Turns out the donation box leads directly to aisle 3.",
            "Charity begins at home — and apparently ends at the checkout.",
            "Corporate thanks you for the free restock.",
            "But all donations go straight back into store stock!",
            "Their donation is now back on the shelf for sale.",
            "However, all donations are returned to the store to be sold, so you now have more stock."
        );

        List<String> customerReactions = Arrays.asList(
            "\"Wait... is that my donation back on the shelf?\" they whisper, suspiciously.",
            "They stare blankly at the shelf, then slowly back away.",
            "\"I'm pretty sure I just donated that...\" they mutter, looking betrayed.",
            "They squint, check the barcode, and look mildly offended.",
            "\"Must be a different box... right?\" they lie to themselves.",
            "They laugh nervously and pretend not to notice.",
            "\"I feel scammed, but it *is* for a good cause... I think.\"",
            "They look around for cameras, convinced it’s a prank show.",
            "They sigh and say, \"Well, at least someone will buy it... again.\"",
            "\"Is this... store charity laundering?\" they joke to the cashier.",
            "They take a picture of the shelf and storm out dramatically.",
            "They slowly nod in acceptance, whispering, \"Capitalism wins again.\"",
            "\"Did I just give the store free stock? ... Yep.\"",
            "They shrug and say, \"That's on me, I guess.\"",
            "The customer looks puzzled, shrugs, and walks away.",
            "\"Wait a second... didn't I just donate that?\" the customer mutters, visibly annoyed."
        );

        int donatedQuantity = 1 + random.nextInt(5);
        String reason = donationReasons.get(random.nextInt(donationReasons.size()));
        String sneaky = sneakyDonation.get(random.nextInt(sneakyDonation.size()));
        String reaction = customerReactions.get(random.nextInt(customerReactions.size()));

        // Store messages instead of printing them
        messages.add("[CHARITY DONATION]");
        messages.add("Donated: " + donatedQuantity + "x " + product);
        messages.add("Reason: " + reason);
        messages.add("Outcome: " + sneaky);

        boolean customerNotices = random.nextBoolean();
        if (customerNotices) {
            messages.add("[CHARITY CUSTOMER RETURNS]");
            messages.add("The same customer spots their donated " + product + " on the shelf... for full price.");
            messages.add("Reaction: " + reaction);
        } else {
            messages.add("Result: Customer left smiling, unaware their donation became tomorrow’s stock.");
        }

        GameState.instance().getInventoryManager().addToInventory(product, donatedQuantity);
    }

    @Override
    public boolean needsProduct() {
        return true;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
