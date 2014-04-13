/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
		// You fill this in, along with any helper methods //
		addNameLabel();
		addNameBox();
		addGraphButton();
		addClearButton();
		//add ActionListeners();
	}
	
	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getSource() == clearButton) {
			println("clear");
		}
		else if (e.getSource() == graphButton) {
			println("Graph: " + nameBox.getText());
		}
		else if (e.getSource() == nameBox) {
			println("Graph: " + nameBox.getText());
		}
	
	}

/********** helper and sub methods
 * 
 * 
 */
	private void addClearButton() {
		// make the "clear" button
		clearButton = new JButton("Clear");
		add (clearButton, SOUTH);
		clearButton.addActionListener(this);
	}

	private void addGraphButton() {
		// make the "graph" button
		graphButton = new JButton("Graph");
		add (graphButton, SOUTH);
		graphButton.addActionListener(this);
	}

	private void addNameLabel() {
		// add the label "Name" to tell the user what the textbox is for
		add (new JLabel("Name "), SOUTH);
	}

	private void addNameBox() {
		// TODO Auto-generated method stub
		nameBox = new JTextField(25);
		nameBox.addActionListener(this);
		add (nameBox, SOUTH);
	}


	/* Private instance variables */
	private JTextField nameBox;
	private JButton graphButton;
	private JButton clearButton;
}
