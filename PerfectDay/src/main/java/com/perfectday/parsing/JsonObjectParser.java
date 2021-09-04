package com.perfectday.parsing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perfectday.entities.CrossCountrySkiingResponse;
import com.perfectday.entities.CyclingResponse;
import com.perfectday.entities.KayakingResponse;
import com.perfectday.entities.RunningResponse;
import com.perfectday.entities.WalkingResponse;
import com.perfectday.service.CrossCountrySkiingService;
import com.perfectday.service.CyclingService;
import com.perfectday.service.KayakingService;
import com.perfectday.service.RunningService;
import com.perfectday.service.WalkingService;

/**
 * This class represents methods that support the CSV and JSON file parser and
 * contains methods that extract the specific attributes required in the
 * responses for each activity types and parses the relevant data accordingly.
 */

@Component
public class JsonObjectParser implements IJsonObjectParser {

	@Autowired
	WalkingService walkingService;
	@Autowired
	WalkingResponse walkingResponse;
	@Autowired
	RunningService runningService;
	@Autowired
	RunningResponse runningResponse;
	@Autowired
	CyclingService cyclingService;
	@Autowired
	CyclingResponse cyclingResponse;
	@Autowired
	KayakingService kayakingService;
	@Autowired
	KayakingResponse kayakingResponse;
	@Autowired
	CrossCountrySkiingService skiingService;
	@Autowired
	CrossCountrySkiingResponse skiingResponse;

	public Boolean parseWalkingActivity(JSONObject activityObject) {
		walkingResponse.setStepCount((long) activityObject.getOrDefault("steps", 0L));
		walkingResponse.setActivityType((String) activityObject.getOrDefault("activity", ""));

		Date startTime = null;
		Date endTime = null;
		try {
			startTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("startTime", ""));

		} catch (Exception e) {
			startTime = new Date();
		}
		try {
			endTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("endTime", ""));
		} catch (Exception ex) {
			endTime = new Date();
		}

		walkingResponse.setStartTime(startTime);
		walkingResponse.setEndTime(endTime);
		walkingResponse.setDistance((double) activityObject.getOrDefault("distance", 0.0));
		walkingResponse.setCalories((long) activityObject.getOrDefault("calories", 0L));

		walkingService.createWalkingResponse(walkingResponse);
		return true;
	}

	public Boolean parseRunningActivity(JSONObject activityObject) {

		runningResponse.setStepCount((long) activityObject.getOrDefault("steps", 0L));
		runningResponse.setActivityType((String) activityObject.getOrDefault("activity", ""));

		Date startTime = null;
		Date endTime = null;
		try {
			startTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("startTime", ""));

		} catch (Exception e) {
			startTime = new Date();
		}
		try {
			endTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("endTime", ""));
		} catch (Exception ex) {
			endTime = new Date();
		}

		runningResponse.setStartTime(startTime);
		runningResponse.setEndTime(endTime);
		runningResponse.setDistance((double) activityObject.getOrDefault("distance", 0.0));
		runningResponse.setCalories((long) activityObject.getOrDefault("calories", 0L));

		runningService.createRunningResponse(runningResponse);

		return true;

	}

	public Boolean parseCyclingActivity(JSONObject activityObject) {

		cyclingResponse.setActivityType((String) activityObject.getOrDefault("activity", ""));

		Date startTime = null;
		Date endTime = null;
		try {
			startTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("startTime", ""));

		} catch (Exception e) {
			startTime = new Date();
		}
		try {
			endTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("endTime", ""));
		} catch (Exception ex) {
			endTime = new Date();
		}

		cyclingResponse.setStartTime(startTime);
		cyclingResponse.setEndTime(endTime);
		cyclingResponse.setDistance((double) activityObject.getOrDefault("distance", 0.0));
		cyclingResponse.setCalories((long) activityObject.getOrDefault("calories", 0L));

		cyclingService.createCyclingResponse(cyclingResponse);

		return true;

	}

	public Boolean parseKayakingActivity(JSONObject activityObject) {

		kayakingResponse.setActivityType((String) activityObject.getOrDefault("activity", ""));

		Date startTime = null;
		Date endTime = null;
		try {
			startTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("startTime", ""));

		} catch (Exception e) {
			startTime = new Date();
		}
		try {
			endTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("endTime", ""));
		} catch (Exception ex) {
			endTime = new Date();
		}

		kayakingResponse.setDistance((double) activityObject.getOrDefault("distance", 0.0));
		kayakingResponse.setCalories((long) activityObject.getOrDefault("calories", 0L));

		kayakingService.createKayakingResponse(kayakingResponse);

		return true;

	}

	public boolean parseSkiingActivity(JSONObject activityObject) {
		skiingResponse.setActivityType((String) activityObject.getOrDefault("activity", ""));

		Date startTime = null;
		Date endTime = null;
		try {
			startTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("startTime", ""));

		} catch (Exception e) {
			startTime = new Date();
		}
		try {
			endTime = new SimpleDateFormat("yyyyMMdd'T'HHmmss-SSSS", Locale.US)
					.parse((String) activityObject.getOrDefault("endTime", ""));
		} catch (Exception ex) {
			endTime = new Date();
		}

		skiingResponse.setDistance((double) activityObject.getOrDefault("distance", 0.0));
		skiingResponse.setCalories((long) activityObject.getOrDefault("calories", 0L));

		skiingService.createCrossCounrySkiingResponse(skiingResponse);

		return true;
	}

}
