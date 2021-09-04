package com.perfectday.legacy;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public interface IRunningActivityDao extends ISteppedActivityDao {
  
  ArrayList<IRunningActivity> getAll();
  
  IRunningActivity getActivity(int activityId);

  boolean insertActivity(IRunningActivity runningActivity);

  boolean updateActivity(IRunningActivity runningActivity);
}
