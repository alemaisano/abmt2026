package org.eth.week3.exercises;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.api.core.v01.Id;

public class RunSimulationObjects {

    public static void main (String[] args) {
      
    //Scenario: Siouxfalls example project
            
        //Load the config object
        String configPath = "/home/kaghog/git/abmt-2026/abmt2026/scenarios/SiouxFalls/config_default.xml"; //you can also directly add the config path here
        Config config = ConfigUtils.loadConfig(configPath);
        
        //We can access and change the config file directly here, change the iteration number or even the output directory
        System.out.println("********************************************************************************");
        System.out.println("********************************************************************************");
        System.out.println("the old last iteration setting is: " + config.controller().getLastIteration());
        
        config.controller().setLastIteration(3);
    
        
        System.out.println("the new last iteration setting is: " + config.controller().getLastIteration());

        config.controller().setOutputDirectory("output3");

        //ToDo: how can we change the flow capacity?
        config.qsim().setFlowCapFactor(0.5);
        config.qsim().setStorageCapFactor(0.5);
        
        //ToDo: Load the scenario object

        Scenario scenario = ScenarioUtils.loadScenario(config);
        
        
        //Let's access modify input files that make up the scenario
        for (Person person : scenario.getPopulation().getPersons().values()) {
            Id<Person> id = person.getId();
            System.out.println("Person ID: " + id);
            break;
        }

        //Create the controller object
        Controler controller = new Controler(scenario);
        
        //We can modify the config even after loading it in the scenario by calling it through the controler
        //it depends on where you are. You would not want to modify the config using the previously created config object
        //now because it has already been loaded into the scenario
        
        controller.getConfig().controller().setLastIteration(0);

        config.controller().setLastIteration(2);

        controller.run();
        }
    }
