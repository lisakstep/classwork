/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		//Get the starter number
		int initialInteger = readInt("Enter a positive integer for a \"Hailstone\" demonstration.  ");
		int workingNumber = initialInteger;
		int stepCounter = 0;
		if (initialInteger <= 0) {
			println("Sorry, " + initialInteger + " is not a valid positive integer.");
			}
		else {
			while (workingNumber > 1) {
				stepCounter++;
				if ((workingNumber % 2) == 1 ) {
				//if the number is odd
					println( workingNumber + " is odd, so I make 3n + 1 :  " + (workingNumber = (3*workingNumber + 1)));

				}
				else {
				//if the number is even
					println( workingNumber + " is even, so I take half :  " + (workingNumber = ( workingNumber/2 )));

				}
		}
		
		}
		if (workingNumber == 1) {
			println("The process took " + stepCounter + " steps to reach " + workingNumber + " starting from " + initialInteger + ".");
	
		}
	}
}

