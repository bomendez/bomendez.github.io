package com.perfectday.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perfectday.model.IPerfectDayModel;
import com.perfectday.view.IPerfectDayView;

@Component
public class PerfectDayController implements IPerfectDayController {

	/**
	 * This class represents the controller of the Perfect Day application and
	 * interacts with the model to parse the CSV and JSON files and interacts with
	 * the view to set the view for the application.
	 */

	@Autowired
	IPerfectDayModel m_model;
	@Autowired
	IPerfectDayView m_view;

	@Override
	public void parseCsvFile(String filePath) {
		m_model.parseCsvFile(filePath);
	}

	@Override
	public void parseJsonFile(String filePath) {
		m_model.parseJsonFile(filePath);
	}

	@Override
	public void setView(IPerfectDayView view) {

	}
}
