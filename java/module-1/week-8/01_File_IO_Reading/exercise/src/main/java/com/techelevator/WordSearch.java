package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//In this exercise, you'll write a program that searches the contents of a file for a word.
// For each occurrence of the word in the file, you'll display the line number
// and the contents of that line in the console.

public class WordSearch {

	// Use this scanner for all user input. Don't create additional Scanners with System.in
	private final Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) {
		WordSearch wordSearch = new WordSearch();
		wordSearch.run();
	}

	public void run() {
		/* Your code goes here */

		//Runs Scanner object for user input
		try(userInput){

			//Ask for user input
			System.out.println("What is the fully qualified name of the file that should be searched?");
			System.out.println("[path-to-the-file]");

			//Save input to file
			String filePath = userInput.nextLine();
			File file = new File(filePath);

			//prompt the user a word to search for in the file.
			System.out.println("What is the search word you are looking for?");
			String searchWord = userInput.nextLine(); //Variable created to saver user input/answer


			//Modify the WordSearch program to ask the user if they want the search
			// to be case-sensitive.
			System.out.println("Should the search be case sensitive? (Y\\N)");
			String sensitivity = userInput.nextLine(); //Variable created to save user input/answer

			int lineCount = 1; // count number of lines

			//To open the file pass it through a new Scanner
			try(Scanner input = new Scanner(file)){
				//Read the file line by line
				while (input.hasNextLine()){
					//Assigns the line to then input variable allowing us to work with the lines
					String allWordsInFIle = input.nextLine();
					//Turns the user input into lowercase and checks if insensitive
					if (sensitivity.toLowerCase().equals("n")){
						//Since insensitive need to change words to all same case to
						//check if the searched word is in the file at all
						if(allWordsInFIle.toLowerCase().contains(searchWord.toLowerCase())){
							System.out.println(lineCount + ") " + allWordsInFIle);
						}
					} else{
						//must check if the searched word is in the words in the file as the user input it
						if(allWordsInFIle.contains(searchWord)){
							System.out.println(lineCount + ") " + allWordsInFIle);
						}
					}
					lineCount += 1;
				}
				// this both closes the file once the try block completes and catches a
				// FileNotFoundException if something happens with the file while reading it

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}


	}

}
