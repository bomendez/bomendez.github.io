/*
 * Tests commented out because it used custom Dao implementations
 */

package com.perfectday.legacy;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KayakingActivityTest {
  private Connection m_connection = null;

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
  public void testInsert() {
    IKayakingActivityDao kayakingActivityDao = new KayakingActivityDao(m_connection);
    IKayakingActivity kayakingActivity = new KayakingActivity();
    kayakingActivity.setCalories(11);
    kayakingActivityDao.insertActivity(kayakingActivity);

    assertEquals(11, getCaloriesByActivityId(kayakingActivity.getKey()));

    kayakingActivityDao.deleteActivity(kayakingActivity);
  }

  @Test
  public void testUpdate() {
    IKayakingActivityDao kayakingActivityDao = new KayakingActivityDao(m_connection);
    IKayakingActivity kayakingActivity = new KayakingActivity();
    kayakingActivity.setCalories(11);
    kayakingActivityDao.insertActivity(kayakingActivity);

    assertEquals(11, getCaloriesByActivityId(kayakingActivity.getKey()));

    kayakingActivity.setCalories(55);
    kayakingActivityDao.updateActivity(kayakingActivity);

    assertEquals(55, getCaloriesByActivityId(kayakingActivity.getKey()));

    kayakingActivityDao.deleteActivity(kayakingActivity);
  }

  @Test (expected = AssertionError.class)
  public void testDelete() {
    IKayakingActivityDao kayakingActivityDao = new KayakingActivityDao(m_connection);
    IKayakingActivity kayakingActivity = new KayakingActivity();
    kayakingActivity.setCalories(11);
    kayakingActivityDao.insertActivity(kayakingActivity);

    kayakingActivityDao.deleteActivity(kayakingActivity);
    // Raises Assertion Error
    getCaloriesByActivityId(kayakingActivity.getKey());
  }
}