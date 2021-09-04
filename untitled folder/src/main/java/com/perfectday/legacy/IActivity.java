package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
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
