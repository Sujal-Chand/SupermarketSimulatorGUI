package com.pdcgame.Enums;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;

import java.util.function.Supplier;
import com.pdcgame.Scenarios.BuildingMalfunctionScenario;
import com.pdcgame.Scenarios.CharityScenario;
import com.pdcgame.Scenarios.DefectiveProductScenario;
import com.pdcgame.Scenarios.EconomicDownturnScenario;
import com.pdcgame.Scenarios.EmployeeErrorScenario;
import com.pdcgame.Scenarios.LongLineScenario;
import com.pdcgame.Scenarios.NoParkingScenario;
import com.pdcgame.Scenarios.ProductExpiryScenario;
import com.pdcgame.Scenarios.QuietScenario;
import com.pdcgame.Scenarios.RobberyScenario;
import com.pdcgame.Scenarios.SaleScenario;
import com.pdcgame.Scenarios.WeatherScenario;

/**
 * @author prisha, sujal
 */
//generate the scenario types with their values
public enum ScenarioType {
    SALE(70, 55, 35, SaleScenario::new, false, false),
    ROBBERY( 1, 3, 6, RobberyScenario::new, false, false),
    EMPLOYEE_ERROR( 3, 5,7, EmployeeErrorScenario::new, false, false),
    PRODUCT_EXPIRY( 2,4,6, ProductExpiryScenario::new, false, false),
    ECONOMIC_DOWNTURN( 1,3,5,EconomicDownturnScenario::new, false, true),
    WEATHER(2,3,4, WeatherScenario::new, false, true),
    QUIET( 5,3,2, QuietScenario::new, true, true),
    BUILDING_ISSUE(1,2,4, BuildingMalfunctionScenario::new, true, true),
    DEFECTIVE_PRODUCT(2,3,5, DefectiveProductScenario::new, false, false),
    CHARITY(3,2,1, CharityScenario::new, false, false),
    NO_PARKING(2,3,5, NoParkingScenario::new, false, false),
    LONG_LINE(2,4,6, LongLineScenario::new, false, false);

    private int weight;
    private final Supplier<Scenario> supplier;
    private final boolean uniquePerHour;
    private final boolean uniquePerDay;
    private final GameState gameInstance = GameState.instance();

    ScenarioType(int easy, int normal, int hard, Supplier<Scenario> supplier, boolean uniquePerHour, boolean uniquePerDay) {
        difficultySetWeight(easy, normal, hard);
        this.supplier = supplier;
        this.uniquePerHour = uniquePerHour;
        this.uniquePerDay = uniquePerDay;
    }

    //returns the current weight of a scenario
    public int getWeight() {
        return weight;
    }

    //used to change chance of scenario appearing at runtime
    public void setWeight(int newWeight) {
        this.weight = Math.max(0, newWeight);  //prevents negative weights
    }

    //creates the scenario
    public Scenario createScenario() {
        return supplier.get();
    }

    //will decide if it appears once per hour
    public boolean isUniquePerHour() {
        return uniquePerHour;
    }

    //will decide if it appears once during a day
    public boolean isUniquePerDay() {
        return uniquePerDay;
    }

    //sets weight of scenarios based off difficulty
    public void difficultySetWeight (int easy, int normal, int hard) {
        switch (gameInstance.getDifficulty()) {
            case Easy -> setWeight(easy);
            case Normal -> setWeight(normal);
            case Hard -> setWeight(hard);
        }
    }
}