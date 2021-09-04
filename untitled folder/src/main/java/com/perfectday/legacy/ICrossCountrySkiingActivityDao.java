package com.perfectday.legacy;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public interface ICrossCountrySkiingActivityDao extends IActivityDao {
  
  ArrayList<ICrossCountrySkiingActivity> getAll();
  
  ICrossCountrySkiingActivity getActivity(int activityId);

  boolean insertActivity(ICrossCountrySkiingActivity crossCountrySkiingActivity);

  boolean updateActivity(ICrossCountrySkiingActivity crossCountrySkiingActivity);
}
