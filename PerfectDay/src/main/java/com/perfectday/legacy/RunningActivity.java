package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public final class RunningActivity extends SteppedActivity implements IRunningActivity {

  public RunningActivity() {
    super("running");
  }
}
