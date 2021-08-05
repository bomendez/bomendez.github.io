package com.perfectday.db.crosscountryskiingactivity;

import com.perfectday.db.activity.ActivityDao;
import com.perfectday.db.activity.IActivity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class CrossCountrySkiingActivityDao extends ActivityDao implements ICrossCountrySkiingActivityDao {

  public CrossCountrySkiingActivityDao(Connection connection) {
    super(connection); 
  }

  public CrossCountrySkiingActivityDao() {
    super(null);
  }

  @Override
  public ArrayList<ICrossCountrySkiingActivity> getAll() {
    ArrayList<ICrossCountrySkiingActivity> crossCountrySkiingActivityList = new ArrayList<ICrossCountrySkiingActivity>();

    String sql = "SELECT activity_id FROM activity WHERE activity_type = 'crossCountrySkiing'";
    ResultSet resultSet = super.executeStatementAndReturnResult(sql);

    try {
      while (resultSet.next()) {
        crossCountrySkiingActivityList.add(getActivity(resultSet.getInt(1)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return crossCountrySkiingActivityList;
  }

  @Override
  public ICrossCountrySkiingActivity getActivity(int activityId) {
    IActivity activity = super.getActivity(activityId);
    ICrossCountrySkiingActivity CrossCountrySkiingActivity = new CrossCountrySkiingActivity();
    CrossCountrySkiingActivity.setActivityId(activityId);
    CrossCountrySkiingActivity.setDistance(activity.getDistance());
    CrossCountrySkiingActivity.setCalories(activity.getCalories());
    return CrossCountrySkiingActivity;
  }

  @Override
  public boolean insertActivity(ICrossCountrySkiingActivity crossCountrySkiingActivity) {
    return super.insertActivity((IActivity)crossCountrySkiingActivity);
  }

  @Override
  public boolean updateActivity(ICrossCountrySkiingActivity crossCountrySkiingActivity) {
    return super.updateActivity((IActivity)crossCountrySkiingActivity);
  }
}
