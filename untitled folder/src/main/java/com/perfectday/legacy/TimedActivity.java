package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public class TimedActivity extends Activity implements ITimedActivity {
  private String m_startTime = "";
  private String m_endTime = "";

  public TimedActivity(String activityType) {
    super(activityType);
  }
  
  public TimedActivity() {
    super();
  }

  @Override
  public String getStartTime() {
    return this.m_startTime;
  }

  @Override
  public void setStartTime(String startTime) {
    this.m_startTime = startTime;
  }

  @Override
  public String getEndTime() {
    return this.m_endTime;
  }

  @Override
  public void setEndTime(String endTime) {
    this.m_endTime = endTime;
  }
}
