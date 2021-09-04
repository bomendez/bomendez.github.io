package com.perfectday.legacy;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public interface IJsonConnectionObject {
  
  Connection getConnection();

  JSONObject getJsonObject();

  void setJsonObject(JSONObject jsonObject);

}
