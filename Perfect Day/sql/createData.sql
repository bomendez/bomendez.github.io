--INSERT INTO activity (activity_type) VALUES ('walking');

--INSERT INTO dates (activity_id, start_date, end_date) VALUES (2, current_timestamp, current_timestamp);

WITH new_activity AS (
	INSERT INTO activity (activity_type) VALUES ('cycling') RETURNING activity_id
)
INSERT INTO dates (activity_id, start_time, end_time) VALUES ((SELECT activity_id FROM new_activity), current_timestamp, current_timestamp);


--INSERT INTO day_rating (day, rating) VALUES (current_timestamp, true);

SELECT activity.activity_id, activity_type, day, rating FROM activity 
INNER JOIN dates ON dates.activity_id = activity.activity_id
INNER JOIN day_rating ON day_rating.day = date_trunc('day', dates.start_time) -- start_date is a string
