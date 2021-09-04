package com.perfectday.controller;

import org.springframework.stereotype.Component;

import com.perfectday.view.IPerfectDayView;

/**
 * This interface represents the Controller of the Perfect Day application and
 * implements methods that interacts with the model and the view.
 */

@Component
public interface IPerfectDayController {

	/**
	 * Begins import of a new CSV file into the application.
	 */
	void parseCsvFile(String filePath);

	/**
	 * Begins import of a new JSON file into the application.
	 */
	void parseJsonFile(String filePath);

	/**
	 * Set the view.
	 * 
	 * @param view view of the application.
	 */
	void setView(IPerfectDayView view);
}