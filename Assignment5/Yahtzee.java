/*
 * File: Yahtzee.java
 * ------------------
 * This program plays the Yahtzee game.
 * @author Lisa Stephens
 * completed 2/23/2014 as part of an assignment for Stanford CS106a
 */


import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public void run() {
		YahtzeeHelpers.showHallOfFame();
		addMouseListeners();
		setupPlayers();
		initScores(); // set every score field to SENTINEL -1 to indicate that it hasn't been set yet
		initDisplay();
		playGame();
		closeOutGame();
	}

	/**
	 * Determine the winner and display the winning message.
	 */
	private void closeOutGame() {
		// Determine winner
		int winningPlayer = 0;
		int checkingPlayer = 0;
		// Cycle through all the players and find the highest Total
		while (checkingPlayer < nPlayers) {
			if (nPlayers == 1) {
				// Only one player, that's your winner! This code does nothing.
			}
			else {
				// More than one player, find the highest Total score
				if (playersScores[checkingPlayer][TOTAL] > playersScores[winningPlayer][TOTAL]) {
					winningPlayer = checkingPlayer;
				}
			}
			checkingPlayer++;
		}
		display.printMessage("Good game! " + playerNames[winningPlayer] + " was the winner this time.");
	}


	/** 
	 * Sets all the fields of the scores array to -1 to indicate that the field
	 * hasn't been yet set.
	 */
	private void initScores() {
		// Set all values for the scoring cells to a Sentinel value to indicate that
		// category has not been yet chosen by a player
		for (int i = 0; i < MAX_PLAYERS; i++) {
			for (int j = 0; j < N_CATEGORIES; j++ ) {
				playersScores[i][j] = SENTINEL;
			}
		}
	}

	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	

		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
	}

	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();

		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");

			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;

			dialog.println("Please enter a valid number of players.");
		}
	}

	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee. For the appropriate number of rounds gives
	 * each player a turn.
	 */
	private void playGame() {
		// A yahtzee game goes N_SCORING_CATEGORIES rounds

		for (int rounds = 0; rounds < N_SCORING_CATEGORIES; rounds++) { // play 13 rounds
			for (int player = 0; player < nPlayers; player++) {
				rollDice(player);
				recordScore(player);
			}
		}
	}

	/**
	 * Guides the player through the process of recording the points from the 
	 * current roll of the dice.
	 * @param playr
	 */
	private void recordScore(int playr) {
		/* This is where the player chooses the category for the score
		 of the dice roll and the scorepad is refreshed
		 */
		display.printMessage("Click to choose the category to score this roll.");
		int category = display.waitForPlayerToSelectCategory();
		int score = 0;
		String message = "";
		// Only accept the chosen category if no score has already been recorded there
		while  (playersScores[playr][category] != -1) {
			display.printMessage("You have already recorded a score there. Choose a different category for your roll.");
			category = display.waitForPlayerToSelectCategory();
		}

		// An empty category has been chosen. Now check that the roll qualifies and compute and record the score.
		if ( YahtzeeHelpers.checkCategory(dice, category)  ){
			message = "Your roll qualifies for that category.";
			score = computeScore(category);
		}
		else {
			message = "Sorry, your roll doesn't qualify for that category. Zero for you.";
			score = 0; 
		}
		// add new score to the playersScores array
		playersScores[playr][category] = score;
		display.updateScorecard(category, playr, score);
		updateTotals(playr); // update the scorecard totals and subtotals displayed
		display.printMessage(message);

	}

	/**
	 * Calculates and displays the subtotals, bonus if earned, and total
	 * on the scoresheet.
	 * @param playr
	 */
	private void updateTotals(int playr) {
		int topSubTotal = 0;
		int bonus = 0;
		int lowerSubTotal = 0;

		// Update the subtotal in UPPER_SCORE
		for (int i= 0; i< 6; i++ ) {
			// if a valid score has been entered in a category, add it as part of the subtotal
			if (playersScores[playr][i] != -1) {
				topSubTotal += playersScores[playr][i];
			}
		}
		playersScores[playr][UPPER_SCORE] = topSubTotal;
		display.updateScorecard(UPPER_SCORE, playr, topSubTotal);

		// assign the top half bonus if earned
		if (topSubTotal >= 63) {
			bonus = recordTopHalfBonus(playr);
			playersScores[playr][UPPER_BONUS] = bonus;
			display.updateScorecard(UPPER_BONUS, playr, bonus);
		}


		// Update the subtotal in LOWER_SCORE
		for (int i= 8; i< 15; i++ ) {
			// if a valid score has been entered in a category, add it as part of the subtotal
			if (playersScores[playr][i] != -1) {
				lowerSubTotal += playersScores[playr][i];
			}
		}
		playersScores[playr][LOWER_SCORE] = lowerSubTotal;

		display.updateScorecard(LOWER_SCORE, playr, lowerSubTotal);

		// update the grand total at the bottom of the scorecard
		int grandTotal = topSubTotal + lowerSubTotal + bonus;
		playersScores[playr][TOTAL] = grandTotal;

		display.updateScorecard(TOTAL, playr, grandTotal);
	}


	/**
	 * This adds the bonus to the scoresheet.
	 * 
	 * @param playr
	 * @return the bonus score for the top half of the scoresheet
	 */
	private int recordTopHalfBonus(int playr) {
		// This method is called if the top half score >= 63
		// assign bonus  
		int bonusEarned = 35 ;
		display.updateScorecard(UPPER_BONUS, playr, bonusEarned);
		return bonusEarned;
	}

	/**
	 * This method calculates the appropriate score for the category
	 * chosen by the player based on the final dice roll.
	 * 
	 * @param category
	 * @return Score for the dice roll
	 */
	private int computeScore(int category) {
		// category is valid for the roll, compute appropriate score
		int total = 0;
		// call appropriate method depending on the scoring category
		switch (category)	{
		case (0) :	total = calculateNumbersScore(category);
		break;
		case (1) :	total = calculateNumbersScore(category);
		break;	
		case (2) :	total = calculateNumbersScore(category);
		break;
		case (3) :	total = calculateNumbersScore(category);
		break;
		case (4) :	total = calculateNumbersScore(category);
		break;
		case (5) :	total = calculateNumbersScore(category);
		break;	
		case (8) :	total = addAllDice(); // three of a kind score is all die pips
		break;
		case (9) :	total = addAllDice(); // four of a kind score is all die pips
		break;
		case (10) :	total = fullHouseScore();
		break;
		case (11) :	total = smallStraightScore();
		break;
		case (12) :	total = largeStraightScore();
		break;
		case (13) :	total = yahtzeeScore();
		break;
		case (14) :	total = addAllDice(); // Chance score is all die pips
		break;
		default: break;
		}

		return total;
	}

	/**
	 * @return Score for a Large Straight
	 */
	private int largeStraightScore() {
		// large straight always scores 40
		return 40;
	}

	/**
	 * @return Score for a Yahtzee (five of a kind)
	 */
	private int yahtzeeScore() {
		// yahtzee always scores 50
		return 50;
	}

	/**
	 * 
	 * @return Score for a Small Straight
	 */
	private int smallStraightScore() {
		// small straight always scores 30
		return 30;
	}


	/**
	 * 
	 * @return Score for a full house
	 */
	private int fullHouseScore() {
		// full house always scores 25
		return 25;
	}

	/**
	 * Given the dice roll and chosen category, calculates the score
	 * @param category
	 * @return Score for Ones, Twos, ..., and Sixes categories
	 */
	private int calculateNumbersScore(int category) {
		// This method takes the scoring category entered by the user
		// and calculates the proper score for ones, twos, threes, etc up to sixes
		int digit = category + 1;
		int score = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == digit) {
				score += digit;
			}
		}
		return score;
	}

	/**
	 * Calculates the total of all dice for the Chance, 3 of a Kind, and 4 of a Kind
	 * categories.
	 * @return Adds all the pips of the final roll dice
	 */
	private int addAllDice() {
		// The score for Chance, 3 of a kind, and 4 of a kind is the total of all dice pips
		int total = 0;
		for (int i=0; i < N_DICE; i++) {
			total += dice[i];
		}
		return total;
	}

	/**
	 * Rolls all the dice initially, then rerolls only mouse-selected dice two more times.
	 * @param player
	 */
	private void rollDice(int player) {
		// Start the turn on a mouse click, roll three times
		display.printMessage( playerNames[player] + ", begin your turn by clicking Roll Dice.");

		display.waitForPlayerToClickRoll(player);
		// initial roll
		for (int i=0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1, 6);
		}
		// Update dice display
		display.displayDice(dice);
		// Ask player to choose dice to keep for the two re-rolls
		for (int reroll = 0; reroll < 2; reroll++) {
			display.printMessage("Click any die you want to re-roll, then click Roll again.");
			display.waitForPlayerToSelectDice();
			for (int k=0; k < N_DICE; k++) {

				if (display.isDieSelected(k)){
					dice[k] = rgen.nextInt(1, 6);
				}
			}
			display.displayDice(dice);
		}
	}


	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private int[] dice = new int[N_DICE];
	private int[][] playersScores = new int[MAX_PLAYERS][N_CATEGORIES];

	private RandomGenerator rgen = RandomGenerator.getInstance();
	public static final int SENTINEL = -1;

	private YahtzeeDisplay display;

}
