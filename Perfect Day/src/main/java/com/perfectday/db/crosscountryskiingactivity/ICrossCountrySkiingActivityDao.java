package com.perfectday.db.crosscountryskiingactivity;

import java.util.ArrayList;
import com.perfectday.db.activity.IActivityDao;

public interface ICrossCountrySkiingActivityDao extends IActivityDao {
  
  ArrayList<ICrossCountrySkiingActivity> getAll();
  
  ICrossCountrySkiingActivity getActivity(int activityId);

  boolean insertActivity(ICrossCountrySkiingActivity crossCountrySkiingActivity);

  boolean updateActivity(ICrossCountrySkiingActivity crossCountrySkiingActivity);
}
