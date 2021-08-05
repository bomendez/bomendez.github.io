package com.perfectday.db.activity;

public interface IActivityDao {
  
  IActivity getActivity(int activityId);

  boolean insertActivity(IActivity activity);

  boolean updateActivity(IActivity activity);

  boolean deleteActivity(IActivity activity);
}
