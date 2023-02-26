package com.techelevator;

import java.sql.SQLOutput;

public class Tutorial {

//    private final int TENDOLLARBILL = 10;
//
//    int[] wallet = {10, 5, 5 ,20, 100, 10, 5};
//
//    public int howManyTenDollarBillsAreInMyWallet (int[] wallet) {
//        int count = 0;
//
//        for (int i = 0; i < wallet.length; i++) {
//            if (wallet[i] == TENDOLLARBILL) {
//                count++;
//            }
//        }
//        return count;
//    }


//    private final int TENDOLLARBILL = 10;
//
//    int[] oldWallet = {10, 5, 5 ,20, 100, 10, 5};
//
//    public int[] putOnly10sInNewWallet (int[] oldWallet) {
//        int[] newWallet = new int[oldWallet.length];
//
//        for (int i = 0; i < oldWallet.length; i++) {
//            if (oldWallet[i] == TENDOLLARBILL) {
//                newWallet[i] = oldWallet[i];
//            }
//        }
//        return newWallet;
//    }

    public static void main(String[] args) {

        // write your code here

        int i;

//        for (i = 0; i <= 5; i++) {
//            System.out.println(i);
//        }

//        for (i = 1; i <= 5; i++){
//            System.out.println(i);
//        }

//        for (i = 0; i <= 5; i=i+2){
//            System.out.println(i);
//        }

        // show fori short cut


//        for (i = 10; i >= 0; i--) {
//            System.out.println(i);
//        }

        /**When you want to work with collections of values, you use arrays.
         * Rather than creating multiple variables, you create a single variable name
         * that can represent multiple values.*/

        int[] forecastTemperatures = new int[5];
        forecastTemperatures[0] = 72;
        forecastTemperatures[1] = 78;
        forecastTemperatures[2] = 58;
        forecastTemperatures[3] = 79;
        forecastTemperatures[4] = 74;
//        System.out.println(forecastTemperatures.length);

//        forecastTemperatures[2] = forecastTemperatures[2] + 10;
         forecastTemperatures[2] += 10;
//        System.out.println(forecastTemperatures[2]);

//        for (i = 0; i < forecastTemperatures.length; i++) /*(i = 0; i <= forecastTemperatures.length; i++)*/ {
//            System.out.println(forecastTemperatures[i]);
//        }

        int highestTemperatureValue = forecastTemperatures[0];
        int highestTemperatureIndex = 0;

        for (int j = 1; j < forecastTemperatures.length; j++) {
            if (forecastTemperatures[j] > highestTemperatureValue) {
                highestTemperatureValue = forecastTemperatures[j];
                highestTemperatureIndex = j;
            }
        }

        System.out.println("The highest temperature is " + highestTemperatureValue);
         System.out.println("The highest temperature is in " + (highestTemperatureIndex + 1) + " days");
    }
}
