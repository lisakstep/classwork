import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author lisastephens
 * determine if a number is prime
 *
 */
public class CheckForPrimeness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numberToTest = getTheUserNumber();
		Boolean numberIsPrime = checkIsPrime(numberToTest);

		System.out.println("Testing " + numberToTest);

		if (numberIsPrime ) {
			System.out.println(numberToTest + " is prime.");
		}
		else {
			System.out.println(numberToTest + " is composite (not prime).");

		}

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

	private static int getTheUserNumber() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new InputStreamReader(System.in));

		int numberToTest = 0;
		Boolean validInput = false;

		while (!validInput) {

			System.out.println("Enter the number to be tested for prime-ness: ");
			numberToTest = scanner.nextInt();
			if (numberToTest < 1) {
				System.out.println("Please enter an integer greater than 0.");
			}
			else {
				validInput = true;
			}
		}
		return numberToTest;
	}

}
