package com.perfectday.db.crosscountryskiingactivity;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.perfectday.constants.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrossCountrySkiingActivityTest {
  private Connection m_connection = null;

  private int getDistanceByActivityId(int activityId) {
    int distance = 0;

    try {
      String sql = "SELECT distance_count from distance WHERE activity_id = %d";
      sql = String.format(sql, activityId);

      PreparedStatement statement = m_connection.prepareStatement(sql);
      statement.executeQuery();

      ResultSet resultSet = statement.getResultSet();

      if (resultSet.next()) {
        distance = resultSet.getInt(1);
      }
      else {
        fail("SQL query failed to return distance_count.");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      fail("SQL query failed to select distance_count.");
    }

    return distance;
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
  public void testInsert() {
    ICrossCountrySkiingActivityDao crossCountrySkiingActivityDao =
        new CrossCountrySkiingActivityDao(m_connection);
    ICrossCountrySkiingActivity crossCountrySkiingActivity =
        new CrossCountrySkiingActivity();
    crossCountrySkiingActivity.setDistance(111);
    crossCountrySkiingActivityDao.insertActivity(crossCountrySkiingActivity);

    assertNotEquals(crossCountrySkiingActivity.getKey(), 0);

    assertEquals(111, getDistanceByActivityId(crossCountrySkiingActivity.getKey()));

    crossCountrySkiingActivityDao.deleteActivity(crossCountrySkiingActivity);
  }

  @Test
  public void testUpdate() {
    ICrossCountrySkiingActivityDao crossCountrySkiingActivityDao =
        new CrossCountrySkiingActivityDao(m_connection);
    ICrossCountrySkiingActivity crossCountrySkiingActivity =
        new CrossCountrySkiingActivity();
    crossCountrySkiingActivity.setDistance(111);
    crossCountrySkiingActivityDao.insertActivity(crossCountrySkiingActivity);

    assertEquals(111, getDistanceByActivityId(crossCountrySkiingActivity.getKey()));

    crossCountrySkiingActivity.setDistance(222);
    crossCountrySkiingActivityDao.updateActivity(crossCountrySkiingActivity);

    assertEquals(222, getDistanceByActivityId(crossCountrySkiingActivity.getKey()));

    crossCountrySkiingActivityDao.deleteActivity(crossCountrySkiingActivity);
  }

  @Test (expected = AssertionError.class)
  public void testDelete() {
    ICrossCountrySkiingActivityDao crossCountrySkiingActivityDao =
        new CrossCountrySkiingActivityDao(m_connection);
    ICrossCountrySkiingActivity crossCountrySkiingActivity =
        new CrossCountrySkiingActivity();
    crossCountrySkiingActivity.setDistance(111);
    crossCountrySkiingActivityDao.insertActivity(crossCountrySkiingActivity);

    assertEquals(111, getDistanceByActivityId(crossCountrySkiingActivity.getKey()));

    crossCountrySkiingActivityDao.deleteActivity(crossCountrySkiingActivity);

    // Raises an Assertion Error
    getDistanceByActivityId(crossCountrySkiingActivity.getKey());
  }
}