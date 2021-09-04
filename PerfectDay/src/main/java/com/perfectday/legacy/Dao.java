package com.perfectday.legacy;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public abstract class Dao implements IDao {
  private Connection m_connection;

  public Dao(Connection connection) {
    m_connection = connection;
  }

  public boolean executeStatementAndSetKey(String sql, IDto dto) {

    try {
      PreparedStatement statement = m_connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.executeUpdate();

      ResultSet generatedKeys = statement.getGeneratedKeys();

      if (generatedKeys.next()) {
        int key = generatedKeys.getInt(1);
        dto.setKey(key);
      }

      return true;
    }
    catch (SQLException e) {
      System.out.println("SQL statement execution failed.");
      e.printStackTrace();
    }

    return false;
  }

  public ResultSet executeStatementAndReturnResult(String sql) {
    ResultSet resultSet = null;

    try {
      PreparedStatement statement = m_connection.prepareStatement(sql);
      statement.executeQuery();

      resultSet = statement.getResultSet();
    }
    catch (SQLException e) {
      System.out.println("SQL statement execution failed.");
      e.printStackTrace();
    }

    return resultSet;
  }

  public boolean executeStatement(String sql) {
    try {
      Statement statement = m_connection.createStatement();
      statement.executeUpdate(sql);

      return true;
    }
    catch (SQLException e) {
      System.out.println("SQL statement execution failed.");
      e.printStackTrace();
    }

    return false;
  }
}
