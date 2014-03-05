/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	public void run() {
		
		//make a centered red and white circle target radius 1 inch
		drawTheBigRedOval();
		drawTheMiddleWhiteOval();
		drawTheCenterRedOval();
		
		
	}

	private void drawTheCenterRedOval() {
		int width = getWidth();
		int centerX = width/2;
		int height = getHeight();
		int centerY = height/2;
		// centered, .3 inch radius
		GOval innerOval = new GOval( (centerX - 72*.3), (centerY - 72*.3), 2*72*.3, 2*72*.3);
		innerOval.setFilled(true);
		innerOval.setFillColor(Color.RED);
		add(innerOval);
	}

	private void drawTheMiddleWhiteOval() {
		int width = getWidth();
		int centerX = width/2;
		int height = getHeight();
		int centerY = height/2;
		// centered, .65 inch radius
		GOval middleOval = new GOval( centerX - (72*.65), centerY - (72*.65), 2*72*.65, 2*72*.65);
		middleOval.setFilled(true);
		middleOval.setFillColor(Color.WHITE);
		add(middleOval);
	}

	private void drawTheBigRedOval() {
		int width = getWidth();
		int centerX = width/2;
		int height = getHeight();
		int centerY = height/2;
		// centered, one inch radius
		GOval outerOval = new GOval( (centerX - 72), (centerY - 72), 2*72, 2*72);
		outerOval.setFilled(true);
		outerOval.setFillColor(Color.RED);
		add(outerOval);
	}
}
