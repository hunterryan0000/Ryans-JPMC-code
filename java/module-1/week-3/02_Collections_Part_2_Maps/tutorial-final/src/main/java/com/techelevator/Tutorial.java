package com.techelevator;

import java.util.Map;
import java.util.HashMap;

public class Tutorial {
	

    public static void main(String[] args) {

// Maps aren't indexed like Lists. There's no index, zero-based or otherwise.
// You can't ask for the first, second, or third item in a Map.
// This is why Maps are considered unordered data structures.

        /** Step One: Declare a Map */
// Syntax: Map<K, V> identifier = new HashMap<K, V>();
// Key and Values serves as data types
// Keys must be unique, Values can repeat
        Map<String, String> projects = new HashMap<String, String>();


        /** Step Two: Add items to a Map */
        projects.put("Ada", "Inventing Computer Programming");
        projects.put("Grace", "COBOL");
        projects.put("Margaret", "Apollo Guidance Computer");
        projects.put("Adele", "Graphical User Interfaces");


        /** Step Four: Remove an item (this comment doesn't appear in the student starter) */
        projects.remove("Grace");


        /** Step Three: Loop through a Map */
        for (Map.Entry<String, String> project : projects.entrySet()) {
            System.out.println("The key '" + project.getKey() + "' is linked to the value '" + project.getValue() + "'");
        }
    }

}
