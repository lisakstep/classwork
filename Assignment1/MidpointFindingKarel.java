/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 * 
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		
		fillRowWithBeepers();
		removeOuterBeepersLeavingOne();
		
	}

	private void removeOuterBeepersLeavingOne() {
		
		removeLeftEdgeBeeper();
		
		removeRightEdgeBeeper();
		
		removeOuterBeepersUntilGone();
		
		dropMidpointBeeper();
			
		
	}

private void dropMidpointBeeper() {
	//reposition Karel if necessary
	turnAround();
	if (frontIsClear() ) {
		move();
		}
	putBeeper();		
	}

private void removeOuterBeepersUntilGone() {
		// remove beepers until none left
	turnAround();
		if (frontIsClear() ) {
		move();
		}
		while (beepersPresent() ) {
			removeLeftmostBeeper();
			removeRightmostBeeper();
			//move back over the space left adjacent to the one Karel just picked up
			if (frontIsClear() ) {
				move();
				}
			}
		
	}

private void removeRightmostBeeper() {
	// move just past the rightmost beeper, then move back on it
	move();
	while (beepersPresent() ) {
		move();
	}
	turnAround();
	move();
	// if there is a beeper, pick the rightmost beeper up
	if (beepersPresent() ) {
		pickBeeper();
	}
}

private void removeLeftmostBeeper() {
	// move just past the leftmost beeper, then move back on it
	move();
	while (beepersPresent() ) {
		move();
	}
	turnAround();
	move();
	// if there is a beeper, pick the leftmost beeper up
	if (beepersPresent() ) {
		pickBeeper();
	}
}

//at exit, Karel faces east
private void removeRightEdgeBeeper() {
	//get to the rightmost beeper and remove it
	while (frontIsClear() ) {
		move();
	}
	if (beepersPresent() ) {
		pickBeeper();
		}
	}

//at exit, Karel faces east
private void removeLeftEdgeBeeper() {
	turnAround();
	while (frontIsClear() ) {
		move();
	}
	if (beepersPresent() ) {
		pickBeeper();
	}	
	turnAround();
	}


		

/*
 * Karel starts in the far left position facing east
 * Karel ends in the far right position	facing east
 */
	private void fillRowWithBeepers() {
		// put beepers in every spot of the bottom row
		while (frontIsClear() ) {
			putBeeper();
			move();
		}
		putBeeper();
	}

}
