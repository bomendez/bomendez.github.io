package com.perfectday.db.timedactivity;

import com.perfectday.db.activity.IActivity;

public interface ITimedActivity extends IActivity {

  String getStartTime();

  void setStartTime(String startTime);

  String getEndTime();

  void setEndTime(String endTime);
}
