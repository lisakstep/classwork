/**
 * File: NameCounts
 * Asks the user to input names then when input is complete, prints to the console
 * the names entered and the number of times they were entered
 * Practice using HashMap
 * Solution to Stanford CS106a Exercise 6
 * 
 */

/**
 * @author Lisa Stephens
 * completed March 29, 2014
 *
 */

import java.util.*;

import acm.program.*;


@SuppressWarnings("serial")
public class NameCounts extends ConsoleProgram {

	public void run() {
		names = new HashMap<String, Integer>();
		getNames();
		printNamesAndCounts();
	}

	private void printNamesAndCounts() {
		// Print out the entire names HashMap which shows all the names the user entered and the
		// number of times they were entered
		for (String name:names.keySet())
		{
			Integer count = names.get(name);
			println("Entry [" + name + "] is found " + count + " time(s).");
		}
	}

	private void getNames() {
		// Get names from the user and add to the names HashMap
		while (true) {
			String name = readLine("Please enter a one-word name: ");
			if (name.equals("")) {
				break;
			}
			// record the name and count the number of times it was entered
			recordNameUpdateCount(name);
		}
	}
	private void recordNameUpdateCount(String name) {
		// Process name appropriately
		if (!(names.containsKey(name)) ){
			// If the name is not already recorded, add it to the HashMap and set the count to 1
			names.put(name, 1);
		}
		else {
			// If the name is already in the HashMap, add 1 to the count
			Integer count = names.get(name);
			count++;
			// Remove the old entry and replace it with the same name and the updated count
			names.remove(name);
			names.put(name, count);
		}
	}

	/* Private instance variables */
	private HashMap<String, Integer> names;

}
