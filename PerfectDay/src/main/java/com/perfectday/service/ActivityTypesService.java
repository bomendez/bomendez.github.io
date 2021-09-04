package com.perfectday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectday.entities.ActivityTypes;
import com.perfectday.repository.IActivityRepository;
import com.perfectday.repository.IActivityTypesRepository;

/**
 * This class represents the service for Activity Types and contains methods
 * that handle the business logic associated with writing data to the table and
 * retrieving the count of each activity type.
 */

@Service
public class ActivityTypesService {

	@Autowired
	IActivityTypesRepository placeRepository;

	@Autowired
	IActivityRepository activityRepository;

	public ActivityTypes createActivityTypes(ActivityTypes a) {
		return placeRepository.saveAndFlush(a);
	}

	public int countByActivityType(String activityType) {
		return activityRepository.countByActivityType(activityType);

	}

}
