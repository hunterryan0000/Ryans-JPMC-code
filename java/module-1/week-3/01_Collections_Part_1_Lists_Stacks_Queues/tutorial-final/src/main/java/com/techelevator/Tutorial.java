package com.techelevator;

import java.util.*;

public class Tutorial {
	
    public static void main(String[] args) {

        String[] nameList2 = new String[4]; // an array, size 4, of Strings
// Arrays come with limitations because they are fixed in size
// If one wanted to add to an array you would have to create a new array, etc.

        /** Collections import between the package and class */
// Lists are type-safe collection of values. w/ a 0-based index
// Lists can grow and shrink in size


        /** Step One: Declare a List...most commonly used Collection interface */
// List syntax: List<Data Type> identifier = new ArrayList<Data Type>().
        List<String> nameList4;
        List<String> nameList = new ArrayList<String>(); //empty with size 0
// You aren't just limited to creating Lists of Strings
// Can't use primitive values like int, double, char, or boolean instead there's
// object versions of the values Integer, Double, Character, Boolean
            // List<LocalDate> dates = new ArrayList<LocalDate>();
            // List<Integer> amounts = new ArrayList<>();
            // List<Employee> employee = new ArrayList<Employee>();


        /** Step Two: Add values to a List */
        nameList.add("Ada");
        nameList.add("Grace");
        nameList.add("Margaret");
        nameList.add("Adele");
        nameList.add("Ryan");
// Lists also have another add() method that lets you add a value at a
// specific index in the array. Remember, the index is zero-based:
        //For Example:
        nameList.add(0, "Ryan");
        nameList.add(0, "John");

// You can create Lists and initialize them with values if you know them ahead of time:
        //For Example(Marvel):
        List<String> marvelCharacters = new ArrayList<>(Arrays.asList("Spiderman", "Black Panther", "Hulk"));
        System.out.println(marvelCharacters);


        for (String name: marvelCharacters){
            System.out.println(name);
        }
        System.out.println();


        /** Step Three: Looping through a List in a for loop */


        for (int i = 0; i < nameList2.length; i++) {} // for loop for an array





        /** Step Four: Remove an item */
        nameList.remove("Ada");
        nameList.remove(0);
        for (int i = 0; i < nameList.size(); i++) {
//            nameList.remove(0);
            System.out.println("The name at index " + i + " is " + nameList.get(i));
        }




        /** Step Five: Looping through List in a for-each loop */
// Straightforward syntax
// Only can move forward from the first item
// Every item is retrieved

/* for-each loop syntax:
         (<Data type> <identifier> : <expression>) {
           <body>
        }
*/
        System.out.println();
    for (String name: nameList){
        System.out.println("Name: " + name);
    }

    /**Array2List*/
        System.out.println();
    String[] stringArray = new String[]{"Red", "Green", "Blue"};
         List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < stringArray.length; i++) {
            arrayList.add(stringArray[i]);
        }
        for (String name: arrayList){
            System.out.println("List names: "+ name);
        }

    /**List2Array*/
        System.out.println();
        List<String> stringList = new ArrayList<>(Arrays.asList("Red", "Green", "Blue"));
        String[] stringArray2 = new String[stringList.size()];
        for (int i = 0; i < stringList.size(); i++) {
            stringArray2[i] = stringList.get(i);
        }
        System.out.println();
        for (String result: stringArray2){
            System.out.println("Array names: " + result);
        }
    }

}
