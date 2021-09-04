package com.perfectday;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.perfectday.model.IPerfectDayModel;
import com.perfectday.model.PerfectDayModel;
import com.perfectday.view.PerfectDayView;

/**
 * This class represents the main method for the Perfect Day application. This
 * class calls on the appropriate methods to initialise the pop up to upload the
 * appropriate files to parse, and the methods to activate the RestAPIs and the
 * Web Controller.
 */

@SpringBootApplication(scanBasePackages = { "com.perfectday.*" })
public class PerfectdayApplication {

	private static PerfectDayView view;

	@Autowired
	private PerfectDayView viewResponse;

	@PostConstruct
	private void initStaticDao() {
		view = this.viewResponse;
	}

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PerfectdayApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);

		IPerfectDayModel model = new PerfectDayModel();

		view.start();

	}
}
