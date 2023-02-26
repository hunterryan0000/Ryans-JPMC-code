package com.lendingcatalog.util;

import com.lendingcatalog.util.exception.FileStorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileStorageService {

    // Requirement: File I/O
    public static void writeContentsToFile(String contents, String filename, boolean appendFile) throws FileStorageException {
        // In a case like this, you must open file for appending to the existing file.
        // Appending means that the content you write to the file is added to the end
        // of the file instead of overwriting everything in it
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename, appendFile))) {
        // Open the file to a PrintWriter object by passing strings through print method
            printWriter.print(contents);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("Can't write to " + filename, e);
        }
    }

    public static List<String> readContentsOfFile(String filename) throws FileStorageException {
        // Kind of like we did in the PP exercise where we had to add to the list of search
        // results if we had a hit. Initiate a List variable and read the file contents into the list
        List<String> contents = new ArrayList<>();
        // Open the file
        try (Scanner scanner = new Scanner(new File(filename))) {
            //add file contents to the list
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                contents.add(line);
            }
            //return list
            return contents;
        } catch (FileNotFoundException e) {
            throw new FileStorageException("Failed to read from " + filename, e);
        }
    }
}
