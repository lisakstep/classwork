import java.util.Scanner;

/**
 * @author Lisa Stephens
 * completed May 28, 2014
 * 
 * determine if an integer is prime
 * 
 * This method will work for valid Java ints, that is integers
 * from -2 to the 31st power to (2 to the 31st power -1 )
 *
 */
public class CheckForPrimeness {


	public static void main(String[] args) {
		// main class for using the checkIsPrime method
		
		// get the number to which to apply the primeness test from user input
		int numberToTest = getTheUserNumber();
		// test the number
		Boolean numberIsPrime = checkIsPrime(numberToTest);

		// print out appropriate response
		if (numberIsPrime ) {
			System.out.println(numberToTest + " is prime.");
		}
		else {
			System.out.println(numberToTest + " is composite (not prime).");
		}
	}


	private static int getTheUserNumber() {
		// get the number from the user. ensure it is an integer, return the integer
		Scanner scanner = new Scanner(System.in);

		int numberToTest = 0;
		Boolean validInput = false;

		// cycle through the input process until an integer is received
		while (!validInput) {
			System.out.println("Enter the number to be tested for prime-ness: ");
			if ( !scanner.hasNextInt()) {
				System.out.println("Please enter an integer.");
				scanner.next();
			}
			else {
				numberToTest = scanner.nextInt();
				validInput = true;
			}	
		}
		// return the integer to the caller
		return numberToTest;
	}

	private static Boolean checkIsPrime(int numberToTest) {
		/*
		 * This java method takes an integer as an input and returns a boolean value indicating if the
		 * number is prime (true) or not (false).
		 */
		
		// By definition, 1 and 2 are prime and any integer less than 1 is not
		if ( ((numberToTest) == 1) || (numberToTest == 2) ) {
			return true;
		}
		if (numberToTest < 1) {
			return false;
		}
		
		// check primeness for integers with value greater than 2
		for (int i=2; i< (numberToTest-1) ; i++ ) {
			if (((numberToTest % i) == 0) ) {
				return false;
			}
		}
		return true;
	}

}
