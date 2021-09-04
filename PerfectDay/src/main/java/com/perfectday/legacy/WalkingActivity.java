package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public final class WalkingActivity extends SteppedActivity implements IWalkingActivity {

  public WalkingActivity() {
    super("walking");
  }
}
