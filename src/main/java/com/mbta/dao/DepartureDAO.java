package com.mbta.dao;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by moh on 7/24/16.
 */
@Component
public class DepartureDAO
{

    public Integer id ;
    public DateTime time ;
    public String trip;
    public String originStopId ;
    public String originStopName ;
    public String destination ;
    public DateTime arrivalTime ;
    public Integer lateness ;
    public Integer track ;
    public String status ;

    DateTimeFormatter timeFormat;

    public DepartureDAO() {
        timeFormat = DateTimeFormat.forPattern("h:mm a");
    }

    public String getDepartTime() {
        return  timeFormat.print(time);
    }

    public String getArrivalTime() {
        return  timeFormat.print(arrivalTime);
    }

}
