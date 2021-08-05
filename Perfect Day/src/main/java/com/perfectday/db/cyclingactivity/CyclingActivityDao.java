package com.perfectday.db.cyclingactivity;

import com.perfectday.db.timedactivity.ITimedActivity;
import com.perfectday.db.timedactivity.TimedActivityDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class CyclingActivityDao extends TimedActivityDao implements ICyclingActivityDao {

  public CyclingActivityDao(Connection connection) {
    super(connection);
  }
  
  public CyclingActivityDao() {
    super(null);
  }

  @Override
  public ArrayList<ICyclingActivity> getAll() {
    ArrayList<ICyclingActivity> cyclingActivityList = new ArrayList<ICyclingActivity>();
    
    String sql = "SELECT activity_id FROM activity WHERE activity_type = 'cycling'";
    ResultSet resultSet = super.executeStatementAndReturnResult(sql);
    
    try {
      while (resultSet.next()) {
        cyclingActivityList.add(getActivity(resultSet.getInt(1)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return cyclingActivityList;
  }
  
  @Override
  public ICyclingActivity getActivity(int activityId) {
    ITimedActivity activity = super.getActivity(activityId);
    ICyclingActivity CyclingActivity = new CyclingActivity();
    CyclingActivity.setActivityId(activityId);
    CyclingActivity.setDistance(activity.getDistance());
    CyclingActivity.setCalories(activity.getCalories());
    CyclingActivity.setStartTime(activity.getStartTime());
    CyclingActivity.setEndTime(activity.getEndTime());
    return CyclingActivity;
  }

  @Override
  public boolean insertActivity(ICyclingActivity cyclingActivity) {
    return super.insertActivity((ITimedActivity)cyclingActivity);
  }

  @Override
  public boolean updateActivity(ICyclingActivity cyclingActivity) {
    return super.updateActivity((ITimedActivity)cyclingActivity);
  }
}
