package com.perfectday.db.runningactivity;

import com.perfectday.db.steppedactivity.ISteppedActivity;
import com.perfectday.db.steppedactivity.SteppedActivityDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class RunningActivityDao extends SteppedActivityDao implements IRunningActivityDao {

  public RunningActivityDao(Connection connection) {
    super(connection); 
  }

  public RunningActivityDao() {
    super(null);
  }

  @Override
  public ArrayList<IRunningActivity> getAll() {
    ArrayList<IRunningActivity> runningActivityList = new ArrayList<IRunningActivity>();
    
    String sql = "SELECT activity_id FROM activity WHERE activity_type = 'running'";
    ResultSet resultSet = super.executeStatementAndReturnResult(sql);
    
    try {
      while (resultSet.next()) {
        runningActivityList.add(getActivity(resultSet.getInt(1)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return runningActivityList;
  }
  
  @Override
  public IRunningActivity getActivity(int activityId) {
    ISteppedActivity activity = super.getActivity(activityId);
    IRunningActivity runningActivity = new RunningActivity();
    runningActivity.setActivityId(activityId);
    runningActivity.setDistance(activity.getDistance());
    runningActivity.setCalories(activity.getCalories());
    runningActivity.setStartTime(activity.getStartTime());
    runningActivity.setEndTime(activity.getEndTime());
    runningActivity.setStepCount(activity.getStepCount());
    return runningActivity;
  }

  @Override
  public boolean insertActivity(IRunningActivity runningActivity) {
    return super.insertActivity((ISteppedActivity)runningActivity);
  }

  @Override
  public boolean updateActivity(IRunningActivity runningActivity) {
    return super.updateActivity((ISteppedActivity)runningActivity);
  }
}
