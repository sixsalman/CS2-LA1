package edu.wmich.cs1120.LA1.SKhan;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Muhammad Salman Khan
 * 
 * This class serves as a blue print for CandyGame objects. It holds, manipulates and outputs information related to players.
 *
 */
public class CandyGame {
	
	private int[] studs;
	private Random rand = new Random();
	
	/**
	 * Obtains and validates no. of players from user.
	 * @param low receives lower value for range of no. of players
	 * @param high receives upper value for range of no. of players
	 * @param kbd receives a Scanner object
	 * @return validated no. of players obtained from user
	 */
	public int getStudCount(int low, int high, Scanner kbd){
		System.out.printf("Enter an integer in [%d, %d] inclusive:\n", low, high);
		int inp = kbd.nextInt();	
		while (true) {
			if(inp<low || inp>high) {
				System.out.printf("Must be in [%d, %d] inclusive! Re-enter:\n", low, high);
				inp = kbd.nextInt();
			} else {
				return inp;
			}
		}
	}

	/**
	 * Sets length (size) of array based upon the no. previously obtained from user.
	 * @param count receives no. of players (array length)
	 */
	public void setStudsLength(int count){
		studs = new int[count];
	}

	/**
	 * Generates a random within which meets required conditions
	 * @param low receives lower value for range within which initial no. is to be generated
	 * @param high receives upper value for range within which initial no. is to be generated
	 * @return generated random number
	 */
	private int genRand(int low, int high){
		int generatedNum = rand.nextInt((high-low+1)) + low;
		while (true) {
			if(generatedNum%2!=0) {
				generatedNum = rand.nextInt((high-low+1)) + low;
			} else {
				return generatedNum;
			}
		}
	}

	/**
	 * Assigns initial values to player array using genRand method.
	 * @param low receives lower value for range within which initial no. is to be generated
	 * @param high receives upper value for range within which initial no. is to be generated
	 */
	public void assignStuds(int low, int high){
		for(int i=0; i<studs.length; i++) {
			studs[i] = genRand(low, high);
		}
	}

	/**
	 * Outputs contents of player(student) array
	 */
	public void printStuds(){
		for(int stud: studs) {
			System.out.printf("%4d", stud);
		}
		System.out.print("\n");
	}

	/**
	 * Updates Elements of the player array
	 */
	public void updateStuds(){
		int[] tempArray = new int[studs.length];
		for(int i=0; i<studs.length; i++) {
			studs[i] /=2;
			tempArray[i] = studs[i];
		}
		for(int i=0; i<(studs.length-1) ; i++) {
			studs[i+1] += tempArray[i];
		}
		studs[0] += tempArray[studs.length-1];
		for(int i=0; i<studs.length ; i++) {
			if(studs[i]%2!=0)
				studs[i]++;
		}
	}

	/**
	 * Checks whether game has ended or not.
	 * @return true if game has ended (all players possess equal no. of candies); false otherwise
	 */
	public boolean checkEnd(){
		int checkVal = studs[0];
		for(int i=1; i<studs.length; i++) {
			if(studs[i] != checkVal)
				return false;
		}
		return true;
	}
	
}
