/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 * 
 * @author Lisa Stephens
 * completed June 1, 2014
 * 
 */

//import acm.util.*;
//import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {
		// Set up the form of the NameSurferEntry and initialize
		name = "Empty";
		for (int i = 0; i< NDECADES; i++) {
			decade[i] = 0; 
		}

		/* Take the line read in by the buffered reader and assign the appropriate
		 * values to the appropriate variables
		 */
		parseRawDataLine( line);

	}

	private void parseRawDataLine(String line) {
		// put each of the tokens into the correct variable, name first followed
		// by decade rank number

		String[] tokens = line.split("[ ]+");

		if (tokens.length > 1) {
			// The first token is the name
			name = tokens[0].toUpperCase();

			// Skip the name and store the frequency ranks for the decades
			for (int i = 1; i < tokens.length; i++) {
				decade[i-1] = Integer.parseInt(tokens[i]);

			}
		}


	}


	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		// Give the name of the entry //

		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decadeNumber) {
		// Return the rank for the name in the given decade //
		int rankValue = 0;
		rankValue = decade[decadeNumber];
		return rankValue;
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		// Return the contents of the NameSurferEntry
		String entry = name;
		for (int i = 0; i< NDECADES; i++) {
			entry = entry + " " + decade[i]; }
		return entry;
	}

	private String name;
	private int[] decade = new int[NDECADES];
}

