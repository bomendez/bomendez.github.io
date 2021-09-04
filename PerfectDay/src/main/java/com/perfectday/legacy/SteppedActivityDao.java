package com.perfectday.legacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public abstract class SteppedActivityDao extends TimedActivityDao implements ISteppedActivityDao {

  public SteppedActivityDao(Connection connection) {
    super(connection);
  }

  public SteppedActivityDao() {
    super(null);
  }
  
  @Override
  public ISteppedActivity getActivity(int activityId) {
    ITimedActivity activity = super.getActivity(activityId);
    ISteppedActivity steppedActivity = new SteppedActivity();
    steppedActivity.setActivityId(activityId);
    steppedActivity.setDistance(activity.getDistance());
    steppedActivity.setCalories(activity.getCalories());
    steppedActivity.setStartTime(activity.getStartTime());
    steppedActivity.setEndTime(activity.getEndTime());

    String sql = "SELECT step_count FROM steps WHERE activity_id = %d";
    sql = String.format(sql, activity.getActivityId());
    ResultSet resultSet = super.executeStatementAndReturnResult(sql);
    
    try {
      if (resultSet.next()) {
        steppedActivity.setStepCount(resultSet.getInt(1)); 
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return steppedActivity;
  }

  @Override
  public boolean insertActivity(ISteppedActivity steppedActivity) {
    if (!super.insertActivity((ITimedActivity)steppedActivity)) {
      return false;
    }

    String sql = "INSERT INTO steps(activity_id, step_count) VALUES (%d, %d)";
    sql = String.format(sql,
        steppedActivity.getActivityId(),
        steppedActivity.getStepCount());

    return super.executeStatement(sql);
  }

  @Override
  public boolean updateActivity(ISteppedActivity steppedActivity) {
    super.updateActivity((ITimedActivity)steppedActivity);

    String sql = "UPDATE steps SET step_count = %d WHERE activity_id = %d";
    sql = String.format(sql,
        steppedActivity.getStepCount(),
        steppedActivity.getActivityId());

    return super.executeStatement(sql);
  }
}
