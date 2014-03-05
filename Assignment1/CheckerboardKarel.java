/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		//make the first row which is always odd numbered and east-facing
		createInitialCheckeredRow();
		turnLeft();
		
		while (frontIsClear() ) {
			makeEvenNumberedRow();
			makeOddNumberedRow();			
		}

}				
		
	


	private void makeOddNumberedRow() {
		// TODO Auto-generated method stub
		if (frontIsClear() ){
			moveUpAfterWestFacingRow();
		//facing east
		createGenericCheckeredRow();
		turnLeft();
		}
		
	}



	private void makeEvenNumberedRow() {
		// TODO Auto-generated method stub
		if (frontIsClear() ){
			moveUpAfterEastFacingRow();	
		//facing west
		createGenericCheckeredRow();
		turnRight();
		}
	
	}




	private void createGenericCheckeredRow() {
		// Karel is facing either east or west
		//determine if current square needs a beeper by checking square underneath
		if (facingEast() ) {
			turnRight();
			move();
				if (beepersPresent() ) {
					//return to start of row
					turnAround();
					move();
					turnRight();
					//leave blank space
					move();
					createInitialCheckeredRow();
				}
				else {
					turnAround();
					move();
					turnRight();
					//start row with a beeper
					createInitialCheckeredRow();

				}
		}
		else  {//facing west
			turnLeft();
			move();
			if (beepersPresent() ) {
				//return to start of row
				turnAround();
				move();
				turnLeft();
				//start row with a blank space
				//if room, advance and drop beeper
				if (frontIsClear() ) {
					move();
					createInitialCheckeredRow();
				}
			}
			else {
				turnAround();
				move();
				turnLeft();
				//start row with a beeper
				createInitialCheckeredRow();

			}
		}
	}





	private void moveUpAfterWestFacingRow() {
		// move up and orient facing north to ready for creating new row
		move();		
		turnRight();
	}

	private void moveUpAfterEastFacingRow() {
		// move up and orient facing north to ready for creating new row
		move();		
		turnLeft();
	}

	private void createInitialCheckeredRow() {
		// start by laying the first beeper
		putBeeper();
		paintCorner(MAGENTA);

		while (frontIsClear() ) {
			move();
			if (frontIsClear() ) {
				move();
				putBeeper();
				paintCorner(BLACK);

			}
		
		}
		
	}	
}
