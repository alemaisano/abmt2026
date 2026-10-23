package org.eth.week4.exercises;

import org.eth.week4.exercises.counter.MyControlerListener;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

public class RunSimulation {
    public static void main (String[] args) {
  
// Scenario: Sioux Falls example project

        // Load the config object
        String configPath = args[0];
        Config config = ConfigUtils.loadConfig(configPath);

        // Set config options
        config.controller().setOutputDirectory("output_week4");

        // Run only iteration 0
        config.controller().setLastIteration(0);

        // Load the scenario object
        Scenario scenario = ScenarioUtils.loadScenario(config);

        // Create the controller object
        Controler controler = new Controler(scenario);

        // Add controller listener
        controler.addControllerListener(new MyControlerListener(scenario));

        // Run the simulation
        controler.run();
    }
}