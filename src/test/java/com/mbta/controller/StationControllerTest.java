package com.mbta.controller;

import com.mbta.TestContext;
import com.mbta.view.ScheduleView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by moh on 7/23/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { TestContext.class })
@ActiveProfiles({"test"})
public class StationControllerTest
{

    @Autowired
    JdbcTemplate jdbct;

    Logger log = LoggerFactory.getLogger(StationControllerTest.class);

    @Test
    @Sql("classpath:data1.sql")
    public void get() {

        StationController ctlr = new StationController();
        ctlr.jdbct = jdbct;
        String stationId = "bad_station";

        // Bad station
        List<ScheduleView> view = ctlr.get(stationId);
        log.info ("total departures: "+view.size());
        assertTrue(view.isEmpty());

        // test for stop_1
        stationId = "stop_1";
        view = ctlr.get(stationId);
        log.info ("total departures: "+view.size());
        assertTrue(view.size() == 2);

        ScheduleView sch1 = view.get(0);
        assertEquals(sch1.destination, "daisy_town");
        assertEquals(sch1.departs, "5:03 AM");
        assertEquals(sch1.arrives, "6:35 AM");
        assertTrue(sch1.lateness == 100);
        assertTrue(sch1.track == 2);

        ScheduleView sch2 = view.get(1);
        assertEquals(sch2.destination, "mouse_town");
        assertEquals(sch2.departs, "10:05 AM");
        assertEquals(sch2.arrives, "2:05 PM");
        assertTrue(sch2.lateness == 0);
        assertTrue(sch2.track == 1);

        // test for stop_2
        stationId = "stop_2";
        view = ctlr.get(stationId);
        log.info ("total departures: "+view.size());
        assertTrue(view.size() == 1);

        sch1 = view.get(0);
        assertEquals(sch1.destination, "goose_town");
        assertEquals(sch1.departs, "10:05 AM");
        assertEquals(sch1.arrives, "12:11 PM");
        assertTrue(sch1.lateness == 10);
        assertTrue(sch1.track == 3);
    }
}
