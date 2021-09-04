package com.perfectday.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectday.entities.Activity;
import com.perfectday.entities.Calories;
import com.perfectday.entities.CyclingResponse;
import com.perfectday.entities.Dates;
import com.perfectday.entities.Distance;
import com.perfectday.repository.IActivityRepository;
import com.perfectday.repository.ICaloriesRepository;
import com.perfectday.repository.IDateRepository;
import com.perfectday.repository.IDistanceRepository;

/**
 * This class represents the service for Cycling activity and contains methods
 * that handle the business logic associated with various API calls pertaining
 * to this activity type.
 */

@Service
public class CyclingService {

	@Autowired
	IActivityRepository activityRepository;

	@Autowired
	ICaloriesRepository caloriesRepository;

	@Autowired
	IDistanceRepository distanceRepository;

	@Autowired
	IDateRepository datesRepository;

	private CyclingResponse getCyclingResponsebyId(String activityType, Long id) {
		Optional<Activity> activity = activityRepository.findByActivityTypeAndId(activityType, id);
		if (activity.isPresent()) {
			CyclingResponse cyclingResponse = new CyclingResponse();
			Activity ac = activity.get();
			cyclingResponse.setActivityId(ac.getActivity_id());
			cyclingResponse.setKey(ac.getActivity_id());
			cyclingResponse.setActivityType(ac.getActivity_type());

			Optional<Calories> calories = caloriesRepository.findById(id);
			if (calories.isPresent()) {
				cyclingResponse.setCalories(calories.get().getCalorie_count());
			}

			Optional<Distance> distance = distanceRepository.findById(id);

			if (distance.isPresent()) {
				cyclingResponse.setDistance(distance.get().getDistance_count());
			}

			Optional<Dates> dates = datesRepository.findById(id);
			if (dates.isPresent()) {
				cyclingResponse.setStartTime(dates.get().getStart_time());
				cyclingResponse.setEndTime(dates.get().getEnd_time());
			}

			return cyclingResponse;
		} else {
			return null;
		}

	}

	public List<CyclingResponse> getActivity(String activityType) {
		Optional<List<Activity>> activity = activityRepository.findByActivityType(activityType);
		if (activity.isPresent()) {
			List<Activity> activities = activity.get();
			List<CyclingResponse> cyclingResponseList = new ArrayList();
			for (Activity ac : activities) {
				CyclingResponse cyclingResponse = getCyclingResponsebyId(activityType, ac.getActivity_id());
				if (cyclingResponse != null) {
					cyclingResponseList.add(cyclingResponse);
				}
			}
			return cyclingResponseList;

		} else {
			return null;
		}
	}

	public CyclingResponse getActivitybyID(String activityType, Long id) {
		return getCyclingResponsebyId(activityType, id);
	}

	public String createCyclingResponse(CyclingResponse response) {

		// inserting into activity table
		Activity ac = new Activity();
		ac.setActivity_type(response.getActivityType());
		Activity acResponse = activityRepository.saveAndFlush(ac);

		// inserting into calories table
		Calories calories = new Calories();
		calories.setActivity_id(acResponse.getActivity_id());
		calories.setCalorie_count(response.getCalories());
		caloriesRepository.saveAndFlush(calories);

		// inserting into dates table
		Dates dates = new Dates();
		dates.setActivity_id(acResponse.getActivity_id());
		dates.setStart_time(response.getStartTime());
		dates.setEnd_time(response.getEndTime());
		datesRepository.saveAndFlush(dates);

		// inserting into distance table
		Distance distance = new Distance();
		distance.setActivity_id(acResponse.getActivity_id());
		distance.setDistance_count(response.getDistance());
		distanceRepository.saveAndFlush(distance);

		return "record added";
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
