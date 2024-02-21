package Searching;

public class BinarySearch___DivideConquer {
    /**
     * int[] arr: The sorted array in which we are searching for the element.
     *
     * int l: The starting index of the array segment being searched.
     * Initially, this would be 0 for the first call.
     *
     *  int r: The ending index of the array segment being searched.
     * Initially, this would be arr.length - 1 for the first call,
     * indicating the last index of the array.
     *
     * int x: The value being searched for in the array.
     */
    // A recursive binary search function. It returns location of x in given array arr[l..r]
    public static int binarySearch(int[] arr, int l, int r, int x) {

        // first checks if r (the right or end index) is greater than or equal
        // to l (the left or start index).
        if (r >= l) {
            // The binary search algorithm efficiently narrows down the search space
            // by half at each step, leading to a time complexity of O(log n),
            // where n is the number of elements in the array.
            int mid = l + (r - l) / 2;

            // If the element is present at the middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then it can only be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }

        // Element is not present in array
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 4, 10, 40 };
        int n = arr.length;
        int x = 10;
        int result = binarySearch(arr, 0, n - 1, x);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index " + result);
    }
}
