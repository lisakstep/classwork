/*
 * File: StoneMasonKarel.java
 * --------------------------
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// the main part of the program
	public void run(){
		
	//repair columns until Karel hits a wall	
		while (frontIsClear() ){		
		repairColumn();
		advanceFourSteps();
		}
		//repair final column at wall
		repairColumn();
	}

	private void advanceFourSteps() {
		// move east 4 steps to the next column
		for (int i=0; i<4; i++ ){
			move();
		}
		
	}

	private void repairColumn() {
		// TODO Auto-generated method stub
		faceNorth();
		replaceMissingBeepers();
		returnToColumnBottom();
		faceEast();
	}

	private void faceEast() {
		// TODO Auto-generated method stub
		turnLeft();	
	}

	private void returnToColumnBottom() {
		// TODO Auto-generated method stub
		turnLeft();
		turnLeft();
		while (frontIsClear() ){
			move();
		}
		
	}

	private void replaceMissingBeepers() {
		// climb to top of column replacing empty corners with beepers
		while (frontIsClear() ) {
			if (noBeepersPresent() ) {
			putBeeper();
			move();
			}
			else {
				move();
			}
				}
		//check top of column for missing beeper and replace if necessary
		if (noBeepersPresent() ) {
			putBeeper();
			}

		
	}

	private void faceNorth() {
		// TODO Auto-generated method stub
		turnLeft();
		
	}

}
