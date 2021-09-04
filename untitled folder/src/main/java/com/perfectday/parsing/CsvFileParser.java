package com.perfectday.parsing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

/**
 * This class represents the CSV File Parser and contains methods that reads in
 * a csv file and calls relevant methods to parse the file.
 */

@Component
public final class CsvFileParser {

	@Autowired
	JsonObjectParser jsonParser;

	private Connection m_connection;

	public CsvFileParser(Connection connection) {
		m_connection = connection;
	}

	public CsvFileParser() {

	}

	public void parseCsvFile(String fileName) throws FileNotFoundException, IOException, ParseException, SQLException {
		CSVReader reader = new CSVReader(new FileReader(fileName));
		String[] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			JSONObject activity = new JSONObject();
			String activityName = nextLine[2];
			activity.put("activity", activityName);
			activity.put("startTime", nextLine[3]);
			activity.put("endTime", nextLine[4]);

			String activityType = (String) (activity.getOrDefault("activity", ""));
			if (activityType.equals("walking")) {
				jsonParser.parseWalkingActivity(activity);

			}
			if (activityType.equals("running")) {
				jsonParser.parseRunningActivity(activity);
			}

			if (activityType.equals("cycling")) {
				jsonParser.parseCyclingActivity(activity);
			}

			if (activityType.equals("kayaking")) {
				jsonParser.parseKayakingActivity(activity);

			}

			if (activityType.equals("cross_country_skiing")) {
				jsonParser.parseSkiingActivity(activity);
			}
		}

		System.out.println("Finished parsing CSV file.");
	}
}
