package com.techelevator;

public class Tutorial {
    public static void main(String[] args) {
        /* *********** Logical Branching Tutorial ***********
         * In this tutorial, you will write some boolean expressions and use them to
         * control the flow of your program using if / else statements.
         * This program tells the user how to prepare for their day based on weather conditions.
         * *************************************************/

        // Step One: Greet the user
        System.out.println("**************************************");
        System.out.println("*** Welcome to the Weather Station ***");
        System.out.println("**************************************");

        // Step Two: Declare and initialize Weather Station variables
        int highTemperatureF = 55; //important variable names
        boolean precipitationExpected = true;
        final int FREEZING_TEMPERATURE_F = 32;

        // Step Three: Report today's expected conditions to the user.
        System.out.println("Expected high temperature: " + highTemperatureF + "\u00B0F."); // Degrees character in Unicode: \u00B0
        if (precipitationExpected) { // if (condition)
            System.out.println("The chance of precipitation is high."); //statement or block performed if condition is true
        } else {
            System.out.println("The chance of precipitation is low."); //statement or block performed if condition is false
        }

        // Step Four: Determine if the user should bring a coat. (when the temperature < 65°F)
        if (highTemperatureF < 65) { // less than comparison operator
            System.out.println("* Don't forget to bring a coat today.");
        }

        // Step Five: Determine if the user should bring an umbrella
        boolean isAboveFreezing = highTemperatureF > FREEZING_TEMPERATURE_F; // greater than comparison operator
        if (precipitationExpected && isAboveFreezing) {
            System.out.println("* Be sure to pack an umbrella!");
        }

        // Step Six: Advise the user on the type of jacket they should be wearing. (<= 0°F, <= freezing, < 65°F)
        if (highTemperatureF <= 0) { // less than or equal to comparison operator
            System.out.println("* Bundle up! Wear everything you have!");
        } else if (!isAboveFreezing) { // NOT logical operator...i.e. is not above freezing
            //other logical operators A&&B A||B A^B
            System.out.println("* Please wear a warm winter coat!");
        } else if (highTemperatureF < 65) {
            System.out.println("* Take along a light jacket.");
        }

        // Step Seven: If the temperature delta for the day is more than 20°F, advise the user to dress in layers.
        int lowTemperatureF = 34;
        if (highTemperatureF - lowTemperatureF > 20) {
            System.out.println("* Dress in layers today, as the temperature may swing dramatically.");
        }
    }
}
