package com.perfectday.parsing;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

/**
 * This interface represents the JSON Object Parser and implements methods that
 * extract the specific attributes required in the responses for each activity
 * types and parses the relevant data accordingly.
 */
@Component
public interface IJsonObjectParser {
	Boolean parseWalkingActivity(JSONObject activityObject);

	Boolean parseRunningActivity(JSONObject activityObject);

	Boolean parseCyclingActivity(JSONObject activityObject);

	Boolean parseKayakingActivity(JSONObject activityObject);

	boolean parseSkiingActivity(JSONObject activityObject);

}
