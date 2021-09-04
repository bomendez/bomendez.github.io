package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public final class CrossCountrySkiingActivity extends Activity implements ICrossCountrySkiingActivity {

  public CrossCountrySkiingActivity() {
    super("cross_country_skiing");
  }
}
