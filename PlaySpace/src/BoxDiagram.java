import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


/**
 * 
 * @author Lisa Stephens
 * My solution to the Stanford CS106a Exercise 7
 * Set up an interactive canvas that adds a labeled box to the center of the canvas
 * which allows the user to remove a specified box and move it via mouse click/drag
 *
 * Completed 4/1/2014
 */
public class BoxDiagram extends GraphicsProgram{

	public void init() {
		// Add the textField and buttons to the canvas
		setUpDisplay();
		
		// Add the interactor listeners
		textField.addActionListener(this);
		addActionListeners();
		addMouseListeners();
		
		// Set up the HashMap to track the boxes
		boxSet = new HashMap<String, GCompound>();
	}

	private void setUpDisplay() {
		// TODO Auto-generated method stub
		textField = new JTextField(15);
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		clearButton = new JButton("Clear");
		add(textField, NORTH);
		add(addButton, EAST);
		add(removeButton, EAST);
		add(clearButton, EAST);

	}

	public void actionPerformed(ActionEvent e) {
		// if the add button is pressed, create and draw a rectangle in the center of the canvas 
		// with the word in the textField written in the center of the box
		String cmd = e.getActionCommand();
		String userLabel = textField.getText();
		// Treat enter key the same as the "add" button
		if (e.getSource() == textField) {
			cmd = "Add";
		}
		// Add a box to the canvas and to the boxSet HashMap
		if (cmd ==  "Add" ) {
			GCompound newBox = drawRectangle(userLabel);
			boxSet.put(userLabel, newBox);
		}
		// Remove the box with the name specified in the textField
		if (cmd== "Remove") {
			removeSpecifiedBox(userLabel);
		}
		// Clear the canvas and empty the HashMap of boxes
		if (cmd == "Clear") {
			clearBoxes();
		}
	}

	public void mousePressed(MouseEvent e) {
		// Get ready to move the object
		// Get the x,y coordinate where the mouse was first pressed
		startingPoint = new GPoint(e.getPoint() );
		// Find out which object has been selected via mousePress
		clickedBox = getElementAt(startingPoint );

	}

	public void mouseDragged(MouseEvent e) {
		// Move the selected object to its new location
		if (clickedBox != null) {
			clickedBox.move(e.getX() - startingPoint.getX(), e.getY() - startingPoint.getY());
			startingPoint = new GPoint(e.getPoint() );
		}
	}

	private void clearBoxes() {
		// Erase the boxes from the canvas and delete them all from the HashMap
		for ( String name:boxSet.keySet()) {
			remove(boxSet.get(name));
		}
		boxSet.clear();
	}


	private void removeSpecifiedBox(String userLabel) {
		// Remove the user-specifed box from the canvas and from the HashMap of boxes if it exists
		if (boxSet.containsKey(userLabel)) {
			remove(boxSet.get(userLabel));
			boxSet.remove(userLabel);
		}
	}

	private GCompound drawRectangle(String userLabel) {
		// Make a GCompound instance that consists of a rectangle and a centered user-entered label
		// place the rectangle in the center of the canvas
		GCompound box = new GCompound();
		GRect rectangle = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel rectLabel = new GLabel(userLabel);
		
		// Add the rectangle and label to the GCompound box
		box.add(rectangle, (- BOX_WIDTH/2),  - BOX_HEIGHT/2);
		box.add(rectLabel, -rectLabel.getWidth() / 2, rectLabel.getAscent() / 2);
		
		// Add the box/label GCompound to the center of the canvas
		add(box, getWidth()/2, getHeight()/2 );
		
		return(box);

	}



	/**
	 * Private instance variables
	 */
	private JTextField textField;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;

	private GPoint startingPoint;
	private GObject clickedBox;

	private HashMap<String, GCompound> boxSet;

	/**
	 * Constants
	 */
	private static final long serialVersionUID = 1L;

	private static final double BOX_HEIGHT = 50;
	private static final double BOX_WIDTH = 120;

}
