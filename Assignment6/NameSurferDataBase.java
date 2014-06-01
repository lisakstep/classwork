import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 * 
 * @author Lisa Stephens
 * completed June 1, 2014
 * 
 */

public class NameSurferDataBase implements NameSurferConstants {

	/* File processor							*/
	/**
	 * Reads the data one line at a time to place the census name
	 * information into the proper format for the dynamic database
	 */

	private void readNameFileInfo(String filename) {
		// namesDb HashMap
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader(filename));

			while (true) {
				String line = rd.readLine();

				if (line == null) break;
				NameSurferEntry oneLine = new NameSurferEntry(line);
				myData.put(oneLine.getName(), oneLine);
			}
			rd.close();
		} catch (IOException ex){
			System.out.println("bad file");
		}

	}	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {

		myData = new HashMap<String, NameSurferEntry>();

		readNameFileInfo(filename);
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		name = name.toUpperCase();
		if (myData.containsKey(name) ) {
			return myData.get(name);}
		return(null);
	}

	HashMap< String, NameSurferEntry> myData;
}

