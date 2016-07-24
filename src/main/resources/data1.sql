TRUNCATE departures ;

INSERT INTO departures SET
trip = 911 ,
track = 1,
origin_stop_id = 'stop_1' ,
origin_stop_name = 'mickey_station' ,
destination = 'mouse_town' ,
time = '2016-07-22 10:05:57' ,
arrival_time = '2016-07-22 14:05:57' ,
lateness = 0 ,
status = 'on_time';

INSERT INTO departures SET
trip = 192 ,
track = 2,
origin_stop_id = 'stop_1' ,
origin_stop_name = 'mickey_station' ,
destination = 'daisy_town' ,
time = '2016-07-22 05:03:57' ,
arrival_time = '2016-07-22 06:35:57' ,
lateness = 100 ,
status = 'on_ime';


INSERT INTO departures SET
trip = 981 ,
track = 3,
origin_stop_id = 'stop_2' ,
origin_stop_name = 'duck_station' ,
destination = 'goose_town' ,
time = '2016-07-22 10:05:57' ,
arrival_time = '2016-07-22 12:11:57' ,
lateness = 10 ,
status = 'late';