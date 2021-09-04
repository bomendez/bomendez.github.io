package com.perfectday.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the dates table in the database.
 */

@Entity
public class Dates {

	@Id
	private long activity_id;

	private Date start_time;

	private Date end_time;

	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

}
