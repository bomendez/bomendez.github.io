package com.perfectday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectday.entities.Place;
import com.perfectday.repository.IPlaceRepository;

/**
 * This class represents the service for Place table and contains methods that
 * handle the business logic associated with writing data to the table.
 */

@Service
public class PlaceService {

	@Autowired
	IPlaceRepository placeRepository;

	public Place createPlace(Place p) {
		return placeRepository.saveAndFlush(p);
	}
}
