package com.perfectday.parsing;

import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

/**
 * This interface represents the JSON File Parser and implements methods that
 * parse json files.
 */

@Component
public interface IJsonFileParser {

	void parseJsonFile(String fileName) throws IOException, ParseException, SQLException;
}
