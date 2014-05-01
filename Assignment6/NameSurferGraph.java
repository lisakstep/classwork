/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
		// Set up the ArrayList for the names to draw as requested by the user
		nameToDraw = new ArrayList<NameSurferEntry>();

		// Draw the background grid
		drawGridWithLabels();

	}


	private void drawGridWithLabels() {

		// Draw the horizontal axes
		GLine topXAxis = new GLine( GRAPH_MARGIN_SIZE, 0 + GRAPH_MARGIN_SIZE, APPLICATION_WIDTH, 0 + GRAPH_MARGIN_SIZE);
		add(topXAxis);
		GLine bottomXAxis = new GLine( GRAPH_MARGIN_SIZE, APPLICATION_HEIGHT - 4*GRAPH_MARGIN_SIZE, APPLICATION_WIDTH, APPLICATION_HEIGHT - 4*GRAPH_MARGIN_SIZE);
		add(bottomXAxis);

		// Draw all the vertical lines for the graph and the associated decade labels
		int xPosition = GRAPH_MARGIN_SIZE; 
		int verticalLineSpacing = APPLICATION_WIDTH/NDECADES;
		Integer decadeName = START_DECADE;

		for (int i = 0; i< NDECADES; i++) {
			GLine verticalLine = new GLine( xPosition, APPLICATION_HEIGHT, xPosition, 0);
			add(verticalLine);
			GLabel decadeLabel = new GLabel(decadeName.toString(), xPosition, APPLICATION_HEIGHT - 3.4*GRAPH_MARGIN_SIZE);
			add(decadeLabel);
			decadeName += 10;
			xPosition += verticalLineSpacing;
		}
		
	}


	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //
		// Empty out the nameToDraw ArrayList
		nameToDraw.clear();
		System.out.println("clearing out the names");
		update();
	}


	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		nameToDraw.add(entry);
		update();
	}


	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		// Start by drawing the background grid and labels
		drawGridWithLabels();
		
		// Now add all the name/decade data to the graph
		if ( !nameToDraw.isEmpty() ){
			graphNameData();
		}

	}
	private void graphNameData() {
		// TODO Auto-generated method stub
		// Set the beginning x coordinate
		//int xStart = GRAPH_MARGIN_SIZE;
		// Set the beginning y coordinate
		//String thisName = 
		//System.out.println("The rank for the first decade is: " + nameToDraw.findEntry(name).getRank()	);
		//int yStart = nameToDraw.
		
		// Debugging show me the names in the nameToDraw ArrayList
		for (int i=0; i< nameToDraw.size(); i++) {
			System.out.println(nameToDraw.get(i));
			System.out.println("graphing this name: " + nameToDraw.get(i).getName() + " which has the first decade popularity rank of " + nameToDraw.get(i).getRank(0));

		}
	}
	
	
	// Private Instance variables
	private ArrayList<NameSurferEntry> nameToDraw;


	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
