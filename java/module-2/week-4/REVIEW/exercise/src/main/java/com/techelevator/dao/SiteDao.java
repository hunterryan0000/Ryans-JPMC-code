package com.techelevator.dao;

import com.techelevator.model.Site;

import java.util.List;

public interface SiteDao {

    Site getSite(int siteId);

    List<Site> getSitesByCampgroundId(int campgroundId);

    List<Site> getSitesThatAllowRVs(int parkId);

    List<Site> getAvailableSites(int parkId);
}
