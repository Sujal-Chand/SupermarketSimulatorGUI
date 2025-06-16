/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.pdcgame.Managers;


import com.pdcgame.Enums.Difficulty;
import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.GameState;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class ScenarioManagerTest {

    private ScenarioManager scenarioManager;

    @Before
    public void setUp() {
        scenarioManager = new ScenarioManager();
        GameState.instance().setDifficulty(Difficulty.Normal);
    }

    @Test
    public void testGenerateScenariosForHourAddsEvents() throws Exception {
        // access the private generateScenariosForHour() method
        Method generateMethod = ScenarioManager.class.getDeclaredMethod("generateScenariosForHour");
        generateMethod.setAccessible(true);

        // call the method
        generateMethod.invoke(scenarioManager);

        // access the selectedScenarios field
        Field selectedField = ScenarioManager.class.getDeclaredField("selectedScenarios");
        selectedField.setAccessible(true);

        @SuppressWarnings("unchecked")
        List<ScenarioType> selectedScenarios = (List<ScenarioType>) selectedField.get(scenarioManager);

        // check the list is not empty and has the expected range
        assertNotNull("Selected scenarios should not be null", selectedScenarios);
        assertTrue("Should have at least 1 scenario", selectedScenarios.size() >= 1);
        assertTrue("Should not exceed 15 scenarios", selectedScenarios.size() <= 15);

    }
}