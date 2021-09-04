package com.perfectday.entities;

import org.springframework.stereotype.Component;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with the location table in the database.
 */

@Component
public class Location {

	private double lat;

	private double lon;

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
