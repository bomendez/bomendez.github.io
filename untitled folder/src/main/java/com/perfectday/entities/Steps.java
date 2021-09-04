package com.perfectday.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the steps table in the database.
 */

@Entity
public class Steps {

	@Id
	private long activity_id;

	private long step_count;

	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}

	public long getStep_count() {
		return step_count;
	}

	public void setStep_count(long step_count) {
		this.step_count = step_count;
	}
}
