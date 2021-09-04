package com.perfectday.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectday.entities.Activity;
import com.perfectday.entities.Calories;
import com.perfectday.entities.CrossCountrySkiingResponse;
import com.perfectday.entities.Distance;
import com.perfectday.repository.IActivityRepository;
import com.perfectday.repository.ICaloriesRepository;
import com.perfectday.repository.IDistanceRepository;

/**
 * This class represents the service for Cross Country Skiing activity and
 * contains methods that handle the business logic associated with various API
 * calls pertaining to this activity type.
 */

@Service
public class CrossCountrySkiingService {

	@Autowired
	IActivityRepository activityRepository;

	@Autowired
	ICaloriesRepository caloriesRepository;

	@Autowired
	IDistanceRepository distanceRepository;

	private CrossCountrySkiingResponse getCrossCountrySkiingResponsebyId(String activityType, Long id) {
		Optional<Activity> activity = activityRepository.findByActivityTypeAndId(activityType, id);

		if (activity.isPresent()) {
			CrossCountrySkiingResponse crossCountrySkiingResponse = new CrossCountrySkiingResponse();
			Activity ac = activity.get();
			crossCountrySkiingResponse.setActivityId(ac.getActivity_id());
			crossCountrySkiingResponse.setKey(ac.getActivity_id());
			crossCountrySkiingResponse.setActivityType(ac.getActivity_type());

			Optional<Calories> calories = caloriesRepository.findById(id);

			if (calories.isPresent()) {
				crossCountrySkiingResponse.setCalories(calories.get().getCalorie_count());
			}

			Optional<Distance> distance = distanceRepository.findById(id);

			if (distance.isPresent()) {
				crossCountrySkiingResponse.setDistance(distance.get().getDistance_count());
			}

			return crossCountrySkiingResponse;
		} else {
			return null;
		}
	}

	public List<CrossCountrySkiingResponse> getActivity(String activityType) {
		Optional<List<Activity>> activity = activityRepository.findByActivityType(activityType);

		if (activity.isPresent()) {
			List<Activity> activities = activity.get();
			List<CrossCountrySkiingResponse> crossCountrySkiingResponseList = new ArrayList();

			for (Activity ac : activities) {
				CrossCountrySkiingResponse crossCountrySkiingResponse = getCrossCountrySkiingResponsebyId(activityType,
						ac.getActivity_id());

				if (crossCountrySkiingResponse != null) {
					crossCountrySkiingResponseList.add(crossCountrySkiingResponse);
				}
			}

			return crossCountrySkiingResponseList;

		}

		return null;
	}

	public String createCrossCounrySkiingResponse(CrossCountrySkiingResponse response) {

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

	public CrossCountrySkiingResponse getActivitybyID(String activityType, Long id) {
		return getCrossCountrySkiingResponsebyId(activityType, id);
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
