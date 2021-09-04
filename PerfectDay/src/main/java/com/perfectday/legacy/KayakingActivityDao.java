package com.perfectday.legacy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.perfectday.legacy.IKayakingActivity;
import com.perfectday.legacy.KayakingActivity;

@Component
public final class KayakingActivityDao extends ActivityDao implements IKayakingActivityDao {

  public KayakingActivityDao(Connection connection) {
    super(connection); 
  }

  public KayakingActivityDao() {
    super(null);
  }

  @Override
  public ArrayList<IKayakingActivity> getAll() {
    ArrayList<IKayakingActivity> kayakingActivityList = new ArrayList<IKayakingActivity>();
    
    String sql = "SELECT activity_id FROM activity WHERE activity_type = 'kayaking'";
    ResultSet resultSet = super.executeStatementAndReturnResult(sql);
    
    try {
      while (resultSet.next()) {
        kayakingActivityList.add(getActivity(resultSet.getInt(1)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return kayakingActivityList;
  }
  
  @Override
  public IKayakingActivity getActivity(int activityId) {
    IActivity activity = super.getActivity(activityId);
    IKayakingActivity KayakingActivity = new KayakingActivity();
    KayakingActivity.setActivityId(activityId);
    KayakingActivity.setDistance(activity.getDistance());
    KayakingActivity.setCalories(activity.getCalories());
    return KayakingActivity;
  }

  @Override
  public boolean insertActivity(IKayakingActivity kayakingActivity) {
    return super.insertActivity((IActivity)kayakingActivity);
  }

  @Override
  public boolean updateActivity(IKayakingActivity kayakingActivity) {
    return super.updateActivity((IActivity)kayakingActivity);
  }
}
