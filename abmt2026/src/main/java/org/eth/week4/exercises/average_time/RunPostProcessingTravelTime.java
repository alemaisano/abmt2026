package org.eth.week4.exercises.average_time;

import java.util.Map.Entry;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunPostProcessingTravelTime {

	public static void main(String[] args) {
		
		EventsManager eventsManager = EventsUtils.createEventsManager();
		// create an object for the EventHandler called CalculateAverageTravelTime and add to the event manager
		CalculateAverageTravelTime calculateAverageTravelTime = new CalculateAverageTravelTime();
		eventsManager.addHandler(calculateAverageTravelTime);
				
		// read the events from our events file
		MatsimEventsReader eventsReader = new MatsimEventsReader(eventsManager);
		eventsReader.readFile(args[0]);

		//print out the results of the average travel time and the average travel time per trip leg
		System.out.println("Average Travel Time: " + calculateAverageTravelTime.getAverageTravelTime());
		for (Entry<String, Double> entry : calculateAverageTravelTime.getAverageTravelTimePerMode().entrySet()) {
			System.out.println("Average Travel Time for " + entry.getKey() + ": " + entry.getValue());
		}

	}

}
