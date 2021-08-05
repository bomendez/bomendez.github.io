package com.perfectday.json;

import org.json.simple.JSONObject;
import java.sql.Connection;

public interface IJsonConnectionObject {
  
  Connection getConnection();

  JSONObject getJsonObject();

  void setJsonObject(JSONObject jsonObject);

}
