package com.perfectday.db.cyclingactivity;



import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;
import com.perfectday.constants.Constants;


@SpringBootTest
public class CyclingActivityTest {    
  private Connection m_connection = null;

  private String getStartTimeById(int activityId) {
    String startTime = "";

    try {
      String sql = "SELECT start_time from dates WHERE activity_id = %d";
      sql = String.format(sql, activityId);

      PreparedStatement statement = m_connection.prepareStatement(sql);
      statement.executeQuery();

      ResultSet resultSet = statement.getResultSet();

      if (resultSet.next()) {
        startTime = resultSet.getString(1);
      }
      else {
        fail("SQL query failed to return start_date.");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      fail("SQL query failed to select start_date.");
    }

    return startTime;
  }

  private String getEndTimeById(int activityId) {
    String endTime = "";

    try {
      String sql = "SELECT end_time from dates WHERE activity_id = %d";
      sql = String.format(sql, activityId);

      PreparedStatement statement = m_connection.prepareStatement(sql);
      statement.executeQuery();

      ResultSet resultSet = statement.getResultSet();

      if (resultSet.next()) {
        endTime = resultSet.getString(1);
      }
      else {
        fail("SQL query failed to return end_date.");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      fail("SQL query failed to select end_date.");
    }

    return endTime;
  }

  private int getCaloriesByActivityId(int activityId) {
    int calories = 0;

    try {
      String sql = "SELECT calorie_count from calories WHERE activity_id = %d";
      sql = String.format(sql, activityId);

      PreparedStatement statement = m_connection.prepareStatement(sql);
      statement.executeQuery();

      ResultSet resultSet = statement.getResultSet();

      if (resultSet.next()) {
        calories = resultSet.getInt(1);
      }
      else {
        fail("SQL query failed to return distance_count.");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      fail("SQL query failed to select distance_count.");
    }

    return calories;
  }

  @Before
  public void createConnection() {
    try {
      m_connection = DriverManager.getConnection(Constants.testUrl, Constants.user, Constants.password);
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testStartEndTimeInsert() {
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(m_connection);
    ICyclingActivity cyclingActivity = new CyclingActivity();
    cyclingActivity.setStartTime("20130209T063407-0800");
    cyclingActivity.setEndTime("20140209T063407-0800");
    cyclingActivityDao.insertActivity(cyclingActivity);

    assertNotEquals(cyclingActivity.getKey(), 0);

    // Todo: configure start_time and end_time so this test passes
    assertEquals("2013-02-09 09:34:07-05", getStartTimeById(cyclingActivity.getKey()));
    assertEquals("2014-02-09 09:34:07-05", getEndTimeById(cyclingActivity.getKey()));

    cyclingActivityDao.deleteActivity(cyclingActivity);
  }

  @Test
  public void testStartEndUpdate() {
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(m_connection);
    ICyclingActivity cyclingActivity = new CyclingActivity();
    cyclingActivity.setStartTime("20130209T063407-0800");
    cyclingActivity.setEndTime("20140209T063407-0800");
    cyclingActivityDao.insertActivity(cyclingActivity);
    assertEquals("2013-02-09 09:34:07-05", getStartTimeById(cyclingActivity.getKey()));
    assertEquals("2014-02-09 09:34:07-05", getEndTimeById(cyclingActivity.getKey()));

    cyclingActivityDao.deleteActivity(cyclingActivity);
  }

  @Test (expected = AssertionError.class)
  public void testStartEndDelete() {
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(m_connection);
    ICyclingActivity cyclingActivity = new CyclingActivity();
    cyclingActivity.setStartTime("20130209T063407-0800");
    cyclingActivity.setEndTime("20140209T063407-0800");
    cyclingActivityDao.insertActivity(cyclingActivity);

    cyclingActivityDao.deleteActivity(cyclingActivity);
    getStartTimeById(cyclingActivity.getKey());

    cyclingActivityDao.deleteActivity(cyclingActivity);
  }

  @Test
  public void testCaloriesInsert() {
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(m_connection);
    ICyclingActivity cyclingActivity = new CyclingActivity();
    cyclingActivity.setCalories(111);
    cyclingActivityDao.insertActivity(cyclingActivity);

    assertNotEquals(cyclingActivity.getKey(), 0);

    assertEquals(111, getCaloriesByActivityId(cyclingActivity.getKey()));

    cyclingActivityDao.deleteActivity(cyclingActivity);
  }

  @Test
  public void testCaloriesUpdate() {
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(m_connection);
    ICyclingActivity cyclingActivity = new CyclingActivity();
    cyclingActivity.setCalories(111);
    cyclingActivityDao.insertActivity(cyclingActivity);

    assertEquals(111, getCaloriesByActivityId(cyclingActivity.getKey()));

    cyclingActivity.setCalories(222);
    cyclingActivityDao.updateActivity(cyclingActivity);

    assertEquals(222, getCaloriesByActivityId(cyclingActivity.getKey()));

    cyclingActivityDao.deleteActivity(cyclingActivity);
  }

  @Test (expected = AssertionError.class)
  public void testCaloriesDelete() {
    ICyclingActivityDao cyclingActivityDao = new CyclingActivityDao(m_connection);
    ICyclingActivity cyclingActivity = new CyclingActivity();
    cyclingActivity.setCalories(111);

    cyclingActivityDao.deleteActivity(cyclingActivity);

    // Raises an Assertion Error
    getCaloriesByActivityId(cyclingActivity.getKey());
  }
}
