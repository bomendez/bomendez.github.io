package com.perfectday.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perfectday.entities.CrossCountrySkiingResponse;
import com.perfectday.service.CrossCountrySkiingService;

/**
 * This class represents the Rest Controller for Cross Country Skiing Activities
 * and handles get, post and delete mappings to retrieve, add and delete data
 * pertaining to this activity type.
 */

@RestController
public class CrossCountrySkiingActivitiesRestController {

	@Autowired
	CrossCountrySkiingService activityService;

	@GetMapping(value = "/activities/crossCountrySkiing")
	public List<CrossCountrySkiingResponse> getAllCrossCountrySkiingActivities() {
		return activityService.getActivity("cross_country_skiing");
	}

	@GetMapping(value = "/activities/crossCountrySkiingActivity/{activityId}")
	public CrossCountrySkiingResponse getCrossCountrySkiingActivity(@PathVariable Long activityId) {
		return activityService.getActivitybyID("cross_country_skiing", activityId);
	}

	@PostMapping(value = "/activities/crossCountrySkiingActivity")
	public String insertCrossCountrySkiingActivity(@RequestBody CrossCountrySkiingResponse response) {
		return activityService.createCrossCounrySkiingResponse(response);
	}

	@DeleteMapping(value = "/activities/crossCountrySkiingActivities/{activityId}")
	public String deleteCrossCountrySkiingActivity(@PathVariable Long activityId) {
		return activityService.deleteActivitybyID(activityId);
	}
}
