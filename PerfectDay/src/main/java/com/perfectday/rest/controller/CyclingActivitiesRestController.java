package com.perfectday.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perfectday.entities.CyclingResponse;
import com.perfectday.service.CyclingService;

/**
 * This class represents the Rest Controller for Cycling Activities and handles
 * get, post and delete mappings to retrieve, add and delete data pertaining to
 * this activity type.
 */

@RestController
public class CyclingActivitiesRestController {

	@Autowired
	CyclingService activityService;

	@GetMapping(value = "/activities/cyclingActivities")
	public List<CyclingResponse> getAllWalkingActivities() {
		return activityService.getActivity("cycling");
	}

	@GetMapping(value = "/activities/cyclingActivity/{activityId}")
	public CyclingResponse getCyclingActivity(@PathVariable Long activityId) {
		return activityService.getActivitybyID("cycling", activityId);
	}

	@PostMapping(value = "/activities/cyclingActivity")
	public String insertCyclingctivity(@RequestBody CyclingResponse response) {
		return activityService.createCyclingResponse(response);
	}

	@DeleteMapping(value = "/activities/cyclingActivity/{activityId}")
	public String deleteCyclingActivity(@PathVariable Long activityId) {
		return activityService.deleteActivitybyID(activityId);
	}
}
