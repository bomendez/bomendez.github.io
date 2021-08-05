package com.perfectday;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.perfectday.controller.IPerfectDayController;
import com.perfectday.controller.PerfectDayController;
import com.perfectday.model.IPerfectDayModel;
import com.perfectday.model.PerfectDayModel;
import com.perfectday.view.IPerfectDayView;
import com.perfectday.view.PerfectDayView;


@SpringBootApplication
public class PerfectdayApplication {

  public static void main(String[] args) {
    SpringApplicationBuilder builder = new SpringApplicationBuilder(PerfectdayApplication.class);
    builder.headless(false);
    ConfigurableApplicationContext context = builder.run(args);

    IPerfectDayModel model = new PerfectDayModel();
    IPerfectDayController controller = new PerfectDayController(model);

    IPerfectDayView view = new PerfectDayView("Perfect Day", controller);
  }

}
