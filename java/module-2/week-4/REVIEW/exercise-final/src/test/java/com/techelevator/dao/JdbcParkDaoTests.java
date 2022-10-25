package com.techelevator.dao;

import com.techelevator.model.Park;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcParkDaoTests extends BaseDaoTests {

    private static final Park PARK_1 = mapValuesToPark(1, "Acadia", "Maine", LocalDate.parse("1919-02-26"),
            47389, 2563129, "Covering most of Mount Desert Island and other coastal islands, Acadia features the tallest mountain on the Atlantic coast of the United States, granite peaks, ocean shoreline, woodlands, and lakes. There are freshwater, estuary, forest, and intertidal habitats.");
    private static final Park PARK_2 = mapValuesToPark(2, "Arches", "Utah", LocalDate.parse("1929-04-12"),
            76518, 1284767, "This site features more than 2,000 natural sandstone arches, including the famous Delicate Arch. In a desert climate, millions of years of erosion have led to these structures, and the arid ground has life-sustaining soil crust and potholes, which serve as natural water-collecting basins. Other geologic formations are stone columns, spires, fins, and towers.");
    private static final Park PARK_3 = mapValuesToPark(3, "Cuyahoga Valley", "Ohio", LocalDate.parse("2000-10-11"),
            32860, 2189849, "This park along the Cuyahoga River has waterfalls, hills, trails, and exhibits on early rural living. The Ohio and Erie Canal Towpath Trail follows the Ohio and Erie Canal, where mules towed canal boats. The park has numerous historic homes, bridges, and structures, and also offers a scenic train ride.");

    private JdbcParkDao jdbcParkDao;

    @Before
    public void setup() {

        jdbcParkDao = new JdbcParkDao(dataSource);
    }

    @Test
    public void getParkById_returns_correct_park_for_id() {

        Park park = jdbcParkDao.getPark(1);
        Assert.assertNotNull("getPark(1) returned null", park);
        assertParksMatch("getPark(1) returned wrong or partial data", PARK_1, park);

        park = jdbcParkDao.getPark(2);
        Assert.assertNotNull("getPark(2) returned null", park);
        assertParksMatch("getPark(2) returned wrong or partial data", PARK_2, park);

        park = jdbcParkDao.getPark(3);
        Assert.assertNotNull("getPark(3) returned null", park);
        assertParksMatch("getPark(3) returned wrong or partial data", PARK_3, park);

        // Park 4 does not exist
        park = jdbcParkDao.getPark(4);
        Assert.assertNull("getPark(4) does not exist and should be null", park);
    }

    @Test
    public void getAllParks_returns_all_parks() {

        List<Park> parks = jdbcParkDao.getAllParks();
        Assert.assertEquals("getAllParks returned wrong number of parks", 3, parks.size());
    }

    // Convenience method in lieu of a Park constructor with all the fields as parameters.
    // Similar to mapRowToPark() in JdbcParkDao.
    private static Park mapValuesToPark(int parkId, String name, String location, LocalDate establishDate,
        int area, int visitors, String description) {

        Park park = new Park();
        park.setParkId(parkId);
        park.setName(name);
        park.setLocation(location);
        park.setEstablishDate(establishDate);
        park.setArea(area);
        park.setVisitors(visitors);
        park.setDescription(description);
        return park;
    }

    // Note that the version of this method provided to students does not have the message parameter.
    private void assertParksMatch(String message, Park expected, Park actual) {

        Assert.assertEquals(message, expected.getParkId(), actual.getParkId());
        Assert.assertEquals(message, expected.getName(), actual.getName());
        Assert.assertEquals(message, expected.getLocation(), actual.getLocation());
        Assert.assertEquals(message, expected.getEstablishDate(), actual.getEstablishDate());
        Assert.assertEquals(message, expected.getArea(), actual.getArea());
        Assert.assertEquals(message, expected.getVisitors(), actual.getVisitors());
        Assert.assertEquals(message, expected.getDescription(), actual.getDescription());
    }
}
