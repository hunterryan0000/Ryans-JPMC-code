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
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(filename, appendFile))) {
            printWriter.print(contents);
        } catch (FileNotFoundException e) {
            throw new FileStorageException("Can't write to " + filename, e);
        }
    }

    public static List<String> readContentsOfFile(String filename) throws FileStorageException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            List<String> contents = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                contents.add(line);
            }
            return contents;
        } catch (FileNotFoundException e) {
            throw new FileStorageException("Failed to read from " + filename, e);
        }
    }
}
