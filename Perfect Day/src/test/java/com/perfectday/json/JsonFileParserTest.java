package com.perfectday.json;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;
import com.perfectday.constants.Constants;

@SpringBootTest
public class JsonFileParserTest {
  private Connection m_connection = null;

  private int getActivityCount() {
    try {
      String sql = "SELECT count(*) from activity";
      PreparedStatement statement = m_connection.prepareStatement(sql);
      statement.executeQuery();

      ResultSet resultSet = statement.getResultSet();

      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
      else {
        fail("SQL query failed to get activity count.");
      }
    } 
    catch (SQLException e) {
      e.printStackTrace();
      fail("SQL query failed to select activities.");
    }

    return 0;
  }

  private int clearTestActivities() {
    try {
      String sql = "DELETE FROM activity";
      PreparedStatement statement = m_connection.prepareStatement(sql);
      statement.executeUpdate();
    } 
    catch (SQLException e) {
      e.printStackTrace();
      fail("SQL query failed to remove activities.");
    }

    return 0;
  }

  @Before
  public void createConnection() {
    try {
      m_connection = DriverManager.getConnection(Constants.testUrl, Constants.user, Constants.password);
    } 
    catch (SQLException e) {
      e.printStackTrace();
    }

    clearTestActivities();
  }

  @Test
  public void testParseJsonFile() {
    JsonFileParser jsonFileParser = new JsonFileParser(m_connection);

    try {
      String filePath = "src/test/java/com/resources/test.json";
      File file = new File(filePath);
      System.out.println(file.getAbsolutePath());

      String path = file.getPath();
      jsonFileParser.parseJsonFile(path);
    } catch (IOException | ParseException | SQLException e) {
      e.printStackTrace();
    }

    assertEquals(5, getActivityCount());
  }

}
