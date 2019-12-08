package edu.wmich.cs1120.LA1.SKhan;

import java.util.Scanner;

/**
 * 
 * @author Muhammad Salman Khan
 * 4th September 2019
 * CS-1120-545
 * 
 * This program simulates a game in which players (students in our case) stand in a circle and are given an even number of
 * candies. Every player passes half of his candies to the player on his right and if after a round a player ends up with
 * an odd number candies, he is one more candy and the game continues until all players end up having same number of candies.
 * 
 * Pseudocode is LA1SKhan folder (parent folder of this class) as a text file named "Pseudocode".
 *
 */
public class Controller {

	/**
	 * Creates Scanner and CandyGame objects, obtains and validates no. of players, lowest and highest no. (range) of
	 * desired candies to pass to every player initially, along with other choices , and passes the information
	 * accordingly to methods of CandyGame object.
	 * @param args is not used
	 */
	public static void main(String[] args) {
		
		Scanner kbd = new Scanner(System.in);
		
		CandyGame game = new CandyGame();
		
		System.out.println("Getting the number of students.");
		game.setStudsLength(game.getStudCount(15, 30, kbd));
		
		System.out.println("Getting the lower number of starting candy pieces 4 to 10.\n"
				+ "Enter an even integer in [4, 10] inclusive:");
		
		int low = kbd.nextInt();
		
		boolean valid = false;	
		while (!valid) {
			if(low<4 || low>10 || low%2!=0) {
				System.out.println("Must be EVEN and in [4, 10] inclusive! Re-enter:");
				low = kbd.nextInt();
			} else {
				valid = true;
			}
		}
		
		System.out.printf("Getting the upper number of starting candy pieces 4 to 10.\n"
				+ "Must be even and greater than %d (the lower number) but less or equal to %d (the lower number plus 50).\n"
				+ "Enter an even integer in [%d, %d] inclusive:\n", low, (low+50), (low+2), (low+50));
		
		int high = kbd.nextInt();
		
		valid = false;	
		while (!valid) {
			if(high<(low+2) || high>(low+50) || high%2!=0) {
				System.out.printf("Must be EVEN and in [%d, %d] inclusive! Re-enter:\n", (low+2), (low+50));
				high = kbd.nextInt();
			} else {
				valid = true;
			}
		}
		
		game.assignStuds(low, high);
		
		System.out.println("The original deal is:\n");
		
		game.printStuds();
		
		System.out.println("We are ready to play the game.\n"
				+ "Do you want to print the status after each move? (1 for yes, 0 for no)\n"
				+ "Enter an integer in [0, 1] inclusive:");
		
		int opt = kbd.nextInt();
		
		valid = false;	
		while (!valid) {
			if(opt!=0 && opt!=1) {
				System.out.println("Must be in [0, 1] inclusive! Re-enter:");
				opt = kbd.nextInt();
			} else {
				valid = true;
			}
		}
		
		System.out.print("\n");
		
		switch(opt) {
		case 0:
			while (!game.checkEnd()) {
				game.updateStuds();
			}
			game.printStuds();
			break;
		case 1:
			if(game.checkEnd())
				game.printStuds();
			while (!game.checkEnd()) {
				game.updateStuds();
				game.printStuds();
			}
		}
		
		kbd.close();
		
	}

}
