package com.techelevator;

import java.util.*;
import java.util.stream.Collectors;

public class Testing {

    int[] primeFactors(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed");
        }

        List<Integer> nums = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                nums.add(i);
                n /= i;
            }
        }
        return nums.stream().mapToInt(Integer::intValue).toArray();
    }

    public List<Integer> oddOnly(int[] nums) {
        // Use the Arrays.stream method to create a stream from the nums array.
        return Arrays.stream(nums)
                // Apply a filter to the stream that keeps only the odd numbers.
                // This is done by checking if a number is odd using 'num % 2 == 1'.
                .filter(num -> num % 2 == 1)
                // Since the stream is of int primitives, we need to box each int into an Integer.
                // This is necessary to collect them into a List<Integer>.
                .boxed()
                // Finally, collect the elements of the stream into a List.
                // Collectors.toList() provides a convenient way to achieve this.
                .collect(Collectors.toList());
    }


    public boolean endsLy(String str) {
        return str.endsWith("ly");
    }

    public boolean has12(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num == 1 || num == 2) {
                list.add(num);
            }
        }
        return list.indexOf(1) < list.indexOf(2);
    }

    public boolean doubleX(String str) {
        int index = str.indexOf('x');
        // Check if 'x' is found and not at the end of the string
        if (index != -1 && index < str.length() - 1) {
            return str.charAt(index + 1) == 'x';
        }
        return false;
    }

    public static int[] biggerTwo(int[] a, int[] b) {
        int sum1 = a[0] + a[1];
        int sum2 = b[0] + b[1];

        if (sum1 >= sum2) {
            return a;
        } else return b;
    }

    public boolean lessBy10(int a, int b, int c) {
        return Math.abs(a - b) >= 10 || Math.abs(a - c) >= 10 || Math.abs(b - c) >= 10;
    }

    public String extraEnd(String str) {
        return str.substring(str.length() - 2).repeat(3);
    }

    public int arrayCount9(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = nums[i] == 9 ? count + 1 : 0;
        }
        return count;
    }

    public boolean evenlySpaced(int a, int b, int c) {
        if (a < b && b < c) {
            return Math.abs(a - b) == Math.abs(b - c);
        }
        return Math.abs(a - c) == Math.abs(b - a);

    }

    public boolean foundIntTwice(List<Integer> nums, int value) {
        int count = 0;
        for (Integer num : nums) {
            if (num == value) {
                count++;
            }
        }
        return count == 2;
    }

    public String comboString(String a, String b) {
        int shorter = Math.min(a.length(), b.length());

        if (a.length() == shorter) {
            return a + b + a;
        } else {
            return b + a + b;
        }
    }

    public int countXX(String str) {
        int count = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.substring(i, i + 2).equals("xx")) {
                count++;
            }
        }
        return count;
    }

    public boolean array123(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 1 && nums[i + 1] == 2 && nums[i + 2] == 3) {
                return true;
            }
        }
        return false;
    }

    public int[] fizzArray3(int start, int end) {
        List<Integer> numbers = new ArrayList<>();
        for (; start < end; start++) {
            numbers.add(start);
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();

    }

    public int factorial(int n) {
        int factor = 1;
        for (int i = 1; i <= n; i++) {
            factor *= i;
        }
        return factor;
    }

    public boolean hasBad(String str) {
        for (int i = 0; i < 2; i++) {
            if (str.indexOf("bad") == i) {
                return true;
            }
        }
        return false;
    }

    public String frontTimes(String str, int n) {
        if (str.length() < 3) {
            return str.substring(0, str.length()).repeat(n);
        }
        return str.substring(0, 3).repeat(n);
    }

    public String reverseString(String str) {
        StringBuilder reverse = new StringBuilder(str);
        return reverse.reverse().toString();
    }

    public List<String> reverseList(List<String> strings) {
        Collections.reverse(strings);
        return strings;
    }

    public String[] list2Array(List<String> strings) {
        return strings.toArray(new String[0]);
    }

    public List<String> distinctValues(List<String> strings) {
        HashSet<String> distinct = new HashSet<>(strings);
        List<String> distinctList = new ArrayList<>(distinct);
        return distinctList;
    }

    public List<String> array2List(String[] strings) {
        return Arrays.asList(strings);
    }

    public List<Double> arrayInt2ListDouble(int[] ints) {
        List<Double> doubles = new ArrayList<>();

        for (int i : ints) {
            doubles.add(i / 2.0);
        }
        return doubles;
    }

    public int blackjack(int a, int b) {
        return a > 21 && b > 21 ? 0 : a > 21 ? b : b > 21 ? a : Math.max(a, b);
    }

    public static int[] fibonacci() {
        List<Integer> fib = new ArrayList<>();
        fib.add(0);
        int prev = 1;
        fib.add(prev);
        int next;

        for (int i = 1, j = 2; i < 2000; j++) {
            fib.add(i);
            next = prev + i;
            prev = i;
            i = next;
        }
        return fib.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] primeFactors2(int n){
        List<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= n;) {
            if(n % i != 0){
                i++;
            } else{
                prime.add(i);
                n /= i;
            }

        } return prime.stream().mapToInt(Integer::intValue).toArray();
    }


    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings);
        System.out.println(Arrays.toString(strings));
        System.out.println(Arrays.toString(biggerTwo(new int[]{1, 3}, new int[]{3, 4})));
        System.out.println();
        System.out.print(Arrays.toString(fibonacci()));
        System.out.println();
        System.out.print(Arrays.toString(primeFactors2(28)));


    }
}
