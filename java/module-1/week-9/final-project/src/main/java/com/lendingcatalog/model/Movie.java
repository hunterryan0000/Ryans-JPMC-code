package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Movie implements CatalogItem {
    private String id;
    private String name;
    private String director;
    private LocalDate releaseDate;

    public Movie(String name, String director, String releaseDateStr) {
        this.name = name;
        this.director = director;
        releaseDate = LocalDate.parse(releaseDateStr);
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean matchesName(String searchStr) {
        return name.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return director.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return releaseDate.getYear() == searchYear;
    }

    @Override
    public void registerItem() throws FileStorageException {
        this.id = UUID.randomUUID().toString();//universally unique identifier
        FileStorageService.writeContentsToFile(
                //How contents print first line to log
                "Movie created at " + LocalDate.now() + " " + LocalTime.now() + System.lineSeparator() + this.toString() + System.lineSeparator(),
                LOG_LOCATION + "movie-" + LocalDate.now() + ".log", true);
    }

    @Override
    public String toString() {
        //How contents print inside log
        return "* " + name + System.lineSeparator()
                + " - Directed by: " + director + System.lineSeparator()
                + " - Released: " + releaseDate + System.lineSeparator()
                + " - Id: " + id;
    }
}
