/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* Wipe the canvas clean then put the scaffold centered 45/100 from top to bottom and 
		 * center the rope. Adjust the beam and scaffold accordingly. This leaves room for
		 * word and guessed-letter info along the bottom of the canvas */
		removeAll();
		setBackground(Color.CYAN);
		GLine scaffold = new GLine( (getWidth()/2 - BEAM_LENGTH), (getHeight()*.45 - SCAFFOLD_HEIGHT/2), (getWidth()/2 - BEAM_LENGTH), (getHeight()*.45 + SCAFFOLD_HEIGHT/2) );
		add(scaffold);
		GLine beam = new GLine( (getWidth()/2 - BEAM_LENGTH), (getHeight()*.45 - SCAFFOLD_HEIGHT/2), getWidth()/2, (getHeight()*.45 - SCAFFOLD_HEIGHT/2) );
		add(beam);
		double bottomOfRope = (getHeight()*.45 - SCAFFOLD_HEIGHT/2) + ROPE_LENGTH;
		GLine rope = new GLine(getWidth()/2, (getHeight()*.45 - SCAFFOLD_HEIGHT/2), getWidth()/2, bottomOfRope);
		add(rope);
	}

	/**
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		/* You fill this in */
		// Set the origin of the Current Word label
		double x = getWidth()*.1;
		double y = (SCAFFOLD_HEIGHT + (getHeight() - SCAFFOLD_HEIGHT)*.70);
		// Remove the old label if there is one
		GObject oldLabel = getElementAt( x,  y);
		if (oldLabel != null){
			remove (oldLabel);
		}
		// Display the updated Current Word
		GLabel label = new GLabel(word);
		label.setFont("SansSerif-bold-18");
		add(label, x, y);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 * Body parts are: 1) Head 2) Body 3) Left arm
	 * 4) Right arm 5) Left leg 6) Right leg 7) Left foot 8) Right foot
	 */
	public void noteIncorrectGuess(String letter, int wrongGuessCount) {
		double bottomOfRope = (getHeight()*.45 - SCAFFOLD_HEIGHT/2) + ROPE_LENGTH;
		double bottomOfBody = bottomOfRope + 2*HEAD_RADIUS + BODY_LENGTH;

		// Add the incorrectly guessed letter to the row of letters under the Current Word
		writeIncorrectLetter(letter);

		// Graphically define the eight body parts
		// Define the head
		GOval head = new GOval( (getWidth()/2 - HEAD_RADIUS), bottomOfRope, 2*HEAD_RADIUS, 2*HEAD_RADIUS);

		// Define the body
		GLine body = new GLine(getWidth()/2, bottomOfRope + 2*HEAD_RADIUS, getWidth()/2, bottomOfRope + 2*HEAD_RADIUS + BODY_LENGTH);

		// Define the left arm
		GLine upperLeftArm = new GLine(getWidth()/2, bottomOfRope + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth()/2 - UPPER_ARM_LENGTH , bottomOfRope + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine lowerLeftArm = new GLine(getWidth()/2 - UPPER_ARM_LENGTH, bottomOfRope + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth()/2 - UPPER_ARM_LENGTH, bottomOfRope + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);

		// Define the right arm
		GLine upperRightArm = new GLine(getWidth()/2, bottomOfRope + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth()/2 + UPPER_ARM_LENGTH, bottomOfRope + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
		GLine lowerRightArm = new GLine(getWidth()/2 + UPPER_ARM_LENGTH, bottomOfRope + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth()/2 + UPPER_ARM_LENGTH, bottomOfRope + 2*HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);

		// Define the left leg
		GLine upperLeftLeg = new GLine(getWidth()/2, bottomOfBody, getWidth()/2 - HIP_WIDTH, bottomOfBody);
		GLine lowerLeftLeg = new GLine(getWidth()/2 - HIP_WIDTH, bottomOfBody, getWidth()/2 - HIP_WIDTH, bottomOfBody + LEG_LENGTH);

		// Define the right leg
		GLine upperRightLeg = new GLine(getWidth()/2, bottomOfBody, getWidth()/2 + HIP_WIDTH, bottomOfBody);
		GLine lowerRightLeg = new GLine(getWidth()/2 + HIP_WIDTH, bottomOfBody, getWidth()/2 + HIP_WIDTH, bottomOfBody + LEG_LENGTH);

		// Define the left foot
		GLine leftFoot = new GLine(getWidth()/2 - HIP_WIDTH, bottomOfBody + LEG_LENGTH, getWidth()/2 - HIP_WIDTH - FOOT_LENGTH, bottomOfBody + LEG_LENGTH);

		// Define the right foot
		GLine rightFoot = new GLine(getWidth()/2 + HIP_WIDTH, bottomOfBody + LEG_LENGTH, getWidth()/2 + HIP_WIDTH + FOOT_LENGTH, bottomOfBody + LEG_LENGTH);

		// Draw the appropriate body parts based on wrongGuessCount
		switch (wrongGuessCount)	{
		case (1) :	
			add(head);
		break;
		case (2) :	
			add(body);
		break;	
		case (3) :	
			add(upperLeftArm);
		add(lowerLeftArm);
		break;
		case (4) :	
			add(upperRightArm);
		add(lowerRightArm);
		break;
		case (5) :	
			add(upperLeftLeg);
		add(lowerLeftLeg);
		break;	
		case (6) :	
			add(upperRightLeg);
		add(lowerRightLeg);
		break;
		case (7) :	
			add(leftFoot);
		break;
		case (8) :	
			add(rightFoot);
		break;
		}
	}

	private void writeIncorrectLetter(String letter) {
		// Set the origin of the Incorrect letters label
		double x = getWidth()*.1;
		double y = (SCAFFOLD_HEIGHT + (getHeight() - SCAFFOLD_HEIGHT)*.85);
		// Look for previous incorrect letters in order to not overwrite them
		while (true) {
			GObject oldLabel = getElementAt( x,  y);
			if (oldLabel != null){
				double width = oldLabel.getWidth();
				x = x + width;
			}
			else break;
		}
		// Add the newest incorrect letter to the line of incorrect letters
		GLabel label = new GLabel(letter + " ");
		label.setFont("SansSerif-*-12");
		add(label, x, y);

	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
