package com.perfectday.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class JsonFileParser implements IJsonFileParser  {
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

    JSONArray days = (JSONArray)fileObject;

    for (Object dayObject : days) {

      if (dayObject instanceof JSONObject) {
        JSONObject day = (JSONObject)dayObject;
        Object segmentsObject = day.get("segments");

        if (segmentsObject instanceof JSONArray) {
          JSONArray segments = (JSONArray)segmentsObject;

          for (Object segmentObject : segments) {

            if (segmentObject instanceof JSONObject) {
              JSONObject segment = (JSONObject)segmentObject;
              Object activitiesObject = segment.get("activities");

              if (activitiesObject instanceof JSONArray) {
                JSONArray activities = (JSONArray)activitiesObject;

                for (Object activityObject : activities) {

                  if (activityObject instanceof JSONObject) {
                    JSONObject activity = (JSONObject)activityObject;
                    String activityName = activity.getOrDefault("activity", "").toString();

                    Map<String, Function<JsonConnectionObject, Boolean>> m_functions = new HashMap<>();
                    m_functions.put("walking", JsonObjectParser::parseWalkingActivity);
                    m_functions.put("running", JsonObjectParser::parseRunningActivity);
                    m_functions.put("cycling", JsonObjectParser::parseCyclingActivity);
                    m_functions.put("kayaking", JsonObjectParser::parseKayakingActivity);
                    m_functions.put("cross_country_skiing", JsonObjectParser::parseSkiingActivity);

                    if (m_functions.containsKey(activityName)) {
                      JsonConnectionObject activityConnectionObject = new JsonConnectionObject(activity, m_connection);
                      m_functions.get(activityName).apply(activityConnectionObject);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    System.out.println("Finished parsing JSON file.");
  }
}
