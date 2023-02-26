package com.techelevator;

import java.math.BigDecimal;
import java.util.*;

public class App {

    // The regex string to split the Strings in the dataset.
    private static final String FIELD_DELIMITER = "\\|";

    private static final int TITLE_FIELD = 0;
    private static final int AUTHOR_FIELD = 1;
    private static final int PUBLISHED_YEAR_FIELD = 2;
    private static final int PRICE_FIELD = 3;

    private final Scanner keyboard = new Scanner(System.in);

    private List<String> titles = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<Integer> publishedYears = new ArrayList<>();
    private List<BigDecimal> prices = new ArrayList<>();

    public static void main(String[] args) {

        App app = new App();
        app.loadData();
        app.run();

    }

    private void loadData() {

        String[] dataset = Dataset.load();

         /*
         Requirement: 1
         Populate the instance variables `titles`, `authors`, `publishedYears`,
         and `prices` by splitting each string in the `dataset` array and adding
         the individual fields to the appropriate list.
         See README for additional details.
         */
        String[] tempStorage;
        for (String rawData : dataset) {
            tempStorage = rawData.split(FIELD_DELIMITER);
            titles.add(tempStorage[0]);
            authors.add(tempStorage[1]);
            publishedYears.add(Integer.parseInt(tempStorage[2]));
            prices.add(new BigDecimal(tempStorage[3]));
        }

    }

    private void run() {

        while (true) {
            // Main menu loop
            printMainMenu();
            int mainMenuSelection = promptForMenuSelection("Please choose an option: ");
            if (mainMenuSelection == 1) {
                // Display data and subsets loop
                while (true) {
                    printDataAndSubsetsMenu();
                    int dataAndSubsetsMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (dataAndSubsetsMenuSelection == 1) {
                        displayDataset(Dataset.load());
                    } else if (dataAndSubsetsMenuSelection == 2) {
                        displayTitlesList(titles);
                    } else if (dataAndSubsetsMenuSelection == 3) {
                        displayAuthorsList(authors);
                    } else if (dataAndSubsetsMenuSelection == 4) {
                        displayPublishedYearsList(publishedYears);
                    } else if (dataAndSubsetsMenuSelection == 5) {
                        displayPricesList(prices);
                    } else if (dataAndSubsetsMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 2) {
                while (true) {
                    printSearchBooksMenu();
                    int searchBooksMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (searchBooksMenuSelection == 1) {
                        // Search by title
                        String filterTitle = promptForString("Enter title: ");
                        /*
                         Requirement: 3b
                         Replace `displayTitlesList(titles)` with calls to the
                         `filterByTitle()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(filterByTitle(filterTitle), TITLE_FIELD);

                    } else if (searchBooksMenuSelection == 2) {
                        // Search by author
                        String filterAuthor = promptForString("Enter author: ");
                        /*
                         Requirement: 4b
                         Replace `displayAuthorsList(authors)` with calls to the
                         `filterByAuthor()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(filterByAuthor(filterAuthor), AUTHOR_FIELD);

                    } else if (searchBooksMenuSelection == 3) {
                        // Search by published year
                        int filterYear = promptForPublishedYear("Enter date (YYYY): ");
                        /*
                         Requirement: 5b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYear()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(filterByPublishedYear(filterYear), PUBLISHED_YEAR_FIELD);

                    } else if (searchBooksMenuSelection == 4) {
                        // Search by published year range
                        int filterFromYear = promptForPublishedYear("Enter \"from\" date (YYYY): ");
                        int filterToYear = promptForPublishedYear("Enter \"to\" date (YYYY): ");
                        /*
                         Requirement: 6b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYearRange()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(filterByPublishedYearRange(filterFromYear, filterToYear), PUBLISHED_YEAR_FIELD);

                    } else if (searchBooksMenuSelection == 5) {
                        // Find the most recent books
                        /*
                         Requirement: 7b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `findMostRecentBooks()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(findMostRecentBooks(), PUBLISHED_YEAR_FIELD);

                    } else if (searchBooksMenuSelection == 6) {
                        // Search by price
                        double filterPrice = promptForPrice("Enter price: ");
                        /*
                         Requirement: 8b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPrice()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(filterByPrice(filterPrice), PRICE_FIELD);

                    } else if (searchBooksMenuSelection == 7) {
                        // Search by price range
                        double filterFromPrice = promptForPrice("Enter \"from\" price: ");
                        double filterToPrice = promptForPrice("Enter \"to\" price: ");
                        /*
                         Requirement: 9b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPriceRange()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(filterByPriceRange(filterFromPrice, filterToPrice), PRICE_FIELD);

                    } else if (searchBooksMenuSelection == 8) {
                        // Find the least expensive books
                        /*
                         Requirement: 10b
                         Replace `displayPricesList(prices)` with calls to the
                         `findLeastExpensiveBooks()` and `displaySearchResults()` methods.
                         */
                        displaySearchResults(findLeastExpensiveBooks(), PRICE_FIELD);
                    } else if (searchBooksMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 0) {
                break;
            }
        }

    }

    /*
     Requirement: 2
     Write the displaySearchResults(List<Integer> indexes) method.
     See README for additional details.
     */
    private void displaySearchResults(List<Integer> indexes, int primaryField) {

        sortSearchResults(indexes, primaryField);

        for (Integer index : indexes) {
            switch (primaryField) {
                case TITLE_FIELD:
                    System.out.print(titles.get(index) + ": " + authors.get(index) + ": " + publishedYears.get(index) + ": $" + prices.get(index) + "\n");
                    break;
                case AUTHOR_FIELD:
                    System.out.print(authors.get(index) + ": " + titles.get(index) + ": " + publishedYears.get(index) + ": $" + prices.get(index) + "\n");
                    break;
                case PUBLISHED_YEAR_FIELD:
                    System.out.print(publishedYears.get(index) + ": " + titles.get(index) + ": " + authors.get(index) + ": $" + prices.get(index) + "\n");
                    break;
                case PRICE_FIELD:
                    System.out.print("$" + prices.get(index) + ": " + titles.get(index) + ": " + authors.get(index) + ": " + publishedYears.get(index) + "\n");
                    break;
            }
        }
    }

    /*
    Challenge 2 Helper Method
     */
    private void sortSearchResults(List<Integer> indexes, int primaryField) {
        boolean hasSwapped = true;

        while (hasSwapped) {
            hasSwapped = false;
            for (int i = 0; i < indexes.size() - 1; i++) {
                int currentIndex = indexes.get(i);
                int nextIndex = indexes.get(i + 1);
                switch (primaryField) {
                    case TITLE_FIELD:
                        if (titles.get(currentIndex).compareToIgnoreCase(titles.get(nextIndex)) > 0) {
                            indexes.set(i, nextIndex);
                            indexes.set(i + 1, currentIndex);
                            hasSwapped = true;
                        }
                        break;
                    case AUTHOR_FIELD:
                        if (authors.get(currentIndex).compareToIgnoreCase(authors.get(nextIndex)) > 0) {
                            indexes.set(i, nextIndex);
                            indexes.set(i + 1, currentIndex);
                            hasSwapped = true;
                        }
                        break;
                    case PUBLISHED_YEAR_FIELD:
                        if (publishedYears.get(currentIndex) > publishedYears.get(nextIndex)) {
                            indexes.set(i, nextIndex);
                            indexes.set(i + 1, currentIndex);
                            hasSwapped = true;
                        }
                        break;
                    case PRICE_FIELD:
                        if (prices.get(currentIndex).compareTo(prices.get(nextIndex)) > 0)  {
                            indexes.set(i, nextIndex);
                            indexes.set(i + 1, currentIndex);
                            hasSwapped = true;
                        }
                        break;

                }
            }

        }
    }


    /*
     Requirement: 3a
     Complete the `filterByTitle()` method.
     See README for additional details.
     */
    private List<Integer> filterByTitle(String filterTitle) {
        List<Integer> titleIndexes = new ArrayList<>();
        if (titles.size() == 0) return titleIndexes;

        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).toLowerCase().contains(filterTitle.toLowerCase())) {
                titleIndexes.add(i);
            }
        }
        return titleIndexes;
    }

    /*
     Requirement: 4a
     Complete the `filterByAuthor()` method.
     See README for additional details.
     */
    private List<Integer> filterByAuthor(String filterAuthor) {
        List<Integer> authorIndexes = new ArrayList<>();
        if (authors.size() == 0) return authorIndexes;

        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).toLowerCase().contains(filterAuthor.toLowerCase())) {
                authorIndexes.add(i);
            }
        }
        return authorIndexes;
    }

    /*
     Requirement: 5a
     Complete the `filterByPublishedYear()` method.
     See README for additional details.
     */
    private List<Integer> filterByPublishedYear(int filterYear) {
        List<Integer> yearIndexes = new ArrayList<>();
        if (publishedYears.size() == 0) return yearIndexes;

        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) == filterYear) {
                yearIndexes.add(i);
            }
        }
        return yearIndexes;
    }

    /*
     Requirement: 6a
     Complete the `filterByPublishedYearRange()` method.
     See README for additional details.
     */
    private List<Integer> filterByPublishedYearRange(int filterFromYear, int filterToYear) {
        List<Integer> yearRangeIndexes = new ArrayList<>();
        if (publishedYears.size() == 0) return yearRangeIndexes;

        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) >= filterFromYear && publishedYears.get(i) <= filterToYear) {
                yearRangeIndexes.add(i);
            }
        }
        return yearRangeIndexes;
    }

    /*
     Requirement: 7a
     Add the `private List<Integer> findMostRecentBooks()` method.
     See README for additional details.
     */
    private List<Integer> findMostRecentBooks() {
        List<Integer> recentBooksIndexes = new ArrayList<>();
        if (publishedYears.size() == 0) return recentBooksIndexes;

        recentBooksIndexes.add(0);
        int largestYear = publishedYears.get(0);
        for (int i = 1; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) > largestYear) {
                recentBooksIndexes.clear();
                recentBooksIndexes.add(i);
                largestYear = publishedYears.get(i);
            } else if (publishedYears.get(i) == largestYear) {
                recentBooksIndexes.add(i);
            }
        }

        return recentBooksIndexes;
    }

    /*
     Requirement: 8a
     Complete the `filterByPrice()` method.
     See README for additional details.
     */
    private List<Integer> filterByPrice(double filterPrice) {
        List<Integer> priceIndexes = new ArrayList<>();
        if (prices.size() == 0) return priceIndexes;

        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i).doubleValue() == filterPrice) {
                priceIndexes.add(i);
            }
        }
        return priceIndexes;
    }

    /*
     Requirement: 9a
     Complete the `filterByPriceRange()` method.
     See README for additional details.
     */
    private List<Integer> filterByPriceRange(double filterFromPrice, double filterToPrice) {
        List<Integer> priceRangeIndexes = new ArrayList<>();
        if (prices.size() == 0) return priceRangeIndexes;

        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i).doubleValue() >= filterFromPrice && prices.get(i).doubleValue() <= filterToPrice) {
                priceRangeIndexes.add(i);
            }
        }
        return priceRangeIndexes;
    }

    /*
     Requirement: 10a
     Add the `private List<Integer> findLeastExpensiveBooks()` method.
     See README for additional details.
     */
    private List<Integer> findLeastExpensiveBooks() {
        List<Integer> cheapestPriceIndexes = new ArrayList<>();
        if (prices.size() == 0) return cheapestPriceIndexes;

        cheapestPriceIndexes.add(0);

        double cheapestPrice = prices.get(0).doubleValue();

        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i).doubleValue() < cheapestPrice) {
                cheapestPriceIndexes.clear();
                cheapestPriceIndexes.add(i);
                cheapestPrice = prices.get(i).doubleValue();

            } else if (prices.get(i).doubleValue() == cheapestPrice) {
                cheapestPriceIndexes.add(i);
            }
        }

        return cheapestPriceIndexes;
    }


    // UI methods

    private void printMainMenu() {
        System.out.println("1: Display data and subsets");
        System.out.println("2: Search books");
        System.out.println("0: Exit");
        System.out.println();
    }

    private void printDataAndSubsetsMenu() {
        System.out.println("1: Display dataset");
        System.out.println("2: Display titles");
        System.out.println("3: Display authors");
        System.out.println("4: Display published years");
        System.out.println("5: Display prices");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void printSearchBooksMenu() {
        System.out.println("1: Search by title");
        System.out.println("2: Search by author");
        System.out.println("3: Search by published year");
        System.out.println("4: Search by published year range");
        System.out.println("5: Find most recent books");
        System.out.println("6: Search by price");
        System.out.println("7: Search by price range");
        System.out.println("8: Find least expensive books");
        System.out.println("0: Return to main menu");
        System.out.println();
    }

    private void displayDataset(String[] dataset) {
        System.out.println("Dataset");
        System.out.println("-------");
        for (String data : dataset) {
            System.out.println(data);
        }
        System.out.println();
        promptForReturn();
    }

    private void displayTitlesList(List<String> titles) {
        System.out.println("Titles");
        System.out.println("-------");
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(i + ": " + titles.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayAuthorsList(List<String> authors) {
        System.out.println("Authors");
        System.out.println("-------");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println(i + ": " + authors.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayPublishedYearsList(List<Integer> publishedYears) {
        System.out.println("Published Years");
        System.out.println("---------------");
        for (int i = 0; i < publishedYears.size(); i++) {
            System.out.println(i + ": " + publishedYears.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private void displayPricesList(List<BigDecimal> prices) {
        System.out.println("Prices");
        System.out.println("------");
        for (int i = 0; i < prices.size(); i++) {
            System.out.println(i + ": " + prices.get(i));
        }
        System.out.println();
        promptForReturn();
    }

    private int promptForMenuSelection(String prompt) {
        System.out.print(prompt);
        int menuSelection;
        try {
            menuSelection = Integer.parseInt(keyboard.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    private String promptForString(String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private int promptForPublishedYear(String prompt) {
        int year;
        while (true) {
            System.out.println(prompt);
            try {
                year = Integer.parseInt(keyboard.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("The year provided is not well-formed. It must be YYYY.");
            }
        }
        return year;
    }

    private double promptForPrice(String prompt) {
        double price;
        while (true) {
            System.out.println(prompt);
            try {
                price = Double.parseDouble(keyboard.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("The price provided is not a valid monetary value.");
            }
        }
        return price;
    }

    private void promptForReturn() {
        System.out.println("Press RETURN to continue.");
        keyboard.nextLine();
    }
}
