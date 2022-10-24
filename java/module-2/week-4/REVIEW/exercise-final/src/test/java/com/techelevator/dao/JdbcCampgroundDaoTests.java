package com.techelevator.dao;

import com.techelevator.model.Campground;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class JdbcCampgroundDaoTests extends BaseDaoTests {

    // Park 1 campgrounds
    private static final Campground CAMPGROUND_1 = mapValuesToCampground(1, 1, "Blackwoods", 1, 12,
            new BigDecimal("35.00"));
    private static final Campground CAMPGROUND_2 = mapValuesToCampground(2, 1, "Seawall", 5, 9,
            new BigDecimal("30.00"));
    private static final Campground CAMPGROUND_3 = mapValuesToCampground(3, 1, "Schoodic Woods", 5, 10,
            new BigDecimal("30.00"));

    // Park 2 campgrounds
    private static final Campground CAMPGROUND_4 = mapValuesToCampground(4, 2, "Devil's Garden", 1, 12,
            new BigDecimal("25.00"));
    private static final Campground CAMPGROUND_5 = mapValuesToCampground(5, 2, "Canyon Wren Group Site", 1, 12,
            new BigDecimal("160.00"));
    private static final Campground CAMPGROUND_6 = mapValuesToCampground(6, 2, "Juniper Group Site", 1, 12,
            new BigDecimal("250.00"));

    // Park 3 campgrounds
    private static final Campground CAMPGROUND_7 = mapValuesToCampground(7, 3, "The Unnamed Primitive Campsites", 5, 11,
            new BigDecimal("20.00"));

    private JdbcCampgroundDao jdbcCampgroundDao;

    @Before
    public void setup() {

        jdbcCampgroundDao = new JdbcCampgroundDao(dataSource);
    }

    @Test
    public void getCampground_returns_correct_campground_for_id() {

        Campground campground = jdbcCampgroundDao.getCampground(1);
        Assert.assertNotNull("getCampground(1) returned null", campground);
        assertCampgroundsMatch("getCampground(1) returned wrong or partial data",
                CAMPGROUND_1, campground);

        campground = jdbcCampgroundDao.getCampground(2);
        Assert.assertNotNull("getCampground(2) returned null", campground);
        assertCampgroundsMatch("getCampground(2) returned wrong or partial data",
                CAMPGROUND_2, campground);

        campground = jdbcCampgroundDao.getCampground(3);
        Assert.assertNotNull("getCampground(3) returned null", campground);
        assertCampgroundsMatch("getCampground(3) returned wrong or partial data",
                CAMPGROUND_3, campground);

        campground = jdbcCampgroundDao.getCampground(4);
        Assert.assertNotNull("getCampground(4) returned null", campground);
        assertCampgroundsMatch("getCampground(4) returned wrong or partial data",
                CAMPGROUND_4, campground);

        campground = jdbcCampgroundDao.getCampground(5);
        Assert.assertNotNull("getCampground(5) returned null", campground);
        assertCampgroundsMatch("getCampground(5) returned wrong or partial data",
                CAMPGROUND_5, campground);

        campground = jdbcCampgroundDao.getCampground(6);
        Assert.assertNotNull("getCampground(6) returned null", campground);
        assertCampgroundsMatch("getCampground(6) returned wrong or partial data",
                CAMPGROUND_6, campground);

        campground = jdbcCampgroundDao.getCampground(7);
        Assert.assertNotNull("getCampground(7) returned null", campground);
        assertCampgroundsMatch("getCampground(7) returned wrong or partial data",
                CAMPGROUND_7, campground);

        // Campground 8 does not exist
        campground = jdbcCampgroundDao.getCampground(8);
        Assert.assertNull("getCampground(8) does not exist and should be null", campground);
    }

    @Test
    public void getCampgroundsByParkId_returns_campgrounds_for_park_id() {

        List<Campground> campgrounds = jdbcCampgroundDao.getCampgroundsByParkId(1);
        Assert.assertEquals("getCampgroundsByParkId(1) returned wrong number of campgrounds",
                3, campgrounds.size());

        campgrounds = jdbcCampgroundDao.getCampgroundsByParkId(2);
        Assert.assertEquals("getCampgroundsByParkId(2) returned wrong number of campgrounds",
                3, campgrounds.size());

        campgrounds = jdbcCampgroundDao.getCampgroundsByParkId(3);
        Assert.assertEquals("getCampgroundsByParkId(3) returned wrong number of campgrounds",
                1, campgrounds.size());

        campgrounds = jdbcCampgroundDao.getCampgroundsByParkId(4);
        Assert.assertEquals("ParkId 4 doesn't exist, getCampgroundsByParkId(4) returned the wrong number of campgrounds",
                0, campgrounds.size());
    }

    // Convenience method in lieu of a Campground constructor with all the fields as parameters.
    // Similar to mapRowToCampground() in JdbcCampgroundDao.
    private static Campground mapValuesToCampground(int campgroundId, int parkId, String name, int openFromMonth,
        int openToMonth, BigDecimal dailyFee) {

        Campground campground = new Campground();
        campground.setCampgroundId(campgroundId);
        campground.setParkId(parkId);
        campground.setName(name);
        campground.setOpenFromMonth(openFromMonth);
        campground.setOpenToMonth(openToMonth);
        campground.setDailyFee(dailyFee);
        return campground;
    }

    // Note that the version of this method provided to students does not have the message parameter.
    private void assertCampgroundsMatch(String message, Campground expected, Campground actual) {

        Assert.assertEquals(message, expected.getCampgroundId(), actual.getCampgroundId());
        Assert.assertEquals(message, expected.getParkId(), actual.getParkId());
        Assert.assertEquals(message, expected.getName(), actual.getName());
        Assert.assertEquals(message, expected.getOpenFromMonth(), actual.getOpenFromMonth());
        Assert.assertEquals(message, expected.getOpenToMonth(), actual.getOpenToMonth());
        Assert.assertEquals(message, expected.getDailyFee(), actual.getDailyFee());
    }
}
