package com.perfectday.model;

import com.perfectday.json.JsonFileParser;
import com.perfectday.constants.*;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PerfectDayModel implements IPerfectDayModel {

  @Override
  public void parseJsonFile(String fileName) {
    try {
      Connection connection = DriverManager.getConnection(Constants.appUrl, Constants.user, Constants.password);
      JsonFileParser jsonFileParser = new JsonFileParser(connection);
      jsonFileParser.parseJsonFile(fileName);
    }
    catch (IOException e) {
      System.out.format("Failed to find file with fileName: %s.", fileName);
      e.printStackTrace();
    }
    catch (ParseException e) {
      System.out.format("Failed to parse JSON file with fileName: %s.", fileName);
      e.printStackTrace();
    } 
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
