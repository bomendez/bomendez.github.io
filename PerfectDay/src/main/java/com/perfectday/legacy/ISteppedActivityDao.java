package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public interface ISteppedActivityDao extends ITimedActivityDao {
  
  ISteppedActivity getActivity(int activityId);

  boolean insertActivity(ISteppedActivity steppedActivity);

  boolean updateActivity(ISteppedActivity steppedActivity);
}
