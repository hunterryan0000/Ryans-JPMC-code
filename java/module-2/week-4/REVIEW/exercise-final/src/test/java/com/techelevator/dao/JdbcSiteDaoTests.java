package com.techelevator.dao;

import com.techelevator.model.Site;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcSiteDaoTests extends BaseDaoTests {

    private static final Site SITE_1 = mapValuesToSite(1, 1, 1, 6, false, 0, false);
    private static final Site SITE_2 = mapValuesToSite(2, 1, 2, 10, false, 0, false);
    private static final Site SITE_3 = mapValuesToSite(3, 1, 3, 20, true, 0 , false);
    private static final Site SITE_4 = mapValuesToSite(4, 1, 4, 30, true, 40, false);
    private static final Site SITE_5 = mapValuesToSite(5, 1, 5, 40, true, 50, true);

    private JdbcSiteDao jdbcSiteDao;

    @Before
    public void setup() {

        jdbcSiteDao = new JdbcSiteDao(dataSource);
    }

    @Test
    public void getSite_returns_correct_site_for_id() {

        Site site = jdbcSiteDao.getSite(1);
        Assert.assertNotNull("getSite(1) returned null", site);
        assertSitesMatch("getSite(1) returned wrong or partial data", SITE_1, site);

        site = jdbcSiteDao.getSite(2);
        Assert.assertNotNull("getSite(2) returned null", site);
        assertSitesMatch("getSite(2) returned wrong or partial data", SITE_2, site);

        site = jdbcSiteDao.getSite(3);
        Assert.assertNotNull("getSite(3) returned null", site);
        assertSitesMatch("getSite(3) returned wrong or partial data", SITE_3, site);

        site = jdbcSiteDao.getSite(4);
        Assert.assertNotNull("getSite(4) returned null", site);
        assertSitesMatch("getSite(4) returned wrong or partial data", SITE_4, site);

        site = jdbcSiteDao.getSite(5);
        Assert.assertNotNull("getSite(5) returned null", site);
        assertSitesMatch("getSite(5) returned wrong or partial data", SITE_5, site);
    }

    @Test
    public void getSitesByCampgroundId_returns_sites_for_campground_id() {

        List<Site> sites = jdbcSiteDao.getSitesByCampgroundId(1);
        Assert.assertEquals("getSitesByCampgroundId(1) returned wrong number of sites",
                12, sites.size());

        sites = jdbcSiteDao.getSitesByCampgroundId(2);
        Assert.assertEquals("getSitesByCampgroundId(2) returned wrong number of sites",
                12, sites.size());

        sites = jdbcSiteDao.getSitesByCampgroundId(3);
        Assert.assertEquals("getSitesByCampgroundId(3) returned wrong number of sites",
                12, sites.size());

        sites = jdbcSiteDao.getSitesByCampgroundId(4);
        Assert.assertEquals("getSitesByCampgroundId(4) returned wrong number of sites",
                8, sites.size());

        sites = jdbcSiteDao.getSitesByCampgroundId(5);
        Assert.assertEquals("getSitesByCampgroundId(5) returned wrong number of sites",
                1, sites.size());

        sites = jdbcSiteDao.getSitesByCampgroundId(6);
        Assert.assertEquals("getSitesByCampgroundId(6) returned wrong number of sites",
                1, sites.size());

        sites = jdbcSiteDao.getSitesByCampgroundId(7);
        Assert.assertEquals("getSitesByCampgroundId(7) returned wrong number of sites",
                5, sites.size());

        // Campground 8 doesn't exist
        sites = jdbcSiteDao.getSitesByCampgroundId(8);
        Assert.assertEquals("Campground 8 doesn't exist, getSitesByCampgroundId(8) returned the wrong number of sites",
                0, sites.size());
    }

    @Test
    public void getSitesThatAllowRVs_returns_correct_sites_for_park_id() {

        List<Site> sites = jdbcSiteDao.getSitesThatAllowRVs(1);
        Assert.assertEquals("getSitesThatAllowRVs(1) returned wrong number of sites",
                23, sites.size());

        sites = jdbcSiteDao.getSitesThatAllowRVs(2);
        Assert.assertEquals("getSitesThatAllowRVs(2) returned wrong number of sites",
                2, sites.size());

        sites = jdbcSiteDao.getSitesThatAllowRVs(3);
        Assert.assertEquals("getSitesThatAllowRVs(3) returned wrong number of sites",
                0, sites.size());

        // Park 4 doesn't exist
        sites = jdbcSiteDao.getSitesThatAllowRVs(4);
        Assert.assertEquals("Park 4 doesn't exist, getSitesThatAllowRVs(4) returned wrong number of sites",
                0, sites.size());

    }

    @Test
    public void getAvailableSites_returns_correct_sites_for_parkId() {

        List<Site> sites = jdbcSiteDao.getAvailableSites(1);
        Assert.assertEquals("getAvailableSites(1) returned wrong number of sites",
                31, sites.size());

        sites = jdbcSiteDao.getAvailableSites(2);
        Assert.assertEquals("getAvailableSites(2) returned wrong number of sites",
                7, sites.size());

        sites = jdbcSiteDao.getAvailableSites(3);
        Assert.assertEquals("getAvailableSites(3) returned wrong number of sites",
                5, sites.size());

        // Park 4 doesn't exist
        sites = jdbcSiteDao.getAvailableSites(4);
        Assert.assertEquals("Park 4 doesn't exist, getAvailableSites(4) returned wrong number of sites",
                0, sites.size());
    }

    // Convenience method in lieu of a Site constructor with all the fields as parameters.
    // Similar to mapRowToSite() in JdbcSiteDao.
    private static Site mapValuesToSite(int siteId, int campgroundId, int siteNumber, int maxOccupancy,
        boolean accessible, int maxRvLength, boolean utilities) {

        Site site = new Site();
        site.setSiteId(siteId);
        site.setCampgroundId(campgroundId);
        site.setSiteNumber(siteNumber);
        site.setMaxOccupancy(maxOccupancy);
        site.setAccessible(accessible);
        site.setMaxRvLength(maxRvLength);
        site.setUtilities(utilities);
        return site;
    }

    // Note that the version of this method provided to students does not have the message parameter.
    private void assertSitesMatch(String message, Site expected, Site actual) {

        Assert.assertEquals(message, expected.getSiteId(), actual.getSiteId());
        Assert.assertEquals(message, expected.getCampgroundId(), actual.getCampgroundId());
        Assert.assertEquals(message, expected.getSiteNumber(), actual.getSiteNumber());
        Assert.assertEquals(message, expected.getMaxOccupancy(), actual.getMaxOccupancy());
        Assert.assertEquals(message, expected.isAccessible(), actual.isAccessible());
        Assert.assertEquals(message, expected.getMaxRvLength(), actual.getMaxRvLength());
        Assert.assertEquals(message, expected.isUtilities(), actual.isUtilities());
    }
}
