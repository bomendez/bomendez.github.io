package com.perfectday.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perfectday.entities.WalkingResponse;
import com.perfectday.service.WalkingService;

/**
 * This class represents the Rest Controller for Walking Activities and handles
 * get, post and delete mappings to retrieve, add and delete data pertaining to
 * this activity type.
 */

@RestController
public class WalkingActivitiesRestController {

	@Autowired
	WalkingService activityService;

	@GetMapping(value = "/activities/walkingActivities")
	public List<WalkingResponse> getAllWalkingActivities() {
		return activityService.getActivity("walking");
	}

	@GetMapping(value = "/activities/walkingActivity/{activityId}")
	public WalkingResponse getWalkingActivity(@PathVariable Long activityId) {
		return activityService.getActivitybyID("walking", activityId);
	}

	@PostMapping(value = "/activities/walkingActivity")
	public String insertWalkingActivity(@RequestBody WalkingResponse response) {
		return activityService.createWalkingResponse(response);
	}

	@DeleteMapping(value = "/activities/walkingActivity/{activityId}")
	public String deleteWalkingActivity(@PathVariable Long activityId) {
		return activityService.deleteActivitybyID(activityId);
	}
}
