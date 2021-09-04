package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public interface IActivityDao {
  
  IActivity getActivity(int activityId);

  boolean insertActivity(IActivity activity);

  boolean updateActivity(IActivity activity);

  boolean deleteActivity(IActivity activity);
}
