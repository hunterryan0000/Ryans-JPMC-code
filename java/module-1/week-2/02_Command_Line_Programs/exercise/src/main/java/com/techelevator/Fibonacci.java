package com.techelevator;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {

		final boolean USE_RECURSION = true; //toggle this to switch between recursive and non-recursive methods on the suggestion of Douglas Doughty
		Scanner input = new Scanner(System.in);

		System.out.print("Please enter a number: ");
		String value = input.nextLine();
		int startingValue = Integer.parseInt(value);

		System.out.print("0, 1");
		if (startingValue > 0) {
			if (USE_RECURSION) {
				for (int i = 1; i <= startingValue; i++) {
					int nextDigit = fibonacciRecursion(i);
					if (nextDigit > startingValue) {
						break;
					}
					System.out.print(", " + nextDigit);
				}
			} else {
				fibonacci(startingValue);
			}
		}
	}

	//non-recursive method
	public static void fibonacci(int startingValue) {
		int firstValue = 0;
		int secondValue = 1;
		int sumValue = firstValue + secondValue;

		while (sumValue <= startingValue) {
			System.out.print(", " + sumValue);
			firstValue = secondValue;
			secondValue = sumValue;
			sumValue = firstValue + secondValue;
		}
	}

	//recursive method
	public static int fibonacciRecursion(int startingValue) {
		if (startingValue == 1) {
			return 1;
		}

		if (startingValue == 2) {
			return 2;
		}

		return fibonacciRecursion(startingValue - 2) + fibonacciRecursion(startingValue - 1);



	}

}
