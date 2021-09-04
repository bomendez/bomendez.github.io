package com.perfectday.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectday.entities.Activity;
import com.perfectday.entities.Calories;
import com.perfectday.entities.Dates;
import com.perfectday.entities.Distance;
import com.perfectday.entities.Steps;
import com.perfectday.entities.WalkingResponse;
import com.perfectday.repository.IActivityRepository;
import com.perfectday.repository.ICaloriesRepository;
import com.perfectday.repository.IDateRepository;
import com.perfectday.repository.IDistanceRepository;
import com.perfectday.repository.IStepsRepository;

/**
 * This class represents the service for Walking activity and contains methods
 * that handle the business logic associated with various API calls pertaining
 * to this activity type.
 */

@Service
public class WalkingService {

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

	private WalkingResponse getWalkingResponsebyId(String activityType, Long id) {
		Optional<Activity> activity = activityRepository.findByActivityTypeAndId(activityType, id);
		if (activity.isPresent()) {
			WalkingResponse walkingResponse = new WalkingResponse();
			Activity ac = activity.get();
			walkingResponse.setActivityId(ac.getActivity_id());
			walkingResponse.setKey(ac.getActivity_id());
			walkingResponse.setActivityType(ac.getActivity_type());

			Optional<Calories> calories = caloriesRepository.findById(id);
			if (calories.isPresent()) {
				walkingResponse.setCalories(calories.get().getCalorie_count());
			}

			Optional<Distance> distance = distanceRepository.findById(id);

			if (distance.isPresent()) {
				walkingResponse.setDistance(distance.get().getDistance_count());
			}

			Optional<Dates> dates = datesRepository.findById(id);
			if (dates.isPresent()) {
				walkingResponse.setStartTime(dates.get().getStart_time());
				walkingResponse.setEndTime(dates.get().getEnd_time());
			}

			Optional<Steps> steps = stepsRepository.findById(id);

			if (steps.isPresent()) {
				walkingResponse.setStepCount(steps.get().getStep_count());

			}

			return walkingResponse;
		} else {
			return null;
		}

	}

	public List<WalkingResponse> getActivity(String activityType) {
		Optional<List<Activity>> activity = activityRepository.findByActivityType(activityType);
		if (activity.isPresent()) {
			List<Activity> activities = activity.get();
			List<WalkingResponse> walkingResponseList = new ArrayList();
			for (Activity ac : activities) {
				WalkingResponse walkingResponse = getWalkingResponsebyId(activityType, ac.getActivity_id());
				if (walkingResponse != null) {
					walkingResponseList.add(walkingResponse);
				}
			}
			return walkingResponseList;

		} else {
			return null;
		}
	}

	public String createWalkingResponse(WalkingResponse response) {

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

		// inserting into steps table
		Steps steps = new Steps();
		steps.setActivity_id(acResponse.getActivity_id());
		steps.setStep_count(response.getStepCount());
		stepsRepository.saveAndFlush(steps);

		// inserting into distance table
		Distance distance = new Distance();
		distance.setActivity_id(acResponse.getActivity_id());
		distance.setDistance_count(response.getDistance());
		distanceRepository.saveAndFlush(distance);

		return "record added";
	}

	public WalkingResponse getActivitybyID(String activityType, Long id) {
		return getWalkingResponsebyId(activityType, id);
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
