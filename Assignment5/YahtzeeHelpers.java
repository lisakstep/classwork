/**
 * File: YahtzeeHelpers
 * This contains the public methods for determining if the category
 * chosen by a Yahtzee player is valid for their dice roll.
 * 
 * @author Lisa Stephens
 * completed 2/23/2014 as part of an assignment for Stanford CS106a
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import acm.program.*;
import acm.util.*;

public class YahtzeeHelpers extends ConsoleProgram implements YahtzeeConstants{

	/**
	 * Tallies the number of each dice value, then determines if a player-chosen
	 * category is valid for the dice roll.
	 * 
	 * @param dice
	 * @param category
	 * @return true or false indicating that whether a valid category was chosen
	 */
	public static boolean checkCategory(int dice[], int category) {
		// Count how many dice of each value are in the roll
		ones = countDigits(dice, 1);
		twos = countDigits(dice, 2);
		threes = countDigits(dice, 3);
		fours = countDigits(dice, 4);
		fives = countDigits(dice, 5);
		sixes = countDigits(dice, 6);

		// Use the game criteria and the digit counts from above to determine if a roll is valid
		// for the player's chosen category
		switch (category)	{
		case (0) :	return true;	// The numbers categories are always a valid choice
		case (1) :	return true;	// The numbers categories are always a valid choice
		case (2) :	return true;	// The numbers categories are always a valid choice
		case (3) :	return true;	// The numbers categories are always a valid choice
		case (4) :	return true;	// The numbers categories are always a valid choice
		case (5) :	return true;	// The numbers categories are always a valid choice
		case (8) :	return (isThreeOfAKind(dice)); 
		case (9) :	return (isFourOfAKind(dice));
		case (10) :	return (isFullHouse(dice));
		case (11) :	return (isSmallStraight(dice));
		case (12) :	return (isLargeStraight(dice));
		case (13) :	return (isYahtzee(dice));	// Valid when all five dice are the same
		case (14) :	return true; 	// Chance is always a valid choice
		default: return true;
		}
	}

	private static int countDigits(int[] dice, int j) {
		// count the number of dice with j pips
		int count = 0;
		for (int i = 0; i< N_DICE; i++){
			if (dice[i] == j) {
				count++;
			}
		}
		return count;
	}

	private static boolean isLargeStraight(int[] dice) {
		// TODO Auto-generated method stub
		// There are two possibilities for large straights: 1,2,3,4,5 or 2,3,4,5,6 
		if  ( ( (ones >= 1) && (twos >= 1) && (threes >= 1) && (fours >= 1) && (fives >= 1) ) ||
				( (twos >= 1) && (threes >= 1) && (fours >= 1) && (fives >= 1) && (sixes >= 1) )  ){
			return true;
		}
		else
			return false;
	}

	private static boolean isSmallStraight(int[] dice) {
		// There are three possibilities for small straights: 1,2,3,4 or 2,3,4,5 or 3,4,5,6
		if  ( ( (ones >= 1) && (twos >= 1) && (threes >= 1) && (fours >= 1) ) ||
				( (twos >= 1) && (threes >= 1) && (fours >= 1) && (fives >= 1) ) ||
				( (threes >= 1) && (fours >= 1)  && (fives >= 1) && (sixes >= 1) )  ){
			return true;
		}
		else
			return false;
	}

	private static boolean isFullHouse(int[] dice) {
		// See if there is exactly a three-of-a-kind and a two-of-a-kind
		if ( ( (ones == 3) || (twos == 3) || (threes == 3) || (fours == 3) || (fives == 3) || (sixes == 3) ) &&
				( (ones == 2) || (twos == 2) || (threes == 2) || (fours == 2) || (fives == 2) || (sixes == 2) )  ) {
			return true;	
		}
		else
			return false;
	}

	private static boolean isFourOfAKind(int[] dice) {
		// See if there are at least four of any number
		if ( (ones >= 4) || (twos >= 4) || (threes >= 4) || (fours >= 4) || (fives >= 4) || (sixes >= 4) ) {
			return true;
		}
		else		
			return false;
	}

	private static boolean isThreeOfAKind(int[] dice) {
		// See if there are at least 3 of any die face value
		if ( (ones >= 3) || (twos >= 3) || (threes >= 3) || (fours >= 3) || (fives >= 3) || (sixes >= 3) ) {
			return true;
		}
		else		
			return false;
	}

	private static boolean isYahtzee(int[] dice) {
		// is the dice roll a Yahtzee?
		if ( (ones >= 5) || (twos >= 5) || (threes >= 5) || (fours >= 5) || (fives >= 5) || (sixes >= 5) ) {
			return true;
		}
		else		
			return false;
	}

	private static int ones;
	private static int twos;
	private static int threes;
	private static int fours;
	private static int fives;
	private static int sixes;


	public static void showHallOfFame() {
		// TODO Auto-generated method stub
		System.out.println("got to the showHallOfFame method.");
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader("HighScores"));

			while (rd == null) {

				String line = rd.readLine();
				if (line == null) break;
				System.out.println(line);
			}
			rd.close();

		} catch (IOException ex) {
			System.out.println("bad file");
		}

	}

}
