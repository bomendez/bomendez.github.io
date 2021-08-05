package com.perfectday.db.steppedactivity;

import com.perfectday.db.timedactivity.ITimedActivityDao;

public interface ISteppedActivityDao extends ITimedActivityDao {
  
  ISteppedActivity getActivity(int activityId);

  boolean insertActivity(ISteppedActivity steppedActivity);

  boolean updateActivity(ISteppedActivity steppedActivity);
}
