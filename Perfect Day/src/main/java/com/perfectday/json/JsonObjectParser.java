package com.perfectday.json;

import org.json.simple.JSONObject;

import com.perfectday.db.crosscountryskiingactivity.CrossCountrySkiingActivity;
import com.perfectday.db.crosscountryskiingactivity.CrossCountrySkiingActivityDao;
import com.perfectday.db.crosscountryskiingactivity.ICrossCountrySkiingActivity;
import com.perfectday.db.crosscountryskiingactivity.ICrossCountrySkiingActivityDao;
import com.perfectday.db.cyclingactivity.CyclingActivity;
import com.perfectday.db.cyclingactivity.CyclingActivityDao;
import com.perfectday.db.cyclingactivity.ICyclingActivity;
import com.perfectday.db.cyclingactivity.ICyclingActivityDao;
import com.perfectday.db.kayakingactivity.IKayakingActivity;
import com.perfectday.db.kayakingactivity.IKayakingActivityDao;
import com.perfectday.db.kayakingactivity.KayakingActivity;
import com.perfectday.db.kayakingactivity.KayakingActivityDao;
import com.perfectday.db.runningactivity.IRunningActivity;
import com.perfectday.db.runningactivity.IRunningActivityDao;
import com.perfectday.db.runningactivity.RunningActivity;
import com.perfectday.db.runningactivity.RunningActivityDao;
import com.perfectday.db.walkingactivity.IWalkingActivity;
import com.perfectday.db.walkingactivity.IWalkingActivityDao;
import com.perfectday.db.walkingactivity.WalkingActivity;
import com.perfectday.db.walkingactivity.WalkingActivityDao;


public class JsonObjectParser implements IJsonObjectParser {

  public static Boolean parseRunningActivity(IJsonConnectionObject jsonConnectionObject) {
    JSONObject activityObject = jsonConnectionObject.getJsonObject();
    IRunningActivityDao runningActivityDao = new RunningActivityDao(jsonConnectionObject.getConnection());
    IRunningActivity runningActivity = new RunningActivity();
    runningActivity.setStepCount(((Number) activityObject.getOrDefault("stepCount", 0)).intValue());
    runningActivity.setStartTime(activityObject.getOrDefault("startTime", "").toString());
    runningActivity.setEndTime(activityObject.getOrDefault("endTime", "").toString());
    runningActivity.setDistance(((Number) activityObject.getOrDefault("distance", 0)).intValue());
    runningActivity.setCalories(((Number) activityObject.getOrDefault("calories", 0)).intValue());
    return runningActivityDao.insertActivity(runningActivity);
  }

  public static Boolean parseWalkingActivity(IJsonConnectionObject jsonConnectionObject) {
    JSONObject activityObject = jsonConnectionObject.getJsonObject();
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(jsonConnectionObject.getConnection());
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setStepCount(((Number) activityObject.getOrDefault("stepCount", 0)).intValue());
    walkingActivity.setStartTime(activityObject.getOrDefault("startTime", "").toString());
    walkingActivity.setEndTime(activityObject.getOrDefault("endTime", "").toString());
    walkingActivity.setDistance(((Number) activityObject.getOrDefault("distance", 0)).intValue());
    walkingActivity.setCalories(((Number) activityObject.getOrDefault("calories", 0)).intValue());
    return walkingActivityDao.insertActivity(walkingActivity);
  }

  public static Boolean parseCyclingActivity(IJsonConnectionObject jsonConnectionObject) {
    JSONObject activityObject = jsonConnectionObject.getJsonObject();
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(jsonConnectionObject.getConnection());
    ICyclingActivity cyclingActivity = new CyclingActivity();
    cyclingActivity.setStartTime(activityObject.getOrDefault("startTime", "").toString());
    cyclingActivity.setEndTime(activityObject.getOrDefault("endTime", "").toString());
    cyclingActivity.setDistance(((Number) activityObject.getOrDefault("distance", 0)).intValue());
    cyclingActivity.setCalories(((Number) activityObject.getOrDefault("calories", 0)).intValue());
    return cyclingActivityDao.insertActivity(cyclingActivity);
  }

  public static Boolean parseKayakingActivity(IJsonConnectionObject jsonConnectionObject) {
    JSONObject activityObject = jsonConnectionObject.getJsonObject();
    IKayakingActivityDao kayakingActivityDao = new KayakingActivityDao(jsonConnectionObject.getConnection());
    IKayakingActivity kayakingActivity = new KayakingActivity();
    kayakingActivity.setDistance(((Number) activityObject.getOrDefault("distance", 0)).intValue());
    kayakingActivity.setCalories(((Number) activityObject.getOrDefault("calories", 0)).intValue());
    return kayakingActivityDao.insertActivity(kayakingActivity);
  }

  public static Boolean parseSkiingActivity(IJsonConnectionObject jsonConnectionObject) {
    JSONObject activityObject = jsonConnectionObject.getJsonObject();
    ICrossCountrySkiingActivityDao skiingActivityDao = new CrossCountrySkiingActivityDao(jsonConnectionObject.getConnection());
    ICrossCountrySkiingActivity skiingActivity = new CrossCountrySkiingActivity();
    skiingActivity.setDistance(((Number) activityObject.getOrDefault("distance", 0)).intValue());
    skiingActivity.setCalories(((Number) activityObject.getOrDefault("calories", 0)).intValue());
    return skiingActivityDao.insertActivity(skiingActivity);
  }
}
