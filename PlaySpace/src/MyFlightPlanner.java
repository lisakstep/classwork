
import java.util.*;
import java.awt.Container;
import java.io.*;

import acm.program.*;
import acm.util.*;

/**
 * File: FlightPlanner.java
 * uses file: FlightPlanner.txt
 */

/**
 * @author Lisa Stephens
 * 
 * This class is my solution to Stanford CS106a exercise 8
 * It reads in a file (FlightPlanner.txt) and then helps a user plan a flight from and to user-selected cities
 * based on available interim flights defined in the text file
 *
 */


public class MyFlightPlanner extends ConsoleProgram {

	public void init() {
		cities = new ArrayList<String>();
		dataFileName = "/Users/lisastephens/Documents/workspace/PlaySpace/src/FlightPlanner.txt";
		readFlightInfo();
		printOutCities();
	}

	public void run() {
		// Get the route from the user and when the destination is the same as the origination,
		// close out and print the final route for the user
		String origCity = readLine("Enter the starting city: ");
		if (cities.contains(origCity) ){
			createItinerary(origCity);
		} else {
			println("Sorry, we don't fly from there.");
		}
		printFinalRoute();
	}

	private void printFinalRoute() {
		// Print out the String containing the final route city sequence
		println("The route you've chosen is: \n" + fullRoute);

	}

	private void createItinerary(String origCity) {
		// Get the information from the user about which cities they want to fly to
		// and store the sequence to be echoed later
		// Stop when the destination city is the same as the origin
		String nextCity = origCity;
		fullRoute = origCity;
		
		while (true)	{
			println("From " + nextCity + " you can fly directly to:");
			printPossibleDestinations(nextCity);
			nextCity = readLine("Where do you want to go from " + nextCity + "? ");
			fullRoute = addCityToItinerary(nextCity, fullRoute);
			if (nextCity.equals(origCity) ) break;
		}
	}

	private String addCityToItinerary(String newCity, String fullRoute) {
		// Add the new city to the String containing the entire city sequence for the flights
		fullRoute = fullRoute + " -> " + newCity;
		return fullRoute;
	}

	private void printPossibleDestinations(String origCity) {
		// Print out the valid destination cities from the specified origin city
		
		for (int i = 0; i< getFlightStart(origCity).size(); i++) {
			println(getFlightStart(origCity).get(i));
		}
	}

	private void printOutCities() {
		// Display the welcome messages on the console
		println("Welcome to Lisa's Flight Planner!");
		println("Here's a list of the " + cities.size() + " cities in our database:");
		// Print out all cities in the cities ArrayList
		for (int i=0; i<cities.size(); i++) {
			println(cities.get(i));
		}
		println("Let's plan a round trip flight.");

	}

	private void readFlightInfo() {
		// Read the data in the FlightPlanner.txt file and save the cities named therein in the cities ArrayList
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader(dataFileName));

			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				findTheCityName(line);
			}
			rd.close();
		} catch (IOException ex){
			System.out.println("bad file");
		}

	}

	private void findTheCityName(String line) {
		// This method takes a single line of text, strips out spaces and "->" and 
		// identifies and saves the city names in the line from the text file
		// Each line has an origin and destination listed in that order
		String delims = "[ ]";
		String arrow = "->";
		String cityName = "";
		String origin = "";
		String destination = "";


		String[] tokens = line.split(delims);

		// Some of the city names are multi-words. Concatenate all words before the arrow into a single
		// city name separated by a space and then do the same for the words after the arrow
		// Only process if the line in the file has more than one "token"
		if (tokens.length > 1) {

			for (int i = 0; i < tokens.length; i++) {

				if ( !(tokens[i].contains(arrow)) ){
					cityName = cityName + tokens[i] + " ";
				}
				else {
					// reached the arrow in the token array, process the first city name
					addCityToList(cityName.trim());
					origin = cityName.trim();
					cityName = "";
				}
			}
			// finished parsing the words in the token array, process the second city name
			addCityToList(cityName.trim());
			destination = cityName;
			// Add route information to the routeInfo HashMap
			getFlightStart(origin).add(destination);

		}
	}

	private ArrayList<String> getFlightStart(String origin) {
		// Return the ArrayList of cities that holds the destinations in the routes Hashmaps. the origin city is the key to the 
		// routes ArrayList
		return routeInfo.get(origin);
	}

	private void addCityToList(String cityName) {
		// Add the city name to the ArrayList cities if it isn't already contained there and isn't all spaces
		if (  !cityName.isEmpty() && !cities.contains(cityName) ) {
			cities.add(cityName);
			routeInfo.put(cityName, new ArrayList<String>());
		}
	}

	/* Private Instance variables  	 */

	private ArrayList<String> cities;
	private String dataFileName;
	private String fullRoute;
	private HashMap <String,ArrayList<String>> routeInfo = new HashMap<String,ArrayList<String>>();
}
