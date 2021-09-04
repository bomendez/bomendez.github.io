package com.perfectday.model;

import org.springframework.stereotype.Component;

/**
 * This interface represents the model of the Perfect Day application and
 * implements methods that parses the csv and json files.
 */

@Component
public interface IPerfectDayModel {

	void parseJsonFile(String filePath);

	void parseCsvFile(String filePath);
}
