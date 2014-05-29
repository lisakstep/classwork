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
		// Read the data text file and load up the local database
		initData();
		
		
		
		// Create the labels and the textbox
		addNameLabel();
		addNameBox();
		addGraphButton();
		addClearButton();
		
		//add ActionListeners();
		// Set up the graph object
		graph = new NameSurferGraph();
		add(graph);

	}

	private void initData() {
		// TODO Auto-generated method stub
		namesInfo = new NameSurferDataBase(fileName);
		
		/* temp testin code
		String testName = "Tabitha";
		int testDecade = 6;
		if (namesInfo.findEntry(testName) != null ) {
			println("Name " + testName + " exists.");

			println(namesInfo.findEntry(testName).toString());
			println(namesInfo.findEntry(testName).getRank(testDecade));
		}
		else {
			println("Name " + testName + " doesn't exist.");

		}
		*/
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
			graph.clear();
		}
		else if (e.getSource() == graphButton) {
			//println("Graph: " + nameBox.getText());
			
			
			// temp testing code
			if (namesInfo.findEntry(nameBox.getText()) != null ) {
				println("Name " + nameBox.getText() + " exists.");

				println("Graph: " + namesInfo.findEntry(nameBox.getText()).toString());
				graph.addEntry(namesInfo.findEntry(nameBox.getText()) );
			}
			else {
				println("Name " + nameBox.getText() + " isn't in the database.");

			}
			
		}
		else if (e.getSource() == nameBox) {
			//println("Graph: " + nameBox.getText());
			
			/*temp testing code
			if (namesInfo.findEntry(nameBox.getText()) != null ) {
				println("Name " + nameBox.getText() + " exists.");

				println("Graph: " + namesInfo.findEntry(nameBox.getText()).toString());
			}
			else {
				println("Name " + nameBox.getText() + " doesn't exist.");

			}
			*/
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
		// Add the empty box for user-entered text
		nameBox = new JTextField(25);
		nameBox.addActionListener(this);
		add (nameBox, SOUTH);
	}


	/* Private instance variables */
	private JTextField nameBox;
	private JButton graphButton;
	private JButton clearButton;

	private static final String fileName = "names-data.txt";
	private NameSurferDataBase namesInfo;
	
	private NameSurferGraph graph;
}
