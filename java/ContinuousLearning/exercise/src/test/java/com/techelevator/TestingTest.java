package com.techelevator;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class TestingTest {

    @Test
    void primeFactors() {
        Testing test = new Testing();
        int[] nums = new int[]{23, 29};
        int[] nums1 = new int[]{2, 3};
        int[] nums2 = new int[]{2, 2, 7};
        int[] expected = {13};

        assertArrayEquals(expected, test.primeFactors(13));
        assertArrayEquals(nums, test.primeFactors(667));
        assertArrayEquals(nums1, test.primeFactors(6));
        assertArrayEquals(nums2, test.primeFactors(28));
        assertThrows(IllegalArgumentException.class, () -> test.primeFactors(-10));
    }
}
