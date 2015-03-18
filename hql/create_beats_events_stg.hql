drop table beats_events_stage;
CREATE EXTERNAL TABLE beats_events_stage (id INT, type STRING, user INT, date STRING)
COMMENT 'this is a staging table'
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','	
location "/user/hive/warehouse/beats_events_stage/"
