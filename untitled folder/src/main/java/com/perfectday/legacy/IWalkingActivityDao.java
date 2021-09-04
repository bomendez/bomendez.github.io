package com.perfectday.legacy;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public interface IWalkingActivityDao extends ISteppedActivityDao {
  
  ArrayList<IWalkingActivity> getAll();
  
  IWalkingActivity getActivity(int activityId);

  boolean insertActivity(IWalkingActivity walkingActivity);

  boolean updateActivity(IWalkingActivity walkingActivity);
}
