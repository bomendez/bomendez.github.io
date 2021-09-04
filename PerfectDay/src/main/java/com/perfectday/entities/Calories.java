package com.perfectday.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the calories table in the database.
 */

@Entity
public class Calories {

	@Id
	private long activity_id;

	private long calorie_count;

	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}

	public long getCalorie_count() {
		return calorie_count;
	}

	public void setCalorie_count(long calorie_count) {
		this.calorie_count = calorie_count;
	}
}
