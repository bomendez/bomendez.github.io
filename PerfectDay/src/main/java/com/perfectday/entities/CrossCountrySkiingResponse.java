package com.perfectday.entities;

import org.springframework.stereotype.Component;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the response pertaining to API calls associated with Cross Country
 * Skiing activity.
 */

@Component
public class CrossCountrySkiingResponse {

	private double distance;

	private long activityId;

	private String activityType;

	private long calories;

	private long key;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public long getCalories() {
		return calories;
	}

	public void setCalories(long calories) {
		this.calories = calories;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long l) {
		this.key = l;
	}
}
