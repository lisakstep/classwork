/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		
		println("This program computes the hypotenuse of a right triangle given the two sides.");
		println("Enter the values.");
		
		int a = readInt("First side:  ");
		int b = readInt("Second side:  ");
		
		double c = Math.sqrt(a*a + b*b);
		println("The hypotenuse of that triangle is " + c);
	}
}
