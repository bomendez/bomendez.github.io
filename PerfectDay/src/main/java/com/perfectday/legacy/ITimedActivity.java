package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public interface ITimedActivity extends IActivity {

  String getStartTime();

  void setStartTime(String startTime);

  String getEndTime();

  void setEndTime(String endTime);
}
