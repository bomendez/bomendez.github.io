package com.perfectday.parsing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class represents the JSON File Parser and contains methods that reads in
 * a json file and calls relevant methods to parse the file.
 */

@Component
public class JsonFileParser implements IJsonFileParser {

	@Autowired
	JsonObjectParser jsonParser;

	private Connection m_connection;

	public JsonFileParser(Connection connection) {
		m_connection = connection;
	}

	public JsonFileParser() {

	}

	public void parseJsonFile(String fileName) throws FileNotFoundException, IOException, ParseException, SQLException {
		JSONParser jsonParser = new JSONParser();

		FileReader fileReader = new FileReader(fileName);
		Object fileObject = jsonParser.parse(fileReader);

		if (fileObject instanceof JSONArray) {
			JSONArray days = (JSONArray) fileObject;

			for (Object dayObject : days) {

				if (dayObject instanceof JSONObject) {
					JSONObject day = (JSONObject) dayObject;
					Object segmentsObject = day.get("segments");

					if (segmentsObject instanceof JSONArray) {
						JSONArray segments = (JSONArray) segmentsObject;

						for (Object segmentObject : segments) {

							if (segmentObject instanceof JSONObject) {
								parseActivities((JSONObject) segmentObject);
							}
						}
					}
				}
			}
		}

		if (fileObject instanceof JSONObject) {
			JSONObject featureCollection = (JSONObject) fileObject;
			Object featuresObject = featureCollection.get("features");

			if (featuresObject instanceof JSONArray) {
				JSONArray features = (JSONArray) featuresObject;

				for (Object featureObject : features) {

					if (featureObject instanceof JSONObject) {
						JSONObject feature = (JSONObject) featureObject;
						Object propertiesObject = feature.get("properties");

						if (propertiesObject instanceof JSONObject) {
							parseActivities((JSONObject) propertiesObject);
						}
					}
				}
			}
		}

		System.out.println("Finished parsing JSON file.");
	}

	private void parseActivities(JSONObject jsonArray) {
		Object activitiesObject = jsonArray.get("activities");

		if (activitiesObject instanceof JSONArray) {
			JSONArray activities = (JSONArray) activitiesObject;

			for (Object activityObject : activities) {

				if (activityObject instanceof JSONObject) {
					JSONObject activity = (JSONObject) activityObject;

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
			}
		}
	}
}
