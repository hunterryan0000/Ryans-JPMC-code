package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    // DO NOT REMOVE the DEFAULT_NUMBER_OF_... constants!!!!!
    public static final int DEFAULT_NUMBER_OF_COMPACT_SLOTS = 3;
    public static final int DEFAULT_NUMBER_OF_MIDSIZE_SLOTS = 5;
    public static final int DEFAULT_NUMBER_OF_FULLSIZE_SLOTS = 2;

    private List<Car> compactSlots = new ArrayList<>();
    private List<Car> midsizeSlots = new ArrayList<>();
    private List<Car> fullsizeSlots = new ArrayList<>();

    private final String name;
    private boolean open;
    private final int numberOfCompactSlots;
    private final int numberOfMidsizeSlots;
    private final int numberOfFullsizeSlots;

    public ParkingLot(String name) {
        this(name, false);
    }

    public ParkingLot(String name, boolean open) {
        this(name, open, DEFAULT_NUMBER_OF_COMPACT_SLOTS, DEFAULT_NUMBER_OF_MIDSIZE_SLOTS, DEFAULT_NUMBER_OF_FULLSIZE_SLOTS);
    }

    public ParkingLot(String name, boolean open, int numberOfCompactSlots, int numberOfMidsizeSlots, int numberOfFullsizeSlots) {
        this.name = name;
        this.open = open;
        this.numberOfCompactSlots = numberOfCompactSlots;
        this.numberOfMidsizeSlots = numberOfMidsizeSlots;
        this.numberOfFullsizeSlots = numberOfFullsizeSlots;
        compactSlots = emptySlots(numberOfCompactSlots);
        midsizeSlots = emptySlots(numberOfMidsizeSlots);
        fullsizeSlots = emptySlots(numberOfFullsizeSlots);
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getNumberOfCompactSlots() {
        return numberOfCompactSlots;
    }

    public int getNumberOfMidsizeSlots() {
        return numberOfMidsizeSlots;
    }

    public int getNumberOfFullsizeSlots() {
        return numberOfFullsizeSlots;
    }

    public int getLotSize() {
        return numberOfCompactSlots + numberOfMidsizeSlots + numberOfFullsizeSlots;
    }

    public int numberOfAvailableSlots(String carType) {
        int availableSlots = 0;
        List<Car> carTypeSlots = getCarTypeSlots(carType);
        for (Car slot : carTypeSlots) {
            if (slot == null) {
                availableSlots++;
            }
        }
        return availableSlots;
    }

    public boolean park(Car car) {
        boolean parked = false;
        List<Car> carTypeSlots = getCarTypeSlots(car.getType());
        for (int i = 0; i < carTypeSlots.size(); i++) {
            if (carTypeSlots.get(i) == null) {
                carTypeSlots.set(i, car);
                parked = true;
                break;
            }
        }
        return parked;
    }

    public Car exit(String carType, String license) {
        Car exitingCar = null;
        List<Car> carTypeSlots = getCarTypeSlots(carType);
        for (int i = 0; i < carTypeSlots.size(); i++) {
            if (carTypeSlots.get(i) != null && carTypeSlots.get(i).getLicense().equals(license)) {
                exitingCar = carTypeSlots.get(i);
                carTypeSlots.set(i, null);
                break;
            }
        }
        return exitingCar;
    }

    private List<Car> emptySlots(int numberOfNulls) {
        List<Car> nullCars = new ArrayList<>();
        for (int i = 0; i < numberOfNulls; i++) {
            nullCars.add(null);
        }
        return nullCars;
    }

    private List<Car> getCarTypeSlots(String carType) {
        List<Car> carTypeSlots;
        if (carType.equals(Car.COMPACT)) {
            carTypeSlots = compactSlots;
        } else if (carType.equals(Car.MIDSIZE)) {
            carTypeSlots = midsizeSlots;
        } else if (carType.equals(Car.FULL_SIZE)){
            carTypeSlots = fullsizeSlots;
        } else {
            // Unknown car type has no slots
            carTypeSlots = new ArrayList<>();
        }
        return carTypeSlots;
    }
}
