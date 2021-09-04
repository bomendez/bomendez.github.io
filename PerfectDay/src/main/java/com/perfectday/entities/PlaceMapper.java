package com.perfectday.entities;

import org.springframework.stereotype.Component;

/**
 * This class represents a JPA Entity to create and map attributes associated
 * with location in the place table in the database.
 */

@Component
public class PlaceMapper {

	private long id;

	private String name;

	private Location location;

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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
