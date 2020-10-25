import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.NoSuchElementException;

/** 
 * COMP 2503 Winter 2020 Assignment 1 
 * 
 * This program must read a input stream and keeps track of the 
 * frequency at which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi
 * @date Fall 2020
*/

public class A1 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalWordCount = 0;
	private ArrayList<Avenger> mentionedAvengerList = new ArrayList<>();

	public static void main(String[] args) {
		A1 a1 = new A1();
		a1.run();
	}

	public void run() {
		readInput();
		printResults();
	}

	/**
	 * While Scanner hasNext() from System.in, 
	 * 	1. Takes token from scanner.
	 * 	2. cleans up word, using String.toLowerCase() and String.trim()
	 * 	3. removes apostrophes and any characters following
	 * 	4. removes any non-alphabetic characters
	 * 
	 * 	5. if the String is not empty, increment totalWordCount
	 * 	6. if String matches an alias or last name in the avengerRoster, addAvenger(int rosterIndex).
	 * 
	 *  NoSuchElementException - Thrown when input is blank (i.e. only contains whitespace, or is empty).
	 *  							- Handled within readInput to do nothing. Will print empty results and terminate program.
	 */
	private void readInput() {
		Scanner scan = new Scanner(System.in);
		do {
			String input = "";
			
			// Will throw NoSuchElementException if no input token exists. 
			try {
				input = scan.next();
			} catch (NoSuchElementException e) {
				// Do Nothing. Allow program to run and print results.
			}
			
			input = input.trim().toLowerCase();
			
			// If input contains an apostrophe, retain the substring before the apostrophe character.
			 if (input.contains("'")) {
				 input = input.substring(0, input.indexOf("\'"));
			 }
			 
			input = input.replaceAll("[^a-z]", "");
			
			// If input is not blank, increment totalWordCount and check if input is a valid avenger from avengerRoster.
			// If rosterIndex = -1, input did not match a String within avengerRoster
			if (!input.isBlank()) {
				totalWordCount ++;
				int rosterIndex = getAvengerId(input);
				
				// If Avenger is not in 
				if (rosterIndex > -1) {
					addAvenger(rosterIndex);
				}
			}
			
		} while(scan.hasNext());
		
		scan.close();
	}
	
	/**
	 * print the results
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalWordCount);
		System.out.println("Number of Avengers Mentioned: " + mentionedAvengerList.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		printMentionedList();
		System.out.println();
		
		System.out.println("Top " + topN + " most popular avengers:");
		CompareFrequency compareIncreasing = new CompareFrequency();
		Collections.sort(mentionedAvengerList, compareIncreasing);
		
		printFirstXFromList(topN);
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");	
		CompareLeastFrequent compareDecreasing = new CompareLeastFrequent();
		Collections.sort(mentionedAvengerList, compareDecreasing);
	
		printFirstXFromList(topN);
		System.out.println();
		
		System.out.println("All mentioned avengers in alphabetical order:");
		
		Collections.sort(mentionedAvengerList);
		printMentionedList();
		
		System.out.println();
	}
	
	/**
	 * Takes rosterIndex of matching string and searches for matching Avenger in mentionedAvengerList
	 * @param rosterIndex
	 */
	private void addAvenger (int rosterIndex) {
		int arrayIndex = listContainsAvenger(rosterIndex);
		
		if (arrayIndex == -1) {
			mentionedAvengerList.add(createAvenger(rosterIndex));
			
		} else {
			mentionedAvengerList.get(arrayIndex).mentioned();
		}
	}
	
	
	/**
	 * Returns the index of the Avenger object within mentionedAvengerList that corresponds with the rosterIndex.
	 * 
	 * If no such Avenger exists within mentionedAvengerList or the list is empty, returns -1.
	 * 
	 * @param rosterIndex - index of Avenger within avengerRoster
	 * @return index of matching Avenger object within mentionedAvengerList
	 */
	private int listContainsAvenger(int rosterIndex) {
		
		if (mentionedAvengerList.isEmpty()) {
			return -1;
			
		} else {
			for (int i = 0; i < mentionedAvengerList.size(); i++) {
				
				if (mentionedAvengerList.get(i).getAlias().equals(avengerRoster[rosterIndex][0])) {
					return i;
				}
			}
			return -1;
		}
	}
	
	/**
	 * Creates an Avenger object from the index position of rosterIndex, with a frequency mentioned of 1.
	 * 
	 * @param rosterIndex - first index of avenger within avengerRoster
	 * @return Avenger - object representing the corresponding avengerRoster index.
	 */
	private Avenger createAvenger(int rosterIndex) {
		return new Avenger(avengerRoster[rosterIndex][0], avengerRoster[rosterIndex][1], 1);
	}
	
	/**
	 * Prints each Avenger within mentionedAvengerList using System.out.println();
	 */
	private void printMentionedList() {
		mentionedAvengerList.forEach((n) -> System.out.println(n.toString()));
	}
	
	/**
	 * Prints the first (numToPrint) Avenger objects from mentionedAvengerList using System.out.println();
	 *  If there are not enough objects within mentionedAvengers, the available objects within the list will be printed
	 * 
	 * @param numToPrint
	 */
	private void printFirstXFromList(int numToPrint) {
		if (numToPrint > mentionedAvengerList.size()) {
			
			for (int i = 0; i < mentionedAvengerList.size(); i++) {
				System.out.println(mentionedAvengerList.get(i));
			}
			
		} else {
			
			for (int i = 0; i < numToPrint; i++) {
				System.out.println(mentionedAvengerList.get(i));
			}
		}
		
	}
	
	/**
	 * !Uses avengerRoster!
	 * 
	 *  Requires changes if avengerRoster indices go beyond [x][y>1].
	 * 
	 * 	If no match  is found, returns -1; 
	 *  If a match is found in avengerRoster, returns the first index of avengersRoster
	 * 
	 *  
	 * @param input - String to be matched against avengerRoster.
	 * @return int - first index of corresponding hero in avengersRoster. 
	 */
	private int getAvengerId(String input) {
		int id = -1;
		
		for(int i = 0; i < avengerRoster.length; i++) {
			
			if (avengerRoster[i][0].equals(input)) {
				id = i;
				return id;
				
			} else if (avengerRoster[i][1].equals(input)) {
				id = i;
				return id;
			}
		}
		return id;
	}
}
