package com.techelevator;

public class HotelReservation {

    private String name;
    private int numberOfNights;
    private int nightlyRate;

    public HotelReservation(String name, int numberOfNights, int nightlyRate) {
        this.name = name;
        this.numberOfNights = numberOfNights;
        this.nightlyRate = nightlyRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public int getNightlyRate() {
        return nightlyRate;
    }

    public void setNightlyRate(int nightlyRate) {
        this.nightlyRate = nightlyRate;
    }

    public int getEstimatedTotal() {
        return this.nightlyRate * this.numberOfNights;
    }

    public int getActualTotal(boolean requiresCleaning, boolean usedMinibar) {
//        int MINIBAR_FEE = 15;
//        int CLEANING_FEE = 25;
//        int fees = 0;
//        if (usedMinibar) {
//            fees += MINIBAR_FEE;
//            if (requiresCleaning) {
//                fees += 2 * CLEANING_FEE;
//            }
//        } else {
//            if (requiresCleaning) {
//                fees += CLEANING_FEE;
//            }
//        }
//        return this.getEstimatedTotal() + fees;
//    }
        int totalFees = 0;
        final int MINIBAR_FEE = 15;
        int cleaningFee = 25;

        if (usedMinibar) {
            totalFees += MINIBAR_FEE;
            cleaningFee *= 2;
        }

        if (requiresCleaning) {
            totalFees += cleaningFee;
        }

        return totalFees + getEstimatedTotal();

    }


    @Override
    public String toString() {
        return name + ":" + getEstimatedTotal();
        //return String.format("%s:%d", getName(), getEstimatedTotal());

    }
}
