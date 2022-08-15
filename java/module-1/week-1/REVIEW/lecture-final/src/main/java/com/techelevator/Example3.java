package com.techelevator;

public class Example3 {

    /*
     1. Given an age, return "Adult", "Child" or "Teen". Teens are ages 13-17 and children are under 13.
     ageGroup(18) → "Adult"
     ageGroup(13) → "Teen"
     ageGroup(12) → "Child"
    */
    public String ageGroup(int age) {
        if (age >= 18) {
            return "Adult";
        } else if (age >= 13) {
            return "Teen";
        } else {
            return "Child";
        }
    }

    /*
     2. You have a green lottery ticket, with ints a, b, and c on it. If the numbers are all different from each other,
     the result is 0. If all of the numbers are the same, the result is 20. If two of the numbers are the same, the
     result is 10.
     greenTicket(1, 2, 3) → 0
     greenTicket(2, 2, 2) → 20
     greenTicket(1, 1, 2) → 10
    */
    public int greenTicket(int a, int b, int c) {
        int winnings = 0;

        if (a == b && b == c) {
            winnings = 20;
        } else if (a == b || b == c || a == c) {
            winnings = 10;
        }

        return winnings;
    }

    /*
     3. Given 2 int values greater than 0, return whichever value is nearest to 21 without going over.
     Return 0 if they both go over.
     blackjack(19, 21) → 21
     blackjack(21, 19) → 21
     blackjack(19, 22) → 19
    */
    public int blackjack(int a, int b) {
        if (a > 21 && b > 21) {
            return 0;
        } else if (a > 21) {
            return b;
        } else if (b > 21) {
            return a;
        } else if (21 - a < 21 - b) {
            return a;
        } else {
            return b;
        }
    }
}
