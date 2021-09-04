package com.perfectday.legacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public abstract class TimedActivityDao extends ActivityDao implements ITimedActivityDao {

  public TimedActivityDao(Connection connection) { 
    super(connection); 
  }

  public TimedActivityDao() {
    super(null);
  }
  
  @Override
  public ITimedActivity getActivity(int activityId) {
    IActivity activity = super.getActivity(activityId);
    ITimedActivity timedActivity = new TimedActivity();
    timedActivity.setActivityId(activityId);
    timedActivity.setCalories(activity.getCalories());
    timedActivity.setDistance(activity.getDistance());

    String sql = "SELECT start_time, end_time FROM dates WHERE activity_id = %d";
    sql = String.format(sql, activity.getActivityId());
    ResultSet resultSet = super.executeStatementAndReturnResult(sql);
    
    try {
      if (resultSet.next()) {
        timedActivity.setStartTime(resultSet.getString(1));
        timedActivity.setEndTime(resultSet.getString(2));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return timedActivity;
  }
  
  @Override
  public boolean insertActivity(ITimedActivity timedActivity) {
    if (!super.insertActivity(timedActivity)) {
      return false;
    }

    if (timedActivity.getStartTime().isEmpty() || timedActivity.getEndTime().isEmpty()) {
      return true;
    }

    String sql = "INSERT INTO dates(activity_id, start_time, end_time) VALUES (%d, '%s', '%s')";
    sql = String.format(sql,
        timedActivity.getActivityId(),
        timedActivity.getStartTime(),
        timedActivity.getEndTime());

    return super.executeStatement(sql);
  }

  @Override
  public boolean updateActivity(ITimedActivity timedActivity) {
    if (!super.updateActivity(timedActivity)) {
      return false;
    }

    if (timedActivity.getStartTime().isEmpty() || timedActivity.getEndTime().isEmpty()) {
      return true;
    }

    String sql = "UPDATE dates SET start_time = to_timestamp('%s'), end_time = to_timestamp('%s') WHERE activity_id = %d";
    sql = String.format(sql,
        timedActivity.getStartTime(),
        timedActivity.getEndTime(),
        timedActivity.getActivityId());

    return super.executeStatement(sql);
  }
}
