package com.perfectday.db.timedactivity;

import com.perfectday.db.activity.IActivityDao;

public interface ITimedActivityDao extends IActivityDao {
  
  ITimedActivity getActivity(int activityId);

  boolean insertActivity(ITimedActivity timedActivity);

  boolean updateActivity(ITimedActivity timedActivity);
}
