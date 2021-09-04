package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public interface ISteppedActivity extends ITimedActivity {

  int getStepCount();

  void setStepCount(int stepCount);
}
