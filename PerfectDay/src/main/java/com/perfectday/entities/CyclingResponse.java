package com.perfectday.entities;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the response pertaining to API calls associated with Cycling activity.
 */

@Component
public class CyclingResponse {
	private Date startTime;
	private Date endTime;
	private double distance;
	private long activityId;
	private String activityType;
	private long calories;
	private long key;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

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

	public void setKey(long key) {
		this.key = key;
	}

}
