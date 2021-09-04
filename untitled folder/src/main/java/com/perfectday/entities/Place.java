package com.perfectday.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the place table in the database.
 */

@Entity
public class Place {

	@Id
	private long id;

	private String name;

	private double lat;

	private double lon;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
}
