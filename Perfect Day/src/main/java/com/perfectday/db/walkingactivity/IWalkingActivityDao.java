package com.perfectday.db.walkingactivity;

import java.util.ArrayList;
import com.perfectday.db.steppedactivity.ISteppedActivityDao;

public interface IWalkingActivityDao extends ISteppedActivityDao {
  
  ArrayList<IWalkingActivity> getAll();
  
  IWalkingActivity getActivity(int activityId);

  boolean insertActivity(IWalkingActivity walkingActivity);

  boolean updateActivity(IWalkingActivity walkingActivity);
}
