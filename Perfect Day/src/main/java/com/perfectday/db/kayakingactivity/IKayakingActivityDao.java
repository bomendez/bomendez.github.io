package com.perfectday.db.kayakingactivity;

import java.util.ArrayList;
import com.perfectday.db.activity.IActivityDao;

public interface IKayakingActivityDao extends IActivityDao {

  ArrayList<IKayakingActivity> getAll();
  
  IKayakingActivity getActivity(int activityId);

  boolean insertActivity(IKayakingActivity kayakingActivity);

  boolean updateActivity(IKayakingActivity kayakingActivity);
}
