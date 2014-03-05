/*
 * File: Artistry.java
 * Name:
 * Section Leader:
 * ==========================================================
 * Replace these comments with a description of your program.
 * Since this program is more freeform than the rest, tell us
 * a bit about it in these comments!
 */
import acm.program.*;
import acm.graphics.*;
import java.awt.*;



public class Artistry extends GraphicsProgram {
	public void run() {
		/* You fill this in.  Remember that you must have
		 * 
		 * 1. At most twenty GObjects,
		 * 2. At least one filled object,
		 * 3. At least two different colors of objects, and
		 * 4. At least three different types of objects (not
		 *    counting the GLabel you use to sign your name).
		 * 
		 * Also, be sure to sign your name in the bottom-right
		 * corner of the canvas.
		 */
		int RECT_HEIGHT = 50;
		int RECT_WIDTH = 120;

		
		int centerX = getWidth()/2;
		//int centerY = getHeight()/2;
		
		int x = centerX - RECT_WIDTH/2;
		//int y = centerY - RECT_HEIGHT/2;
		
		//top rectangle
		GRect headRect = new GRect( x, (getHeight()/3), RECT_WIDTH, RECT_HEIGHT);
		headRect.setFilled(true);
		headRect.setFillColor(Color.yellow);
		headRect.setColor(Color.yellow);
		add(headRect);
		GLabel headRectLable = new GLabel ("Top Box", x + RECT_WIDTH/3,(getHeight()/3) + RECT_HEIGHT/2 );
		add(headRectLable);
		
		GRect secondRect = new GRect( x, (2*(getHeight()/3) - RECT_HEIGHT), RECT_WIDTH, RECT_HEIGHT);
		secondRect.setFilled(true);
		secondRect.setFillColor(Color.GREEN);
		add(secondRect);
		
		GRect firstRect = new GRect( x - 2*RECT_WIDTH, (2*(getHeight()/3) - RECT_HEIGHT), RECT_WIDTH, RECT_HEIGHT);
		firstRect.setFilled(true);
		firstRect.setFillColor(Color.cyan);
		add(firstRect);
		
		GRect thirdRect = new GRect( x + 2*RECT_WIDTH, (2*(getHeight()/3) - RECT_HEIGHT), RECT_WIDTH, RECT_HEIGHT);
		thirdRect.setFilled(true);
		thirdRect.setFillColor(Color.orange);
		add(thirdRect);
		
		GLine diagonal1 = new GLine(0, 0, getWidth(), getHeight());
		diagonal1.setColor(Color.red);

		add(diagonal1);
		
		GLine diagonal2 = new GLine (0, getHeight(), getWidth(), 0);
		diagonal2.setColor(Color.red);
		add(diagonal2);

		
		GLabel myName = new GLabel("Lisa, the pretty, pretty princess", .75*getWidth(), getHeight());
		myName.setFont(Font.SANS_SERIF);
		myName.setColor(Color.blue);
		add(myName);
	}
}
