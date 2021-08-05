package com.perfectday.db.steppedactivity;

import com.perfectday.db.timedactivity.TimedActivity;

public class SteppedActivity extends TimedActivity implements ISteppedActivity {
  private int m_stepCount = 0;

  public SteppedActivity(String activityType) {
    super(activityType);
  }

  public SteppedActivity() {
    super(null);
  }

  @Override
  public int getStepCount() {
    return m_stepCount;
  }

  @Override
  public void setStepCount(int stepCount) {
    m_stepCount = stepCount;
  }
}
