/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.program.ConsoleProgram;
import java.io.*;
import java.util.*;

public class HangmanLexicon extends ConsoleProgram{
	ArrayList<String> strList = new ArrayList <String>();
	BufferedReader rd = null;
	// This is the HangmanLexicon constructor
	public HangmanLexicon() {
		//** read the specified file into the ArrayList strList
		try {
			rd = new BufferedReader ( new FileReader("HangmanLexicon.txt") );
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				strList.add(line);

			}
		} catch (IOException ex) {
			println("Bad, bad, file");
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return strList.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return strList.get(index);
	}
}


/* This is the original stub version of the class
public class HangmanLexicon {

/ Returns the number of words in the lexicon. /
	public int getWordCount() {
		return 10;
	}

/ Returns the word at the specified index./
	public String getWord(int index) {
		switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
	};
}
 This is the end of the stub version of the class */