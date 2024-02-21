package Searching;

import java.util.Arrays;

import static Searching.SearchingFinancialRecords.FinancialRecord.linearSearchById;
import static Searching.SearchingFinancialRecords.FinancialRecord.linearSearchByValue;

public class SearchingFinancialRecords {

    static class FinancialRecord implements Comparable<FinancialRecord> {
        String id;
        double value;
        String date; // Simplified for demonstration

        public FinancialRecord(String id, double value, String date) {
            this.id = id; //A unique identifier for the record.
            this.value = value; //The monetary value associated with the record.
            this.date = date; //The date of the record, used for sorting and searching.
        }

        @Override
        public int compareTo(FinancialRecord other) {
            return this.date.compareTo(other.date);
        }

        @Override
        public String toString() {
            return "FinancialRecord {" + "id='" + id + '\'' + ", value=" + value + ", date='" + date + '\'' + '}';
        }

        public static int linearSearchById(FinancialRecord[] records, String targetId) {
            for (int i = 0; i < records.length; i++) {
                if (records[i].id.equals(targetId)) {
                    return i; // Return the index where the record is found
                }
            }
            return -1; // Return -1 if the record is not found
        }

        public static int linearSearchByValue(FinancialRecord[] records, double targetValue) {
            for (int i = 0; i < records.length; i++) {
                if (records[i].value == targetValue) {
                    return i; // Return the index where the record is found
                }
            }
            return -1; // Return -1 if the record is not found
        }


    }

    public static class FinancialRecordSearch {
        public static void main(String[] args) {
            FinancialRecord[] records = {
                    new FinancialRecord("001", 2000.00, "2021-01-01"),
                    new FinancialRecord("002", 1500.00, "2021-02-01"),
                    new FinancialRecord("003", 2500.00, "2021-03-01")
            };

            Arrays.sort(records); // Ensure records are sorted by date for binary search

            int index = Arrays.binarySearch(records, new FinancialRecord("dummy", 0, "2021-02-01"));
            if (index >= 0) {
                System.out.println("Record found by date: " + records[index]);
            } else {
                System.out.println("Record not found.");
            }
            index = Arrays.binarySearch(records, new FinancialRecord("dummy", 0, "2021-03-01"));
            if (index >= 0) {
                System.out.println("Record found by date: " + records[index]);
            } else {
                System.out.println("Record not found.");
            }
            index = linearSearchByValue(records, 2500.00);
            if (index >= 0) {
                System.out.println("Record found by value: " + records[index]);
            } else {
                System.out.println("Record not found.");
            }
            index = linearSearchById(records, "001");
            if (index >= 0) {
                System.out.println("Record found by id: " + records[index]);
            } else {
                System.out.println("Record not found.");
            }
        }
    }

}
