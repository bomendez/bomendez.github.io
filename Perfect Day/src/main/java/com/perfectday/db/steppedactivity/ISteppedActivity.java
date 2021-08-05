package com.perfectday.db.steppedactivity;

import com.perfectday.db.timedactivity.ITimedActivity;

public interface ISteppedActivity extends ITimedActivity {

  int getStepCount();

  void setStepCount(int stepCount);
}
