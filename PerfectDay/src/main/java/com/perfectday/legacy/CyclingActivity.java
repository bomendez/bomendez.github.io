package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public final class CyclingActivity extends TimedActivity implements ICyclingActivity {

  public CyclingActivity() {
    super("cycling");
  }
}
