package Sorting;

import java.util.Arrays;

public class MergeSort___DivideConquer {
    /**
     * Divide and conquer is a strategy that breaks a problem into smaller
     * subproblems, solves each subproblem independently, and combines their
     * solutions to solve the original problem. MergeSort is a classic example,
     * useful for sorting large datasets efficiently.
     */
    // Recursive method to sort an array using MergeSort
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) { // Check if the subarray has more than one element
            int mid = left + (right - left) / 2; // Find the midpoint of the current subarray

            mergeSort(array, left, mid); // Recursively sort the first half of the array
            mergeSort(array, mid + 1, right); // Recursively sort the second half of the array

            // After both halves are sorted, merge them into a single sorted array
            merge(array, left, mid, right);
        }
    }


    // Merge two sorted subarrays into a single sorted array
    private static void merge(int[] array, int left, int mid, int right) {
        // Temporary arrays to hold the left and right subarrays
        int[] L = Arrays.copyOfRange(array, left, mid + 1);
        int[] R = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0; // Pointers for current index of temp subarrays L and R

        int k = left; // Start from the left index of the merged subarray
        while (i < L.length && j < R.length) { // Compare elements from L and R and merge
            if (L[i] <= R[j]) {
                array[k] = L[i]; // If L's element is smaller, add it to the array
                i++; // Move L's pointer
            } else {
                array[k] = R[j]; // If R's element is smaller, add it to the array
                j++; // Move R's pointer
            }
            k++; // Move to the next position in merged array
        }

        // Copy any remaining elements of L[] and R[] back into the main array
        while (i < L.length) { // For L
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < R.length) { // For R
            array[k] = R[j];
            j++;
            k++;
        }
    }


    public static void main(String[] args) {
        int[] transactions = {230, 110, 500, 400, 310};
        mergeSort(transactions, 0, transactions.length - 1);
        System.out.println("Sorted Transactions using mergeSort: " + Arrays.toString(transactions));
        System.out.println();
        int[] transactions2 = {900, 999, 109, 901, 999, 100};
        Arrays.sort(transactions2);
        System.out.println("Sorted Transactions using Arrays.sort: " + Arrays.toString(transactions2));
    }
}
