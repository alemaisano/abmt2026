package org.eth.week4.exercises.average_time;

import java.util.Map.Entry;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunPostProcessingTravelTime {

	public static void main(String[] args) {
		
		EventsManager eventsManager = EventsUtils.createEventsManager();
		// create an object for the EventHandler called CalculateAverageTravelTime and add to the event manager
		CalculateAverageTravelTime myEventHandler = new CalculateAverageTravelTime();
		eventsManager.addHandler(myEventHandler);
						
		// read the events from our events file
		MatsimEventsReader matsimEventsReader = new MatsimEventsReader(eventsManager);
		
		//print out the results of the average travel time and the average travel time per trip leg
		System.out.println("This is my total average travel time: " + myEventHandler.getAverageTravelTime());

		for (Entry<String, Double> entry : myEventHandler.getAverageTravelTimePerMode().entrySet()){
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
		
		
				
	}