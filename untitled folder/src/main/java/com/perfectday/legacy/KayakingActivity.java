package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public final class KayakingActivity extends Activity implements IKayakingActivity {

  public KayakingActivity() {
    super("kayaking");
  }
}
