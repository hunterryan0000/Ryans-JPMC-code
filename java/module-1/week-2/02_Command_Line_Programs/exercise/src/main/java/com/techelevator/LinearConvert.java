package com.techelevator;

import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {

		final double FEET_PER_METER = 3.2808399;
		Scanner input = new Scanner(System.in);

		System.out.print("Please enter the length: ");
		String value = input.nextLine();
		int length = Integer.parseInt(value);

		System.out.print("Is the measure in (m)eter or (f)eet? ");
		String unitType = input.nextLine();

		int processedLength;
		String processedUnitType;
		if (unitType.equals("m")) {
			processedLength = (int) (length * FEET_PER_METER);
			processedUnitType = "f";
		} else {
			processedLength = (int) (length / FEET_PER_METER);
			processedUnitType = "m";
		}

		System.out.println(length + unitType + " equals " + processedLength + processedUnitType);


	}

}
