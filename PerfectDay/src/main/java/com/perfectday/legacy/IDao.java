package com.perfectday.legacy;

import java.sql.ResultSet;

import org.springframework.stereotype.Component;

@Component
public interface IDao {

  boolean executeStatementAndSetKey(String sql, IDto dto);
  
  ResultSet executeStatementAndReturnResult(String sql);

  boolean executeStatement(String sql);
}
