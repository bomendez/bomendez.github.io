package com.perfectday.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the activity_types table in the database.
 */

@Entity
public class ActivityTypes {

	@Id
	private String activity_name;

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
}
