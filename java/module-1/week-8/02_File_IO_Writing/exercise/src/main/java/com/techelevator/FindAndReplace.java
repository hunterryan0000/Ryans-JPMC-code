package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FindAndReplace {

    // Use this scanner for all user input. Don't create additional Scanners with System.in
    private final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        FindAndReplace findAndReplace = new FindAndReplace();
        findAndReplace.run();
    }

    public void run() {
        /* Your code goes here */
        try (userInput) {

            // Get the search word
            System.out.println("What is the search word?");
            String searchWord = userInput.nextLine();


            // Get the replacement word
            System.out.println("What is the replacement word?");
            String replacementWord = userInput.nextLine();

            // Get the location of the source file
            System.out.println("What is the source file?");
            String sourcePath = userInput.nextLine();
            // Validate the input file
            File sourceFile = new File(sourcePath);

            // Get the location of the destination file
            System.out.println("What is the destination file?");
            String destinationPath = userInput.nextLine();
            File destinationFile = new File(destinationPath);

            // Read from one file while writing to another; Replaces word
            try (
                Scanner sourceScanner = new Scanner(sourceFile);
                PrintWriter destinationWriter = new PrintWriter(destinationFile)
            ) {
                while (sourceScanner.hasNextLine()) {
                    String lineOfInput = sourceScanner.nextLine();
                    destinationWriter.println(lineOfInput.replaceAll(searchWord, replacementWord));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

}
