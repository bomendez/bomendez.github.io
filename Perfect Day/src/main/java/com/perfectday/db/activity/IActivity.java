package com.perfectday.db.activity;

import com.perfectday.db.IDto;

public interface IActivity extends IDto {

  int getActivityId();

  void setActivityId(int activityId);

  String getActivityType();
  
  void setActivityType(String activityType);

  int getDistance();

  void setDistance(int distance);

  int getCalories();

  void setCalories(int calories);
}
