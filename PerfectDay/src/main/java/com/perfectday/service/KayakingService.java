package com.perfectday.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectday.entities.Activity;
import com.perfectday.entities.Calories;
import com.perfectday.entities.Distance;
import com.perfectday.entities.KayakingResponse;
import com.perfectday.repository.IActivityRepository;
import com.perfectday.repository.ICaloriesRepository;
import com.perfectday.repository.IDateRepository;
import com.perfectday.repository.IDistanceRepository;
import com.perfectday.repository.IStepsRepository;

/**
 * This class represents the service for Kayaking activity and contains methods
 * that handle the business logic associated with various API calls pertaining
 * to this activity type.
 */

@Service
public class KayakingService {

	@Autowired
	IActivityRepository activityRepository;

	@Autowired
	ICaloriesRepository caloriesRepository;

	@Autowired
	IDistanceRepository distanceRepository;

	@Autowired
	IDateRepository datesRepository;

	@Autowired
	IStepsRepository stepsRepository;

	private KayakingResponse getKayakingResponsebyId(String activityType, Long id) {
		Optional<Activity> activity = activityRepository.findByActivityTypeAndId(activityType, id);
		if (activity.isPresent()) {
			KayakingResponse kayakingResponse = new KayakingResponse();
			Activity ac = activity.get();
			kayakingResponse.setActivityId(ac.getActivity_id());
			kayakingResponse.setKey(ac.getActivity_id());
			kayakingResponse.setActivityType(ac.getActivity_type());

			Optional<Calories> calories = caloriesRepository.findById(id);
			if (calories.isPresent()) {
				kayakingResponse.setCalories(calories.get().getCalorie_count());
			}

			Optional<Distance> distance = distanceRepository.findById(id);

			if (distance.isPresent()) {
				kayakingResponse.setDistance(distance.get().getDistance_count());
			}

			return kayakingResponse;
		} else {
			return null;
		}

	}

	public List<KayakingResponse> getActivity(String activityType) {
		Optional<List<Activity>> activity = activityRepository.findByActivityType(activityType);
		if (activity.isPresent()) {
			List<Activity> activities = activity.get();
			List<KayakingResponse> kayakingResponseList = new ArrayList();
			for (Activity ac : activities) {
				KayakingResponse kayakingResponse = getKayakingResponsebyId(activityType, ac.getActivity_id());
				if (kayakingResponse != null) {
					kayakingResponseList.add(kayakingResponse);
				}
			}
			return kayakingResponseList;

		} else {
			return null;
		}
	}

	public String createKayakingResponse(KayakingResponse response) {

		// inserting into activity table
		Activity ac = new Activity();
		ac.setActivity_type(response.getActivityType());
		Activity acResponse = activityRepository.saveAndFlush(ac);

		// inserting into calories table
		Calories calories = new Calories();
		calories.setActivity_id(acResponse.getActivity_id());
		calories.setCalorie_count(response.getCalories());
		caloriesRepository.saveAndFlush(calories);

		// inserting into distance table
		Distance distance = new Distance();
		distance.setActivity_id(acResponse.getActivity_id());
		distance.setDistance_count(response.getDistance());
		distanceRepository.saveAndFlush(distance);

		return "record added";
	}

	public KayakingResponse getActivitybyID(String activityType, Long id) {
		return getKayakingResponsebyId(activityType, id);
	}

	public String deleteActivitybyID(Long id) {
		if (activityRepository.existsById(id)) {
			activityRepository.deleteById(id);
			return "Deletion of ActivtyID " + id + " successful";

		} else {
			return "Deletion Failed. No record exists for ActivityID " + id;
		}
	}
}
