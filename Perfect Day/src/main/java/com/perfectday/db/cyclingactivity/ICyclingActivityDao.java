package com.perfectday.db.cyclingactivity;

import java.util.ArrayList;
import com.perfectday.db.timedactivity.ITimedActivityDao;

public interface ICyclingActivityDao extends ITimedActivityDao {

  ArrayList<ICyclingActivity> getAll();
  
  ICyclingActivity getActivity(int activityId);

  boolean insertActivity(ICyclingActivity cyclingActivity);

  boolean updateActivity(ICyclingActivity cyclingActivity);
}
