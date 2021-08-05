package com.perfectday.db;

import java.sql.ResultSet;

public interface IDao {

  boolean executeStatementAndSetKey(String sql, IDto dto);
  
  ResultSet executeStatementAndReturnResult(String sql);

  boolean executeStatement(String sql);
}
