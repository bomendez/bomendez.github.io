package com.perfectday.legacy;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public interface IKayakingActivityDao extends IActivityDao {

  ArrayList<IKayakingActivity> getAll();
  
  IKayakingActivity getActivity(int activityId);

  boolean insertActivity(IKayakingActivity kayakingActivity);

  boolean updateActivity(IKayakingActivity kayakingActivity);
}
