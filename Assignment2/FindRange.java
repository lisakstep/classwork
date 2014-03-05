/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	/** Set the SENTINEL to 0 */
	private static final int SENTINEL = 0;

	public void run() {
		println("Enter some integers finishing with " + SENTINEL + " and I'll tell you the lowest and highest.");

		int inputNumber = readInt("? ");
		int lowest = inputNumber;
		int highest = inputNumber;

		while(inputNumber != SENTINEL) {
			inputNumber = readInt("? ");

			if (inputNumber == SENTINEL) {
				break;
			}
			else {
				if (inputNumber < lowest) {
					lowest = inputNumber;
				}
				if (inputNumber > highest) {
					highest = inputNumber;
				}
			}
		}

		if ( (lowest == highest) && (lowest == SENTINEL) ) {
			//First number entered was the Sentinel
			println("You didn't enter any valid integers for me to evaluate.");
		}
		else if (lowest == highest) {
			println("The only number entered was " + lowest + " which makes it both highest and lowest ;)");

		}
		else
		{
			println("The lowest number was " + lowest + " and the highest number was " + highest + ".");
		}
	}
}

