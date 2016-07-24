package com.mbta.controller;

import com.mbta.dao.DepartureDAO;
import com.mbta.view.ScheduleView;
import com.mbta.view.ErrorView;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moh on 7/23/16.
 */
@RestController
@RequestMapping("/stations/{id}/schedules")
public class StationController
{

    protected Logger log ;

    @Autowired
    JdbcTemplate jdbct;

    public StationController() {
        log = LoggerFactory.getLogger(StationController.class);
    }

    @RequestMapping (method = RequestMethod.GET)
    public List<ScheduleView> get(
            @PathVariable("id") String stationID
    ) {

        log.info("station ID= " + stationID);

        List<DepartureDAO> schedules = getStationSchedules(stationID);
        log.debug(schedules.size()+ " departures retrieved" );

        List view = new ArrayList<ScheduleView>();

        log.debug("Building view");
        for (DepartureDAO item: schedules) {

            view.add( new ScheduleView(item) ) ;
        }

        return view;
    }


    protected List<DepartureDAO> getStationSchedules(String stationId) {
        String sql =
                "SELECT " +
                        "*, UNIX_TIMESTAMP(time) dpt_t, " +
                        "UNIX_TIMESTAMP(arrival_time) arv_t " +
                "FROM departures " +
                "WHERE origin_stop_id = ? " +
                "ORDER BY destination ASC";

        //log.info ("SQL = "+sql) ;

        List<DepartureDAO> list =
                    jdbct.query(sql,
                        new Object[] { stationId },
                        new RowMapper<DepartureDAO>() {
                            @Override
                            public DepartureDAO mapRow(ResultSet rs, int rowNum)
                                    throws SQLException {

                                DateTime dTime = new DateTime(
                                        rs.getTimestamp("time"),
                                        DateTimeZone.forID("Etc/GMT")
                                        );

                                DateTime aTime = new DateTime(
                                        rs.getTimestamp("arrival_time"),
                                        DateTimeZone.forID("Etc/GMT")
                                );

                                DepartureDAO row = new DepartureDAO();
                                row.id = rs.getInt("id");
                                row.trip = rs.getString("trip");
                                row.originStopId = rs.getString("origin_stop_id");
                                row.originStopName = rs.getString("origin_stop_name");
                                row.destination = rs.getString("destination");
                                row.time = dTime;
                                row.arrivalTime = aTime;
                                log.info ("depart = "+rs.getTimestamp("time"));
                                row.lateness = rs.getInt("lateness");
                                row.track = rs.getInt("track");
                                row.status = rs.getString("status");

                                return row;
                            }
                        });

        return list;
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(Exception.class)
    protected Object handleError() {

        return new ErrorView();
    }
}
