package com.perfectday.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the distance table in the database.
 */

@Entity
public class Distance {

	@Id
	private long activity_id;

	private double distance_count;

	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}

	public double getDistance_count() {
		return distance_count;
	}

	public void setDistance_count(double distance_count) {
		this.distance_count = distance_count;
	}
}
