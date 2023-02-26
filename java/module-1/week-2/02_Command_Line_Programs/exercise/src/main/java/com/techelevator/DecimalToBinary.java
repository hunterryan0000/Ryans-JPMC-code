package com.techelevator;

import java.util.Scanner;

public class DecimalToBinary {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.print("Please enter in a series of decimal values (separated by spaces): ");
		String numString = input.nextLine();
		String[] numStringArray = numString.split(" ");

		for (int i = 0; i < numStringArray.length; i++) {
			toBinary(Integer.parseInt(numStringArray[i]));
		}

	}

	public static void toBinary(int decimal) {
		System.out.print(decimal + " in binary is ");
		String binaryString = "";
		while (decimal >= 1) {
			binaryString = decimal % 2 + binaryString;
			decimal /= 2;
		}
		System.out.println(binaryString);



	}

}