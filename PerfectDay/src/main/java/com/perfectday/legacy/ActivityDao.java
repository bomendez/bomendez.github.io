package com.perfectday.legacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public abstract class ActivityDao extends Dao implements IActivityDao {

  public ActivityDao(Connection connection) {
    super(connection);
  }
  
  @Override 
  public IActivity getActivity(int activityId) {
    IActivity activity = new Activity(activityId);

    String sql = "SELECT distance_count FROM distance WHERE activity_id = %d";
    sql = String.format(sql, activity.getActivityId());
    ResultSet resultSet = super.executeStatementAndReturnResult(sql);
    
    try {
      if (resultSet.next()) {
        activity.setDistance(resultSet.getInt(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    sql = "SELECT calorie_count FROM calories WHERE activity_id = %d";
    sql = String.format(sql, activity.getActivityId());
    resultSet = super.executeStatementAndReturnResult(sql);
    
    try {
      if (resultSet.next()) {
        activity.setCalories(resultSet.getInt(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return activity;
  }

  @Override
  public boolean insertActivity(IActivity activity) {
    String sql = "INSERT INTO activity(activity_type) VALUES ('%s')";
    sql = String.format(sql, activity.getActivityType());

    if (!super.executeStatementAndSetKey(sql, activity)) {
      return false;
    }

    sql = "INSERT INTO distance(activity_id, distance_count) VALUES (%d, %d)";
    sql = String.format(sql, 
        activity.getActivityId(),
        activity.getDistance());

    if (!super.executeStatement(sql)) {
      return false;
    }

    sql = "INSERT INTO calories(activity_id, calorie_count) VALUES (%d, %d)";
    sql = String.format(sql, 
        activity.getActivityId(),
        activity.getCalories());

    return super.executeStatement(sql);
  }

  @Override
  public boolean updateActivity(IActivity activity) {
    String sql = "UPDATE distance SET distance_count = %d WHERE activity_id = %d";
    sql = String.format(sql, 
        activity.getDistance(),
        activity.getActivityId());

    if (!super.executeStatement(sql)) {
      return false;
    }

    sql = "UPDATE calories SET calorie_count = %d WHERE activity_id = %d";
    sql = String.format(sql,
        activity.getCalories(),
        activity.getActivityId());

    return super.executeStatement(sql);
  }

  @Override
  public boolean deleteActivity(IActivity activity) {
    String sql = "DELETE FROM activity WHERE activity_id = %d";
    sql = String.format(sql, activity.getActivityId());

    return super.executeStatement(sql);
  }

}
