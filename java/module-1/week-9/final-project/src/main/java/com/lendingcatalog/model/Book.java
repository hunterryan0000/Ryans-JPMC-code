package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Book implements CatalogItem {
    private String id;
    private String title;
    private String author;
    private LocalDate publishDate;

    public Book(String title, String author, String publishDateStr) {
        this.title = title;
        this.author = author;
        publishDate = LocalDate.parse(publishDateStr);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean matchesName(String searchStr) { return title.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return author.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return publishDate.getYear() == searchYear;
    }

    @Override
    public void registerItem() throws FileStorageException {
        this.id = UUID.randomUUID().toString(); //universally unique identifier
        FileStorageService.writeContentsToFile(
                //How contents print first line to log
                "Book created at " + LocalDate.now() + " " + LocalTime.now() + System.lineSeparator() + this.toString() + System.lineSeparator(),
                LOG_LOCATION + "book-" + LocalDate.now() + ".log", true);
    }

    @Override
    public String toString() {
        //How contents print inside log
        return "* " + title + System.lineSeparator()
                + " - Written by: " + author + System.lineSeparator()
                + " - Published: " + publishDate + System.lineSeparator()
                + " - Id: " + id;
    }
}
