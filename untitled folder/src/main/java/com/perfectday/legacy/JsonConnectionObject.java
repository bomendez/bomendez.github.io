package com.perfectday.legacy;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class JsonConnectionObject implements IJsonConnectionObject {
  private Connection m_connection = null;
  private JSONObject m_jsonObject = null;

  public JsonConnectionObject(JSONObject jsonObject, Connection connection) {
    setJsonObject(jsonObject);
    m_connection = connection;
  }

  public JsonConnectionObject(){

  }

  public Connection getConnection() {
    return m_connection;
  }

  public JSONObject getJsonObject() {
    return m_jsonObject;
  }

  public void setJsonObject(JSONObject m_jsonObject) {
    this.m_jsonObject = m_jsonObject;
  }
}
