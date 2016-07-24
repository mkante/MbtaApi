package com.mbta.view;

import com.mbta.dao.DepartureDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by moh on 7/23/16.
 */
public class ScheduleView
{
    Logger log ;

    public Integer track;
    public String destination;
    public String departs;
    public String arrives;
    public Integer lateness;
    public String status;

    public ScheduleView() {
        log = LoggerFactory.getLogger(ScheduleView.class);
    }

    public ScheduleView(DepartureDAO departure) {
        this();

        track = departure.track ;
        destination = departure.destination ;
        departs = departure.getDepartTime() ;
        arrives = departure.getArrivalTime() ;
        lateness = departure.lateness ;
        status = departure.status;
    }

    @Override
    public String toString() {
        Map obj = new TreeMap<String,Object>();

        obj.put("track", track);
        obj.put("destination", destination);
        obj.put("departs", departs);
        obj.put("arrives", arrives);
        obj.put("status", status);
        obj.put("lateness", lateness);

        return obj.toString();
    }
}
