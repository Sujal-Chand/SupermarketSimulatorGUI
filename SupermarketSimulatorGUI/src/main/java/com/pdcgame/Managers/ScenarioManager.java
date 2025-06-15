package com.pdcgame.Managers;

import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.Interfaces.Scenario;
import com.pdcgame.GameState;
import com.pdcgame.Printers.ScenarioPrinter;

import java.util.*;

/**
 * @author prisha, sujal
 */
public class ScenarioManager {

    private static final Random random = new Random();
    private final List<ScenarioType> selectedScenarios = new ArrayList<>();
    private final Set<ScenarioType> usedToday = new HashSet<>();
    private final GameState gameInstance = GameState.instance();
    private final ScenarioPrinter scenarioPrinter = new ScenarioPrinter();     
    private final List<Scenario> activeScenarios = new ArrayList<>();

    //Opens the store and runs through all the hours ands scenarios within it
    public void runDay() {
        usedToday.clear(); //clears all scenarios used in previous day
        scenarioPrinter.printBanner("STORE OPEN");

        //controls the time
        for (int hour = 9; hour <= 17; hour++) {
            String time = String.format("%02d:00 %s", (hour > 12 ? hour - 12 : hour), hour < 12 ? "AM" : "PM");
            System.out.println("\n\n====== Time: " + time + " ======");
            generateScenariosForHour(); //generates the scenarios for the current hour
            runScenarios(); //runs the scenario
        }

        scenarioPrinter.printBanner("STORE CLOSED");
        gameInstance.getEndOfDayManager().handleEndOfDay(); //displays and carries out end of day methods
    }

    private void generateScenariosForHour() {
        selectedScenarios.clear(); //clears last set
        int numEvents = 5 + random.nextInt(11); //total number of scenarios for the hour (between 5 and 15)
        Set<ScenarioType> addedThisHour = new HashSet<>(); //adds the scenarios for the hour

        int totalWeight = Arrays.stream(ScenarioType.values()).mapToInt(ScenarioType::getWeight).sum(); //sums up the total weights
        while (selectedScenarios.size() < numEvents) {
            int roll = random.nextInt(totalWeight); //random number between 0 and totalWeight
            int cumulative = 0; //finds which scenario it lands on
            //runs through all the scenarios to get the weight
            for (ScenarioType type : ScenarioType.values()) {
                cumulative += type.getWeight(); //adds the weight of the scenario
                if (roll < cumulative) {
                    //if a scenario can only appear once per hour or once per day, and it's already used, skip it
                    if ((type.isUniquePerHour() && addedThisHour.contains(type)) ||
                            (type.isUniquePerDay() && usedToday.contains(type))) {
                        break;
                    }
                    //if the scenario is unique per hour it overrides all others
                    if (type.isUniquePerHour()) {
                        selectedScenarios.clear(); //clears all other scenarios
                        selectedScenarios.add(type); //add single scenario
                        addedThisHour.add(type); //marks it as used for the hour
                        if (type.isUniquePerDay()) usedToday.add(type);
                        return; //ends method
                    }

                    //otherwise all scenarios get added to the list
                    selectedScenarios.add(type);
                    if (type.isUniquePerDay()) usedToday.add(type); //marks it as used for the day
                    break;
                }
            }
        }
    }

    //runs all the selected scenarios and selects a random product for them
    private void runScenarios() {
        //loops through each scenario type chosen for the hour
        for (ScenarioType type : selectedScenarios) {
            Scenario scenario = type.createScenario(); //creates new instance of scenario

            //separate handling for sale scenario
            if (type == ScenarioType.SALE) {
                scenario.execute(getSaleRandomProduct()); //gets a random product using sale specific method and passes it to sale scenario
            }else{
                String product = scenario.needsProduct() ? getRandomProduct(): null; //checks if scenario needs a product
                scenario.execute(product); //gets a random product and passes it to the scenario
            }
        }
    }

    public List<String> runDayAndGetMessages() {
        List<String> allMessages = new ArrayList<>();
        usedToday.clear();

        for (int hour = 9; hour <= 17; hour++) {
            selectedScenarios.clear();
            int numEvents = 5 + random.nextInt(11);
            Set<ScenarioType> addedThisHour = new HashSet<>();
            int totalWeight = Arrays.stream(ScenarioType.values()).mapToInt(ScenarioType::getWeight).sum();

            String time = String.format("============ Time: %02d:00 %s ============",
                    (hour > 12 ? hour - 12 : hour),
                    (hour < 12 ? "AM" : "PM"));
            allMessages.add(time);

            boolean selectedUniquePerHour = false;

            while (selectedScenarios.size() < numEvents && !selectedUniquePerHour) {
                int roll = random.nextInt(totalWeight);
                int cumulative = 0;
                for (ScenarioType type : ScenarioType.values()) {
                    cumulative += type.getWeight();
                    if (roll < cumulative) {
                        if ((type.isUniquePerHour() && addedThisHour.contains(type)) ||
                            (type.isUniquePerDay() && usedToday.contains(type))) {
                            break;
                        }

                        if (type.isUniquePerHour()) {
                            selectedScenarios.clear();
                            selectedScenarios.add(type);
                            addedThisHour.add(type);
                            if (type.isUniquePerDay()) usedToday.add(type);
                            selectedUniquePerHour = true; 
                            break;
                        }

                        selectedScenarios.add(type);
                        if (type.isUniquePerDay()) usedToday.add(type);
                        addedThisHour.add(type);
                        break;
                    }
                }
            }

            for (ScenarioType type : selectedScenarios) {
                Scenario scenario = type.createScenario();
                String product = scenario.needsProduct() ? getRandomProduct() : null;
                scenario.execute(product);

                if (scenario.getMessages() != null) {
                    allMessages.addAll(scenario.getMessages());
                } else {
                    allMessages.add("[Scenario " + type.name() + " executed.]");
                }
            }
        }
        ScenarioType.resetToDefaultWeights();
        return allMessages;
        
    }
    //returns a random product from inventory
    private String getRandomProduct() {
        // this will always return a random product in the store
        return gameInstance.getInventoryManager().getRandomProduct();
    }

    //returns a random product while also considering popular products
    private String getSaleRandomProduct() {
        int popProduct = random.nextInt(5); //20% chance of popular product being chose for sale

        if(popProduct == 0) {
            return gameInstance.getProductManager().getRandomPopularProduct(); //gets random product from popular product list
        }
        return getRandomProduct(); //gets any random product
    }

    // know if product is out of stock
    public boolean outOfStock(String productName) {
         return gameInstance.getFloorStorageManager().totalQuantityOnFloor(productName) == 0;
    }

}