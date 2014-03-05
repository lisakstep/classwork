/*
 * File: Hangman.java
 * ------------------
 * @author Lisa Stephens
 * 
 * This is my solution to the Stanford CS 106a Assignment 4
 * completed with help from referring to @NatashaTheRobot gist at github
 * and occasional references to questions on Stackoverflow.com
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;


public class Hangman extends ConsoleProgram {

	private static final int NUMBER_OF_GUESSES = 8;

	// Set up the canvas for the hangman picture
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}


	public void run() {
		// Set up game lexicon one time
		HangmanLexicon secretWords = setUpLexicon();
		int playAgain = 1;

		while ( playAgain == 1 ) {
			// Welcome the player, set up some initial info
			int guessesRemaining = NUMBER_OF_GUESSES;
			welcomePlayer();
			boolean wordGuessed = false;
			// Draw the scaffold and clear the gameboard if necessary
			canvas.reset();

			//Select the secret word for this game
			String chosenWord = selectSecretWord (secretWords);
			//println("Word selected (chosenWord) is " + chosenWord);
			String currentGuess = setupInitialGuess(chosenWord);

			// Start playing the game
			while ( (guessesRemaining > 0) && (!wordGuessed) ) {
				printGameStatusInfo(guessesRemaining, currentGuess);

				String letterGuessed = getPlayerLetter();
				if (letterIsInWord (chosenWord, letterGuessed) ) {
					currentGuess = revealLetterGuessed(chosenWord, letterGuessed, currentGuess);

				}
				else {
					println("There are no "+ letterGuessed + "'s in the word");
					guessesRemaining -=1;
					canvas.noteIncorrectGuess(letterGuessed, NUMBER_OF_GUESSES - guessesRemaining);

				}
				if ( currentGuess.equals(chosenWord) ) {
					wordGuessed = true;
				}
			}
			closeOutThisWord( wordGuessed, chosenWord);

			playAgain = readInt("Do you want to play again? Enter \"1\" for yes or any other number for no. ");
		}
		// User is done playing the game
		println("Thanks for playing!");
	}



	// These are the methods called in the main run() class

	private void closeOutThisWord(boolean wordGuessed, String chosenWord) {
		// Give the appropriate message when either the game is won or lost
		if (!wordGuessed) {
			println("You are hanged.\nThe word was " + chosenWord + "\nYou lose.");
		}
		else {
			canvas.displayWord(chosenWord);
			println("You win!! You guessed all the letters!");
		}
	}


	private boolean letterIsInWord(String chosenWord, String letterGuessed) {
		// TODO Auto-generated method stub
		int index = chosenWord.indexOf(letterGuessed);
		if (index == -1) {
			return false;
		}
		else return true;
	}


	private String setupInitialGuess(String str) {
		// initially, set the current word as all hyphens
		String word = "";
		for (int i = 0; i < str.length(); i++){
			word = word + "-";
		}
		return word;
	}


	private String revealLetterGuessed(String chosenWord, String letterGuessed, String currentGuess) {
		// print out the secret word with hyphens in place of letters not yet guessed
		// and make the working copy of the word do the opposite: put hyphens in the place
		// of the guessed letter to handle multiple occurrences of the same letter
		int index = chosenWord.indexOf(letterGuessed);
		String updatedGuess = currentGuess;
		println("That guess is correct.");
		while (index != -1) {
			updatedGuess = updatedGuess.substring(0, index) + letterGuessed + updatedGuess.substring(index+1);
			chosenWord = chosenWord.substring(0,index) + "-" + chosenWord.substring(index+1);
			index = chosenWord.indexOf(letterGuessed);
		}
		return updatedGuess;
	}


	private String getPlayerLetter() {
		// Get the player's guess and ensure it is a single letter
		String singleLetter = "";

		while (true) {
			// Get the user's guess and convert to uppercase
			singleLetter = readLine("Guess a letter: ").toUpperCase();
			char ch = singleLetter.charAt(0);
			// Make sure the user entered only one character and make sure it is a letter
			if ( (singleLetter.length() == 1) && (Character.isLetter(ch) ) ) {
				return singleLetter;
			}
			println("Please enter a single letter. Try again.");
		}
	}

	private void printGameStatusInfo(int guessesLeft, String str) {
		// Show the current appearance of the guessed letters within the word
		// and the appropriate guesses left message
		canvas.displayWord(str);
		println("The word now looks like this " + str);
		if (guessesLeft > 1) {
			println("You have " + guessesLeft + " guesses remaining.");
		}
		else {
			println("You have only one guess left. Make the most of it!");
		}
	}


	private void welcomePlayer() {
		// Welcome the player and introduce the game
		println("Welcome to Lisa's version of Hangman! Good luck!");

	}


	private HangmanLexicon setUpLexicon() {
		// Set up the collection of possible secret words
		HangmanLexicon secretWords = new HangmanLexicon();
		// Set up randomizer for secret word selection
		return secretWords;
	}

	private String selectSecretWord(HangmanLexicon secretWords) {
		// pick a random word for each game
		int wordSelector = rgen.nextInt(0, (secretWords.getWordCount() - 1) );
		String chosenWord = secretWords.getWord(wordSelector);
		return chosenWord;
	}

	private	RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanCanvas canvas;
}
