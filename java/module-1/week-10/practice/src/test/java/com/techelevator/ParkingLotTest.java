package com.techelevator;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.UUID;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingLotTest {

    private String name = "You Park We Tow";
    private boolean open = true;

    private Car compactCar1 = new Car(Car.COMPACT, randomUUIDString());
    private Car compactCar2 = new Car(Car.COMPACT, randomUUIDString());
    private Car compactCar3 = new Car(Car.COMPACT, randomUUIDString());
    private Car compactCar4 = new Car(Car.COMPACT, randomUUIDString());

    private Car midsizeCar1 = new Car(Car.MIDSIZE, randomUUIDString());
    private Car midsizeCar2 = new Car(Car.MIDSIZE, randomUUIDString());
    private Car midsizeCar3 = new Car(Car.MIDSIZE, randomUUIDString());

    private Car fullsizeCar1 = new Car(Car.FULL_SIZE, randomUUIDString());
    private Car fullsizeCar2 = new Car(Car.FULL_SIZE, randomUUIDString());

    private Class klass = ParkingLot.class;

    // region Private Helper methods
    private Method findMethod(String methodName, Class<?> returnType, Class<?>... parameterTypes) {
        // Look for a method with a given signature. Assert it's there.
        Method method = SafeReflection.getMethod(klass, methodName, parameterTypes);
        assertNotNull(klass.getSimpleName() + " class must have method " + methodName + "().", method);
        assertTrue(methodName + "() method must return type " + returnType.getSimpleName(),
                method.getReturnType() == returnType);
        return method;
    }

    private Method findNoMethod(String methodName, Class<?> returnType, Class<?>... parameterTypes) {
        // Look for a method with a given signature. Assert it's NOT there.
        Method method = SafeReflection.getMethod(klass, methodName, parameterTypes);
        assertNull(klass.getSimpleName() + " class must NOT have method " + methodName + "().", method);
        return method;
    }

    private String getMethodString(String methodName, Object... args) {
        String msg = methodName + "(";
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                if (i > 0)
                    msg += ", ";
                msg += args[i].toString();
            }
        }
        msg += ")";
        return msg;
    }

    private void verifyMethod(Method method, Object instance, Object expectedResult, Object... args) {
        try {
            // System.out.print("Verifying " + getMethodString(method.getName(), args) +
            // "... ");
            Object actualResult = method.invoke(instance, args);

            if (expectedResult == null) {
                // Expecting null or void result
                if (actualResult == null) {
                    // Got null or void result
                    // System.out.println(getMethodString(method.getName(), args) + " verified.");
                    return;
                } else {
                    // Got a non-null result
                    fail("Expected no result from " + getMethodString(method.getName(), args) + ", but got "
                            + actualResult.toString());
                }
            } else if (actualResult == null) {
                // Fail because actual is null but expected is NOT null
                // Got a non-null result
                fail(getMethodString(method.getName(), args) + " must return " + expectedResult.toString()
                        + ". Actual result was null.");
            }

            // non-null / non-void expected
            assertTrue(
                    getMethodString(method.getName(), args) + " must return " + expectedResult.toString()
                            + ". Actual result was " + actualResult.toString(),
                    expectedResult.equals(actualResult));
            // System.out.println(getMethodString(method.getName(), args) + " verified.");
        } catch (Exception e) {
            fail("Failed to run method " + getMethodString(method.getName(), args) + ". Error was: " + e.getMessage());
        }
    }

    private ParkingLot createParkingLotInstance(String name) {

        ParkingLot parkingLot = null;
        Constructor<ParkingLot> constructor = SafeReflection.getConstructor(klass, String.class);
        try {
            if (constructor == null) {
                fail(klass.getSimpleName() + " must have the required constructor(String)");
            } else {
                // We have a constructor with the proper parameters
                // Create the instance
                parkingLot = constructor.newInstance(name);
            }
        } catch (Exception e) {
            fail("Failed to instantiate ParkingLot");
        }
        return parkingLot;
    }

    private ParkingLot createParkingLotInstance(String name, boolean open) {
        ParkingLot parkingLot = null;
        Constructor<ParkingLot> constructor = SafeReflection.getConstructor(klass, String.class, boolean.class);
        try {
            if (constructor == null) {
                fail(klass.getSimpleName() + " must have the required constructor(String, boolean)");
            } else {
                // We have a constructor with the proper parameters
                // Create the instance
                parkingLot = constructor.newInstance(name, open);
            }
        } catch (Exception e) {
            fail("Failed to instantiate ParkingLot");
        }
        return parkingLot;
    }

    private ParkingLot createParkingLotInstance(String name, boolean open,
            int numberOfCompactSlots, int numberOfMidsizeSlots, int numberOfFullsizeSlots) {
        ParkingLot parkingLot = null;
        Constructor<ParkingLot> constructor = SafeReflection.getConstructor(klass, String.class, boolean.class,
                int.class, int.class, int.class);
        try {
            if (constructor == null) {
                fail(klass.getSimpleName() + " must have the required constructor(String, boolean, int, int, int)");
            } else {
                // We have a constructor with the proper parameters
                // Create the instance
                parkingLot = constructor.newInstance(name, open, numberOfCompactSlots, numberOfMidsizeSlots,
                        numberOfFullsizeSlots);
            }
        } catch (Exception e) {
            fail("Failed to instantiate ParkingLot");
        }
        return parkingLot;
    }
    // endregion Private Helper methods

    // region Tests for properties
    @Test
    public void A01_nameTest() {
        // test single arg constructor
        ParkingLot parkingLot = createParkingLotInstance(name);

        // make sure there is NOT a setter
        Method method = findNoMethod("setName", void.class, String.class);

        // test for getName
        method = findMethod("getName", String.class);

        // Now get the property
        verifyMethod(method, parkingLot, name);
    }

    @Test
    public void A02_openTest() {
        // test for open property being set on single arg constructor
        ParkingLot parkingLot = createParkingLotInstance(name);

        Method isOpen = findMethod("isOpen", boolean.class);
        Method setOpen = findMethod("setOpen", void.class, boolean.class);

        verifyMethod(isOpen, parkingLot, false);

        // test 2 arg constructor
        parkingLot = createParkingLotInstance(name, open);

        // test that open was set by constructor
        verifyMethod(isOpen, parkingLot, open);

        // test setOpen
        verifyMethod(setOpen, parkingLot, null, !open);

        // validate with getOpen
        verifyMethod(isOpen, parkingLot, !open);
    }

    @Test
    public void A03_compactSlotsTest() {
        // test numberOfCompactSlots is set by 2 arg constructor
        ParkingLot parkingLot = createParkingLotInstance(name, open);

        // make sure there is NOT a setter
        Method method = findNoMethod("setNumberOfCompactSlots", int.class);

        // make sure there is a getter
        method = findMethod("getNumberOfCompactSlots", int.class);
        // validate getNumberOfCompactSlots with default value
        verifyMethod(method, parkingLot, ParkingLot.DEFAULT_NUMBER_OF_COMPACT_SLOTS);

        int numberOfCompactSlots = 5;
        // test 5 arg constructor
        parkingLot = createParkingLotInstance(name, open, numberOfCompactSlots, 9, 7);
        // verify specified number of slots is set by the constructor
        verifyMethod(method, parkingLot, numberOfCompactSlots);
    }

    @Test
    public void A04_midsizeSlotsTest() {
        // test numberOfMidsizeSlots is set by 2 arg constructor
        ParkingLot parkingLot = createParkingLotInstance(name, open);

        // make sure there is NOT a setter
        Method method = findNoMethod("setNumberOfMidsizeSlots", int.class);

        // make sure there is a getter
        method = findMethod("getNumberOfMidsizeSlots", int.class);
        // validate getNumberOfMidsizeSlots with default value
        verifyMethod(method, parkingLot, ParkingLot.DEFAULT_NUMBER_OF_MIDSIZE_SLOTS);

        int numberOfMidsizeSlots = 9;
        // test 5 arg constructor
        parkingLot = createParkingLotInstance(name, open, 5, numberOfMidsizeSlots, 7);
        // verify specified number of slots is set by the constructor
        verifyMethod(method, parkingLot, numberOfMidsizeSlots);
    }

    @Test
    public void A05_fullsizeSlotsTest() {
        // test numberOfFullsizeSlots is set by 2 arg constructor
        ParkingLot parkingLot = createParkingLotInstance(name, open);

        // make sure there is NOT a setter
        Method method = findNoMethod("setNumberOfFullsizeSlots", int.class);

        // make sure there is a getter
        method = findMethod("getNumberOfFullsizeSlots", int.class);
        // validate getNumberOfFullsizeSlots with default value
        verifyMethod(method, parkingLot, ParkingLot.DEFAULT_NUMBER_OF_FULLSIZE_SLOTS);

        int numberOfFullsizeSlots = 7;
        // test 5 arg constructor
        parkingLot = createParkingLotInstance(name, open, 5, 9, numberOfFullsizeSlots);
        // verify specified number of slots is set by the constructor
        verifyMethod(method, parkingLot, numberOfFullsizeSlots);
    }
    // endregion Test for properties

    // region Test for methods
    @Test
    public void A06_getLotSizeTest() {
        // use 2arg constructor for default number of slots
        ParkingLot parkingLot = createParkingLotInstance(name, open);

        int parkingLotSlots = ParkingLot.DEFAULT_NUMBER_OF_COMPACT_SLOTS + ParkingLot.DEFAULT_NUMBER_OF_MIDSIZE_SLOTS
                + ParkingLot.DEFAULT_NUMBER_OF_FULLSIZE_SLOTS;

        Method method = findMethod("getLotSize", int.class);

        verifyMethod(method, parkingLot, parkingLotSlots);
    }

    @Test
    public void A07_parkTest() {
        // use 5arg constructor to specify number of starting slots
        ParkingLot parkingLot = createParkingLotInstance(name, open, 3, 2, 1);

        Method method = findMethod("park", boolean.class, Car.class);

        // test parking a compact car
        verifyMethod(method, parkingLot, true, compactCar1);
        // park 3 more cars, should fail on final - no slots remaining
        verifyMethod(method, parkingLot, true, compactCar2);
        verifyMethod(method, parkingLot, true, compactCar3);
        verifyMethod(method, parkingLot, false, compactCar4);

        // test parking a midsize car
        verifyMethod(method, parkingLot, true, midsizeCar1);
        // park 3 more cars, should fail on final - no slots remaining
        verifyMethod(method, parkingLot, true, midsizeCar2);
        verifyMethod(method, parkingLot, false, midsizeCar3);

        // test parking a fullsize car
        verifyMethod(method, parkingLot, true, fullsizeCar1);
        // park another fullsize car should fail - no slots remaining
        verifyMethod(method, parkingLot, false, fullsizeCar2);
    }

    @Test
    public void A08_exitTest() {
        // use 5arg constructor to specify number of starting slots
        ParkingLot parkingLot = createParkingLotInstance(name, open, 1, 1, 1);

        Method park = findMethod("park", boolean.class, Car.class);

        verifyMethod(park, parkingLot, true, compactCar1);
        verifyMethod(park, parkingLot, true, midsizeCar1);
        verifyMethod(park, parkingLot, true, fullsizeCar1);

        Method exit = findMethod("exit", Car.class, String.class, String.class);

        // test exit for a compact car
        verifyMethod(exit, parkingLot, compactCar1, Car.COMPACT, compactCar1.getLicense());
        // test to ensure compactCar1 is no longer in list
        verifyMethod(exit, parkingLot, null, Car.COMPACT, compactCar1.getLicense());

        // test exit for a compact car
        verifyMethod(exit, parkingLot, midsizeCar1, Car.MIDSIZE, midsizeCar1.getLicense());
        // test to ensure compactCar1 is no longer in list
        verifyMethod(exit, parkingLot, null, Car.MIDSIZE, midsizeCar1.getLicense());

        // test exit for a compact car
        verifyMethod(exit, parkingLot, fullsizeCar1, Car.FULL_SIZE, fullsizeCar1.getLicense());
        // test to ensure compactCar1 is no longer in list
        verifyMethod(exit, parkingLot, null, Car.FULL_SIZE, fullsizeCar1.getLicense());
    }

    @Test
    public void A09_numberOfAvailableSlotsTest() {
        // use 2arg constructor for default number of slots
        ParkingLot parkingLot = createParkingLotInstance(name, open);

        Method method = findMethod("numberOfAvailableSlots", int.class, String.class);

        verifyMethod(method, parkingLot, ParkingLot.DEFAULT_NUMBER_OF_COMPACT_SLOTS, Car.COMPACT);
        verifyMethod(method, parkingLot, ParkingLot.DEFAULT_NUMBER_OF_MIDSIZE_SLOTS, Car.MIDSIZE);
        verifyMethod(method, parkingLot, ParkingLot.DEFAULT_NUMBER_OF_FULLSIZE_SLOTS, Car.FULL_SIZE);
    }

    // endregion Test for methods
    private String randomUUIDString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
