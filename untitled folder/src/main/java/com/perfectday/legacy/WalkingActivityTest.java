/*
 * Tests commented out because it used custom Dao implementations
 */

package com.perfectday.legacy;


import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WalkingActivityTest {
  private Connection m_connection = null;

  private int getStepsByActivityId(int activityId) {
    int steps = 0;

    try {
      String sql = "SELECT step_count from steps WHERE activity_id = %d";
      sql = String.format(sql, activityId);

      PreparedStatement statement = m_connection.prepareStatement(sql);
      statement.executeQuery();

      ResultSet resultSet = statement.getResultSet();

      if (resultSet.next()) {
        steps = resultSet.getInt(1);
      }
      else {
        fail("SQL query failed to return calories.");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      fail("SQL query failed to select calories.");
    }

    return steps;
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
        fail("SQL query failed to return calories.");
      }
    } 
    catch (SQLException e) {
      e.printStackTrace();
      fail("SQL query failed to select calories.");
    }

    return calories;
  }

  @Before
  public void createConnection() {
  }

  @Test
  public void testStepsInsert() {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(m_connection);
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setStepCount(100);
    walkingActivityDao.insertActivity(walkingActivity);

    assertNotEquals(walkingActivity.getKey(), 0);

    assertEquals(100, getStepsByActivityId(walkingActivity.getKey()));

    walkingActivityDao.deleteActivity(walkingActivity);
  }

  @Test
  public void testCaloriesInsert() {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(m_connection);
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setCalories(11);
    walkingActivityDao.insertActivity(walkingActivity);

    assertNotEquals(walkingActivity.getKey(), 0);

    assertEquals(11, getCaloriesByActivityId(walkingActivity.getKey()));

    walkingActivityDao.deleteActivity(walkingActivity);
  }

  @Test
  public void testStepsUpdate() {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(m_connection);
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setStepCount(100);
    walkingActivityDao.insertActivity(walkingActivity);

    assertEquals(100, getStepsByActivityId(walkingActivity.getKey()));

    walkingActivity.setStepCount(500);
    walkingActivityDao.updateActivity(walkingActivity);
    assertEquals(500, getStepsByActivityId(walkingActivity.getKey()));

    walkingActivityDao.deleteActivity(walkingActivity);
  }

  @Test
  public void testCaloriesUpdate() {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(m_connection);
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setCalories(11);
    walkingActivityDao.insertActivity(walkingActivity);

    assertEquals(11, getCaloriesByActivityId(walkingActivity.getKey()));

    walkingActivity.setCalories(55);
    walkingActivityDao.updateActivity(walkingActivity);

    assertEquals(55, getCaloriesByActivityId(walkingActivity.getKey()));

    walkingActivityDao.deleteActivity(walkingActivity);
  }

  @Test (expected = AssertionError.class)
  public void testStepsDelete() {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(m_connection);
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setStepCount(100);
    walkingActivityDao.insertActivity(walkingActivity);

    walkingActivityDao.deleteActivity(walkingActivity);
    // Raises Assertion Error
    getStepsByActivityId(walkingActivity.getKey());

    walkingActivityDao.deleteActivity(walkingActivity);
  }

  @Test (expected = AssertionError.class)
  public void testCaloriesDelete() {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(m_connection);
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setCalories(11);
    walkingActivityDao.insertActivity(walkingActivity);

    walkingActivityDao.deleteActivity(walkingActivity);
    // Raises Assertion Error
    getCaloriesByActivityId(walkingActivity.getKey());
  }

  @Test
  public void testGetActivity() {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(m_connection);
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setCalories(11);
    walkingActivityDao.insertActivity(walkingActivity);
    
    IWalkingActivity sameWalkingActivity = walkingActivityDao.getActivity(walkingActivity.getActivityId());
    
    assertEquals(walkingActivity.getCalories(), sameWalkingActivity.getCalories());

    walkingActivityDao.deleteActivity(walkingActivity);
  }

  @Test
  public void testGetAll() {
    IWalkingActivityDao walkingActivityDao = new WalkingActivityDao(m_connection);
    IWalkingActivity walkingActivity1 = new WalkingActivity();
    walkingActivity1.setCalories(11);
    walkingActivityDao.insertActivity(walkingActivity1);
    
    IWalkingActivity walkingActivity2 = new WalkingActivity();
    walkingActivity2.setCalories(11);
    walkingActivityDao.insertActivity(walkingActivity2);
    
    assertEquals(2, walkingActivityDao.getAll().size());
    
    walkingActivityDao.deleteActivity(walkingActivity1);
    walkingActivityDao.deleteActivity(walkingActivity2);
  }

}
