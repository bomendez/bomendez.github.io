package com.perfectday.rest.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.perfectday.constants.Constants;
import com.perfectday.db.activity.IActivity;
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
import com.perfectday.db.steppedactivity.ISteppedActivity;
import com.perfectday.db.timedactivity.ITimedActivity;
import com.perfectday.db.walkingactivity.IWalkingActivity;
import com.perfectday.db.walkingactivity.IWalkingActivityDao;
import com.perfectday.db.walkingactivity.WalkingActivity;
import com.perfectday.db.walkingactivity.WalkingActivityDao;

@RestController
public class PerfectDayRestController {
  Connection m_connection = null;

  private Connection getConnection() {
    try {
      if (m_connection == null) {
        m_connection = DriverManager.getConnection(Constants.appUrl, Constants.user, Constants.password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return m_connection;
  }

  @GetMapping(value = "/activities/walkingActivities")
  public ArrayList<IWalkingActivity> getAllWalkingActivities () {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(getConnection());
    return walkingActivityDao.getAll();
  }

  @GetMapping(value = "/activities/walkingActivities/{activityId}")
  public IWalkingActivity getWalkingActivity(@PathVariable Integer activityId){
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(getConnection());
    return walkingActivityDao.getActivity(activityId);
  }

  @PostMapping(value = "/activities/walkingActivities/newActivity")
  public void insertWalkingActivity(@RequestBody WalkingActivity walkingActivity){
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(getConnection());
    walkingActivityDao.insertActivity(walkingActivity);
  }

  @DeleteMapping(value = "/activities/walkingActivities/deleteActivity/{activityId}")
  public void deleteWalkingActivity(@PathVariable Integer activityId){
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(getConnection());
    IWalkingActivity walkingActivity = walkingActivityDao.getActivity(activityId);
    walkingActivityDao.deleteActivity(walkingActivity);
  }

  @GetMapping(value = "/activities/runningActivities")
  public ArrayList<IRunningActivity> getAllRunningActivities () {
    RunningActivityDao runningActivityDao = new RunningActivityDao(getConnection());
    return runningActivityDao.getAll();
  }

  @GetMapping(value = "/activities/runningActivities/{activityId}")
  public IRunningActivity getRunningActivity (@PathVariable Integer activityId) {
    RunningActivityDao runningActivityDao = new RunningActivityDao(getConnection());
    return runningActivityDao.getActivity(activityId);
  }

  @PostMapping(value = "/activities/runningActivities/newActivity")
  public void insertRunningActivity(@RequestBody RunningActivity runningActivity){
    IRunningActivityDao runningActivityDao = new RunningActivityDao(getConnection());
    runningActivityDao.insertActivity(runningActivity);
  }

  @DeleteMapping(value = "/activities/runningActivities/deleteActivity/{activityId}")
  public void deleteRunningActivity(@PathVariable Integer activityId){
    IRunningActivityDao runningActivityDao = new RunningActivityDao(getConnection());
    IRunningActivity runningActivity = runningActivityDao.getActivity(activityId);
    runningActivityDao.deleteActivity(runningActivity);
  }

  @GetMapping(value = "/activities/cyclingActivities")
  public ArrayList<ICyclingActivity> getAllCyclingActivities() {
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(getConnection());
    return cyclingActivityDao.getAll();
  }

  @GetMapping(value = "/activities/cyclingActivities/{activityId}")
  public ICyclingActivity getCyclingActivity(@PathVariable Integer activityId){
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(getConnection());
    return cyclingActivityDao.getActivity(activityId);
  }

  @PostMapping(value = "/activities/cyclingActivities/newActivity")
  public void insertCyclingActivity(@RequestBody CyclingActivity cyclingActivity){
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(getConnection());
    cyclingActivityDao.insertActivity(cyclingActivity);
  }

  @DeleteMapping(value = "/activities/cyclingActivities/deleteActivity/{activityId}")
  public void deleteCyclingActivity(@PathVariable Integer activityId){
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(getConnection());
    ICyclingActivity cyclingActivity = cyclingActivityDao.getActivity(activityId);
    cyclingActivityDao.deleteActivity(cyclingActivity);
  }

  @GetMapping(value = "/activities/kayakingActivities")
  public ArrayList<IKayakingActivity> getAllKayakingActivities () {
    IKayakingActivityDao kayakingActivityDao = new KayakingActivityDao(getConnection());
    return kayakingActivityDao.getAll();
  }

  @GetMapping(value = "/activities/kayakingActivities/{activityId}")
  public IKayakingActivity getKayakingActivity(@PathVariable Integer activityId){
    IKayakingActivityDao kayakingActivityDao = new KayakingActivityDao(getConnection());
    return kayakingActivityDao.getActivity(activityId);
  }

  @PostMapping(value = "/activities/kayakingActivities/newActivity")
  public void insertKayakingActivity(@RequestBody KayakingActivity kayakingActivity){
    IKayakingActivityDao kayakingActivityDao = new KayakingActivityDao(getConnection());
    kayakingActivityDao.insertActivity(kayakingActivity);
  }

  @DeleteMapping(value = "/activities/kayakingActivities/deleteActivity/{activityId}")
  public void deleteKayakingActivity(@PathVariable Integer activityId){
    IKayakingActivityDao kayakingActivityDao = new KayakingActivityDao(getConnection());
    IKayakingActivity kayakingActivity = kayakingActivityDao.getActivity(activityId);
    kayakingActivityDao.deleteActivity(kayakingActivity);
  }

  @GetMapping(value = "/activities/crossCountrySkiingActivities")
  public ArrayList<ICrossCountrySkiingActivity> getAllCrossCountrySkiingActivities () {
    ICrossCountrySkiingActivityDao crossCountrySkiingActivityDao = new CrossCountrySkiingActivityDao(getConnection());
    return crossCountrySkiingActivityDao.getAll();
  }

  @GetMapping(value = "/activities/crossCountrySkiingActivities/{activityId}")
  public ICrossCountrySkiingActivity getCrossCountrySkiingActivity(@PathVariable Integer activityId){
    ICrossCountrySkiingActivityDao crossCountrySkiingActivityDao = new CrossCountrySkiingActivityDao(getConnection());
    return crossCountrySkiingActivityDao.getActivity(activityId);
  }

  @PostMapping(value = "/activities/crossCountrySkiingActivities/newActivity")
  public void insertCrossCountrySkiingActivity(@RequestBody CrossCountrySkiingActivity crossCountrySkiingActivity){
    ICrossCountrySkiingActivityDao crossCountrySkiingActivityDao = new CrossCountrySkiingActivityDao(getConnection());
    crossCountrySkiingActivityDao.insertActivity(crossCountrySkiingActivity);
  }

  @DeleteMapping(value = "/activities/crossCountrySkiingActivities/deleteActivity/{activityId}")
  public void deleteCrossCountrySkiingActivity(@PathVariable Integer activityId){
    ICrossCountrySkiingActivityDao crossCountrySkiingActivityDao = new CrossCountrySkiingActivityDao(getConnection());
    ICrossCountrySkiingActivity crossCountrySkiingActivity = crossCountrySkiingActivityDao.getActivity(activityId);
    crossCountrySkiingActivityDao.deleteActivity(crossCountrySkiingActivity);
  }
}
