package com.perfectday.db.walkingactivity;

import com.perfectday.db.steppedactivity.ISteppedActivity;
import com.perfectday.db.steppedactivity.SteppedActivityDao;
import com.perfectday.db.timedactivity.ITimedActivity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class WalkingActivityDao extends SteppedActivityDao implements IWalkingActivityDao {

  public WalkingActivityDao(Connection connection) {
    super(connection);
  }

  public WalkingActivityDao() {
    super(null);
  }

  @Override
  public ArrayList<IWalkingActivity> getAll() {
    ArrayList<IWalkingActivity> walkingActivityList = new ArrayList<IWalkingActivity>();
    
    String sql = "SELECT activity_id FROM activity WHERE activity_type = 'walking'";
    ResultSet resultSet = super.executeStatementAndReturnResult(sql);
    
    try {
      while (resultSet.next()) {
        walkingActivityList.add(getActivity(resultSet.getInt(1)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return walkingActivityList;
  }
  
  @Override
  public IWalkingActivity getActivity(int activityId) {
    ISteppedActivity activity = super.getActivity(activityId);
    IWalkingActivity walkingActivity = new WalkingActivity();
    walkingActivity.setActivityId(activityId);
    walkingActivity.setDistance(activity.getDistance());
    walkingActivity.setCalories(activity.getCalories());
    walkingActivity.setStartTime(activity.getStartTime());
    walkingActivity.setEndTime(activity.getEndTime());
    walkingActivity.setStepCount(activity.getStepCount());
    return walkingActivity;
  }

  @Override
  public boolean insertActivity(IWalkingActivity walkingActivity) {
    return super.insertActivity((ISteppedActivity)walkingActivity);
  }

  @Override
  public boolean updateActivity(IWalkingActivity walkingActivity) {
    return super.updateActivity((ISteppedActivity)walkingActivity);
  }
}
