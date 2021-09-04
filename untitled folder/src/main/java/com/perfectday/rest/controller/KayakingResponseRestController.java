package com.perfectday.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perfectday.entities.KayakingResponse;
import com.perfectday.service.KayakingService;

/**
 * This class represents the Rest Controller for Kayaking Activities and handles
 * get, post and delete mappings to retrieve, add and delete data pertaining to
 * this activity type.
 */

@RestController
public class KayakingResponseRestController {

	@Autowired
	KayakingService activityService;

	@GetMapping(value = "/activities/kayakingActivities")
	public List<KayakingResponse> getAllKayakingActivities() {
		return activityService.getActivity("kayaking");
	}

	@GetMapping(value = "/activities/kayakingActivity/{activityId}")
	public KayakingResponse getKayakingActivity(@PathVariable Long activityId) {
		return activityService.getActivitybyID("kayaking", activityId);
	}

	@PostMapping(value = "/activities/kayakingActivity")
	public String insertKayakingActivity(@RequestBody KayakingResponse response) {
		return activityService.createKayakingResponse(response);
	}

	@DeleteMapping(value = "/activities/kayakingActivity/{activityId}")
	public String deleteKayakingActivity(@PathVariable Long activityId) {
		return activityService.deleteActivitybyID(activityId);
	}
}
