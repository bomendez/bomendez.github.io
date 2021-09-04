package com.perfectday.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.perfectday.service.ActivityTypesService;

/**
 * This class represents the web controller of the Perfect Day application and
 * generates web outputs that demonstrate the count of activity types in the
 * form of bar and pie charts in a web application with relevant get mappings.
 */

@Controller
public class PerfectDayWebController {

	@Autowired
	ActivityTypesService activityTypesService;
	
	@GetMapping("/perfectday/index")
	public String index() {
	  return "Index";
	}

	@GetMapping("/perfectday/activityChart")
	public String greeting(@RequestParam(name = "activity_id", required = false, defaultValue = "0") int id,
			Model model) {
		model.addAttribute("skiingSize", activityTypesService.countByActivityType("cross_country_skiing"));
		model.addAttribute("cyclingSize", activityTypesService.countByActivityType("cycling"));
		model.addAttribute("kayakingSize", activityTypesService.countByActivityType("kayaking"));
		model.addAttribute("runningSize", activityTypesService.countByActivityType("running"));
		model.addAttribute("walkingSize", activityTypesService.countByActivityType("walking"));
		return "ActivityChart";
	}

	@GetMapping("/perfectday/activityPieChart")
	public String pieChart(@RequestParam(name = "activity_id", required = false, defaultValue = "0") int id,
			Model model) {
		model.addAttribute("skiingSize", activityTypesService.countByActivityType("cross_country_skiing"));
		model.addAttribute("cyclingSize", activityTypesService.countByActivityType("cycling"));
		model.addAttribute("kayakingSize", activityTypesService.countByActivityType("kayaking"));
		model.addAttribute("runningSize", activityTypesService.countByActivityType("running"));
		model.addAttribute("walkingSize", activityTypesService.countByActivityType("walking"));
		return "ActivityPieChart";

	}

}