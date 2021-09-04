package com.perfectday.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perfectday.entities.RunningResponse;
import com.perfectday.service.RunningService;

/**
 * This class represents the Rest Controller for Running Activities and handles
 * get, post and delete mappings to retrieve, add and delete data pertaining to
 * this activity type.
 */

@RestController
public class RunningActivitiesRestController {

	@Autowired
	RunningService activityService;

	@GetMapping(value = "/activities/runningActivities")
	public List<RunningResponse> getAllRunningActivities() {
		return activityService.getActivity("running");
	}

	@GetMapping(value = "/activities/runningActivity/{activityId}")
	public RunningResponse getRunningActivity(@PathVariable Long activityId) {
		return activityService.getActivitybyID("running", activityId);
	}

	@PostMapping(value = "/activities/runningActivity")
	public String insertWalkingActivity(@RequestBody RunningResponse response) {
		return activityService.createRunningResponse(response);
	}

	@DeleteMapping(value = "/activities/runningActivity/{activityId}")
	public String deleteWalkingActivity(@PathVariable Long activityId) {
		return activityService.deleteActivitybyID(activityId);
	}
}
