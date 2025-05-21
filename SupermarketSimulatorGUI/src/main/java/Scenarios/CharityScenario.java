package main.java.Scenarios;

import main.java.GameState;
import main.java.Interfaces.Scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static main.java.Printers.Printer.*;

/**
 * @author prisha, sujal
 */
public class CharityScenario implements Scenario {

    @Override
    public void execute(String product) {

        //Donation Reason
        List<String> donationReasons = new ArrayList<>(Arrays.asList(
                "they wanted to feel good about themselves before buying 12 packets of chocolate",
                "their partner told them they're selfish and customer got insecure",
                "they misread the promotion and thought donations came with a prize",
                "they thought it was expired but it wasn’t",
                "they watched a charity ad on YouTube 5 minutes ago",
                "they were guilt-tripped by a kid with a donation box at the entrance",
                "they said 'tax write-off' and walked off",
                "they didn’t like the new packaging design"
        ));

        //Donation outcome
        List<String> sneakyDonation = new ArrayList<>(Arrays.asList(
                "The store kindly accepts the donation... back into its own shelves.",
                "Management decided the best charity is boosting stock levels.",
                "Turns out the donation box leads directly to aisle 3.",
                "Charity begins at home — and apparently ends at the checkout.",
                "Corporate thanks you for the free restock.",
                "But all donations go straight back into store stock!",
                "Their donation is now back on the shelf for sale.",
                "However, all donations are returned to the store to be sold, so you now have more stock."
        ));

        //Customer reaction
        List<String> customerReactions = new ArrayList<>(Arrays.asList(
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
        ));

        Random random = new Random();
        int donatedQuantity = 1 + random.nextInt(5); //get random quantity 1-5

        //get random string
        String reason = donationReasons.get(random.nextInt(donationReasons.size()));
        String sneaky = sneakyDonation.get(random.nextInt(sneakyDonation.size()));
        String reaction = customerReactions.get(random.nextInt(customerReactions.size()));

        //donation
        printWithDelay("\n[CHARITY DONATION]", SMALL_DELAY);
        printWithDelay("\nDonated: " + donatedQuantity + "x " + product, SMALL_DELAY);
        printWithDelay("\nReason: " + reason, SMALL_DELAY);
        printWithDelay("\nOutcome: " + sneaky, SMALL_DELAY);


        //customer realises their donation is on the shelf
        boolean customerNotices = random.nextBoolean();
        if (customerNotices) {
            printWithDelay("\n[CHARITY CUSTOMER RETURNS] ", SMALL_DELAY);
            printWithDelay("\nThe same customer spots their donated " + product + " on the shelf... for full price.", SMALL_DELAY);
            printWithDelay("\nReaction: " + reaction, SMALL_DELAY);
        } else {
            printWithDelay("\nResult: Customer left smiling, unaware their donation became tomorrow’s stock.", SMALL_DELAY);
        }
        GameState.instance().getInventoryManager().addToInventory(product, donatedQuantity);
    }
    @Override
    public boolean needsProduct() {
        return true;
    }
}
