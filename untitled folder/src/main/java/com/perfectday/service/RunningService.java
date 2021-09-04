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
import com.perfectday.entities.RunningResponse;
import com.perfectday.entities.Steps;
import com.perfectday.repository.IActivityRepository;
import com.perfectday.repository.ICaloriesRepository;
import com.perfectday.repository.IDateRepository;
import com.perfectday.repository.IDistanceRepository;
import com.perfectday.repository.IStepsRepository;

/**
 * This class represents the service for Running activity and contains methods
 * that handle the business logic associated with various API calls pertaining
 * to this activity type.
 */

@Service
public class RunningService {

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

	private RunningResponse getRunningResponsebyId(String activityType, Long id) {
		Optional<Activity> activity = activityRepository.findByActivityTypeAndId(activityType, id);
		if (activity.isPresent()) {
			RunningResponse runningResponse = new RunningResponse();
			Activity ac = activity.get();
			runningResponse.setActivityId(ac.getActivity_id());
			runningResponse.setKey(ac.getActivity_id());
			runningResponse.setActivityType(ac.getActivity_type());

			Optional<Calories> calories = caloriesRepository.findById(id);
			if (calories.isPresent()) {
				runningResponse.setCalories(calories.get().getCalorie_count());
			}

			Optional<Distance> distance = distanceRepository.findById(id);

			if (distance.isPresent()) {
				runningResponse.setDistance(distance.get().getDistance_count());
			}

			Optional<Dates> dates = datesRepository.findById(id);
			if (dates.isPresent()) {
				runningResponse.setStartTime(dates.get().getStart_time());
				runningResponse.setEndTime(dates.get().getEnd_time());
			}

			Optional<Steps> steps = stepsRepository.findById(id);

			if (steps.isPresent()) {
				runningResponse.setStepCount(steps.get().getStep_count());

			}

			return runningResponse;
		} else {
			return null;
		}

	}

	public List<RunningResponse> getActivity(String activityType) {
		Optional<List<Activity>> activity = activityRepository.findByActivityType(activityType);
		if (activity.isPresent()) {
			List<Activity> activities = activity.get();
			List<RunningResponse> runningResponseList = new ArrayList();
			for (Activity ac : activities) {
				RunningResponse runningResponse = getRunningResponsebyId(activityType, ac.getActivity_id());
				if (runningResponse != null) {
					runningResponseList.add(runningResponse);
				}
			}
			return runningResponseList;

		} else {
			return null;
		}
	}

	public RunningResponse getActivitybyID(String activityType, Long id) {
		return getRunningResponsebyId(activityType, id);
	}

	public String createRunningResponse(RunningResponse response) {

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

	public String deleteActivitybyID(Long id) {
		if (activityRepository.existsById(id)) {
			activityRepository.deleteById(id);
			return "Deletion of ActivtyID " + id + " successful";

		} else {
			return "Deletion Failed. No record exists for ActivityID " + id;
		}
	}
}
