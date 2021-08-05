package com.perfectday.json;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.sql.SQLException;

public interface IJsonFileParser {

  void parseJsonFile(String fileName) throws IOException, ParseException, SQLException;
}
