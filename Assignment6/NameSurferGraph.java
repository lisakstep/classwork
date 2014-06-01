/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 * 
 * @author Lisa Stephens
 * completed June 1, 2014
 * 
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
		// Empty out the nameToDraw ArrayList
		nameToDraw.clear();
		// Clear the graphics canvas
		removeAll();
		// Redraw the grids
		update();
	}


	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
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
		// empty the field
		removeAll();

		// Start by drawing the background grid and labels
		drawGridWithLabels();

		// Now add all the name/decade data to the graph
		if ( !nameToDraw.isEmpty() ){
			graphNameData();
		}

	}
	private void graphNameData() {
		//Add the points for the popularity data on the vertical decade lines for the graph
		int xPosition1 = GRAPH_MARGIN_SIZE; 
		int xPosition2 = GRAPH_MARGIN_SIZE;
		double yPosition2 = bottomLineY;
		int verticalLineSpacing = APPLICATION_WIDTH/NDECADES;

		// For each name in the nameToDraw ArrayList, plot the popularity points on the vertical bars
		for (int i=0; i< nameToDraw.size(); i++) {

			// Draw the datapoints for this name
			for (int j = 0; j< NDECADES; j++) {


				xPosition1 = GRAPH_MARGIN_SIZE + j*(verticalLineSpacing); 
				double yPosition1 = 0.0;
				Color dataColor = Color.orange;

				double ranking = .001*nameToDraw.get(i).getRank(j);

				// If the ranking is 0, place the line point at the bottom of the graph
				if (ranking == 0) {
					yPosition1 = bottomLineY;
				}
				else {
					yPosition1 = topLineY + (ranking * (bottomLineY - topLineY));
				}


				// Rotate through four colors to differentiate the data lines and labels
				if (i%4 == 0 ) {
					dataColor = Color.blue;
				} else if( i%4 == 1) {
					dataColor = Color.green;
				}else if( i%4 == 2) {
					dataColor = Color.red;
				}else if( i%4 == 3) {
					dataColor = Color.magenta;
				}

				// draw a small rect at the datapoint
				GRect secondRect = new GRect( xPosition1, yPosition1, 3, 3);
				secondRect.setFilled(true);
				secondRect.setFillColor(dataColor);
				add(secondRect);

				// Set up and draw the label next to the data point
				GLabel decadeLabel = new GLabel(nameToDraw.get(i).getName(), xPosition1, yPosition1);
				decadeLabel.setColor(dataColor);
				add(decadeLabel);

				if (j>0) {
					// Once there are at least two data points, define and draw the line
					GLine lineSegment = new GLine( xPosition1, yPosition1, xPosition2, yPosition2);
					lineSegment.setColor(dataColor);
					add(lineSegment);
				}

				// set the endpoint for the new line segment
				xPosition2 = xPosition1;
				yPosition2 = yPosition1;

			}

		}
	}


	// Private Instance variables
	private ArrayList<NameSurferEntry> nameToDraw;
	private int topLineY = GRAPH_MARGIN_SIZE;
	private int bottomLineY = APPLICATION_HEIGHT - 4*GRAPH_MARGIN_SIZE;


	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
