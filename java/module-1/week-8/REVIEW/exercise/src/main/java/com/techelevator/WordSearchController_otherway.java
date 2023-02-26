package com.techelevator;

import com.techelevator.util.BasicConsole;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordSearchController_otherway {

    private final WordSearchView view;

    public WordSearchController_otherway(BasicConsole console) {
        view = new WordSearchView(console);
    }

    public void run() {

        // Prompt for the search path, search word, and book category
        File folderToSearch = view.promptForSearchFolder();
        String searchWord = view.promptForSearchWord();
        String category = view.promptForCategory();
        view.printBlankLine();

        /*
         Your code begins here
         */
        File[] files = folderToSearch.listFiles();
        List<SearchResult> searchResults = new ArrayList<>();
        for (File file: files) {
            String fileName = file.getName();
            if (fileName.endsWith(category)){
                view.printMessage("Searching " + fileName);
                int hitCount = 0;
                SearchResult searchResult = null;
                try(Scanner wordCount = new Scanner(file)){
                    String firstLine = wordCount.nextLine();
                    String[] metaData = firstLine.split("\\|");
                    Book book = new Book(metaData[0], metaData[1], metaData[2], LocalDate.parse(metaData[3]), Integer.parseInt(metaData[4]));
                    while(wordCount.hasNextLine()) {
                        if (wordCount.nextLine().contains(searchWord)) {
                            hitCount++;

                        }

                    }
                    view.printMessage("(" + hitCount + " occurrences of " + searchWord + ")");

                    searchResult = new SearchResult(book, category, hitCount);

                } catch (FileNotFoundException ex){
                    System.err.println("The file does not exist.");
                }


                if (hitCount > 0) {
                    searchResults.add(searchResult);

                }
            }

        }

        if (searchResults.size() >= 1) {
            System.out.println();
            System.out.println("Search Results");
            System.out.println();

            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println(i + 1 + ".   " + searchResults.get(i).getBook().getTitle() + " " + "(" + searchResults.get(i).getNumberOfHits() + " occurrences of " + searchWord + ")");
                System.out.println("       " + "Author: " + searchResults.get(i).getBook().getAuthor());
                System.out.println("       " + "Category: " + searchResults.get(i).getCategory() + "(" + searchResults.get(i).getBook().getPageCount() + " pages)");
                System.out.println("       " + "Publisher:" + searchResults.get(i).getBook().getPublisher());
                System.out.println("       " + "Year:" + searchResults.get(i).getBook().getDatePublished().getYear());
            }


        }
    }

}
