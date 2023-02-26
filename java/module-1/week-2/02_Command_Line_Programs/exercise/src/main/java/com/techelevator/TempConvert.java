package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Please enter the temperature: ");
		String value = input.nextLine();
		int temperature = Integer.parseInt(value);

		System.out.print("Is the temperature in (C)elsius or (F)arenheit? ");
		String degreeType = input.nextLine();

		int processedTemperature;
		String processedDegree;
		if (degreeType.equals("C")) {
			processedTemperature = (int) (temperature * 1.8 + 32);
			processedDegree = "F";
		} else {
			processedTemperature = (int) (( temperature - 32 ) / 1.8);
			processedDegree = "C";
		}

		System.out.println(temperature + degreeType + " equals " + processedTemperature + processedDegree);



	}

}
