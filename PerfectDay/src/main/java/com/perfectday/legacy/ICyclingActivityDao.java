package com.perfectday.legacy;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public interface ICyclingActivityDao extends ITimedActivityDao {

  ArrayList<ICyclingActivity> getAll();
  
  ICyclingActivity getActivity(int activityId);

  boolean insertActivity(ICyclingActivity cyclingActivity);

  boolean updateActivity(ICyclingActivity cyclingActivity);
}
