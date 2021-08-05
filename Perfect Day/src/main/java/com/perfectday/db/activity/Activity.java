package com.perfectday.db.activity;

import com.perfectday.db.Dto;

public class Activity extends Dto implements IActivity {
  private String m_activityType = "";
  private int m_distance = 0;
  private int m_calories = 0;

  public Activity(String activityType) {
    setActivityType(activityType);
  }

  public Activity(String activityType, int distance, int calories) {
    setActivityType(activityType);
    setDistance(distance);
    setCalories(calories);
  }

  public Activity(int activityId, String activityType, int distance, int calories) {
    super.setKey(activityId);
    setActivityType(activityType);
    setDistance(distance);
    setCalories(calories);
  }

  public Activity(int activityId) {
    super.setKey(activityId);
  }

  public Activity() {
  }

  @Override
  public int getActivityId() {
    return super.getKey();
  }

  @Override
  public void setActivityId(int activityId) {
    super.setKey(activityId);
  }

  @Override
  public String getActivityType() {
    return m_activityType;
  }

  @Override
  public void setActivityType(String activityType) {
    m_activityType = activityType;
  }

  @Override
  public int getDistance() { 
    return m_distance; 
  }

  @Override
  public void setDistance(int distance) { 
    m_distance = distance; 
  }

  @Override
  public int getCalories() { 
    return m_calories; 
  }

  @Override
  public void setCalories(int calories) { 
    m_calories = calories; 
  }

}
