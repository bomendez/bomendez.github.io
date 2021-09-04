package com.perfectday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfectday.entities.Steps;
import com.perfectday.repository.IStepsRepository;

/**
 * This class represents the service for Steps table and contains methods that
 * handle the business logic associated with writing data to the table.
 */

@Service
public class StepsService {

	@Autowired
	IStepsRepository stepsRepository;

	public Steps createSteps(Steps s) {
		return stepsRepository.saveAndFlush(s);
	}
}
