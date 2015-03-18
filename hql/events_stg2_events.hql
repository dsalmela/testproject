
DROP TABLE beats_events;
CREATE TABLE beats_events(id INT, type STRING, USER int, date STRING)
COMMENT 'This is the partitioned events table'
PARTITIONED BY(year string, month string);

set hive.exec.dynamic.partition = true;
set hive.exec.dynamic.partition.mode=nonstrict;

INSERT OVERWRITE TABLE beats_events PARTITION(year,month)
	select id, type, user, date, year(date), month(date)
	from beats_events_stage
;
