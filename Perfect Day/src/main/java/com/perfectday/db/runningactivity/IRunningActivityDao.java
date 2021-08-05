package com.perfectday.db.runningactivity;

import java.util.ArrayList;
import com.perfectday.db.steppedactivity.ISteppedActivityDao;

public interface IRunningActivityDao extends ISteppedActivityDao {
  
  ArrayList<IRunningActivity> getAll();
  
  IRunningActivity getActivity(int activityId);

  boolean insertActivity(IRunningActivity runningActivity);

  boolean updateActivity(IRunningActivity runningActivity);
}
