package com.lendingcatalog.model;

import com.lendingcatalog.util.exception.FileStorageException;

public interface CatalogItem {
    boolean matchesName(String searchStr);
    boolean matchesCreator(String searchStr);
    boolean matchesYear(int searchYear);
    void registerItem() throws FileStorageException; // needed to add because of FSService

    String LOG_LOCATION = "src/main/resources/logs/";
}
