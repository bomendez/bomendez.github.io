package com.perfectday.db.runningactivity;



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
public class RunningActivityTest {
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
    IRunningActivityDao runningActivityDao = new RunningActivityDao(m_connection);
    IRunningActivity runningActivity = new RunningActivity();
    runningActivity.setDistance(111);
    runningActivityDao.insertActivity(runningActivity);

    assertNotEquals(runningActivity.getKey(), 0);
    
    assertEquals(111, getDistanceByActivityId(runningActivity.getKey()));

    runningActivityDao.deleteActivity(runningActivity);
  }

  @Test
  public void testUpdate() {
    IRunningActivityDao runningActivityDao = new RunningActivityDao(m_connection);
    IRunningActivity runningActivity = new RunningActivity();
    runningActivity.setDistance(111);
    runningActivityDao.insertActivity(runningActivity);
    
    assertEquals(111, getDistanceByActivityId(runningActivity.getKey()));

    runningActivity.setDistance(222);
    runningActivityDao.updateActivity(runningActivity);
    
    assertEquals(222, getDistanceByActivityId(runningActivity.getKey()));

    runningActivityDao.deleteActivity(runningActivity);
  }

  @Test (expected = AssertionError.class)
  public void testDelete() {
    IRunningActivityDao runningActivityDao = new RunningActivityDao(m_connection);
    IRunningActivity runningActivity = new RunningActivity();
    runningActivity.setDistance(111);

    runningActivityDao.deleteActivity(runningActivity);

    // Raises an Assertion Error
    getDistanceByActivityId(runningActivity.getKey());
  }
}
