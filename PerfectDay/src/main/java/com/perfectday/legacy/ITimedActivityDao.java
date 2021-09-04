package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public interface ITimedActivityDao extends IActivityDao {
  
  ITimedActivity getActivity(int activityId);

  boolean insertActivity(ITimedActivity timedActivity);

  boolean updateActivity(ITimedActivity timedActivity);
}
