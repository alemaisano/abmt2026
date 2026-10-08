package org.eth.week4.exercises;

import org.eth.week4.exercises.counter.MyControlerListener;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

public class RunSimulation {
    public static void main (String[] args) {
  
//Scenario: Siouxfalls example project
    	
        //Load the config object
        String configPath = args[0];
        Config config = ConfigUtils.loadConfig(configPath);

        //set config options like output directory to customise the simulation

        //set the last iteration to 0 so that we only run one iteration


        //Load the scenario object
              
        //Create the controller object
        
        //add controller listener
        //controler.addControllerListener(new MyControlerListener(scenario));
        
        //controler.run();
    }
}

