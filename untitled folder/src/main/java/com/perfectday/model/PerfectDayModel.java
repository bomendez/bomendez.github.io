package com.perfectday.model;

import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perfectday.parsing.CsvFileParser;
import com.perfectday.parsing.JsonFileParser;

/**
 * This class represents the model of the Perfect Day application and contains
 * methods that call on the respective methods to parse the CSV and JSON files.
 */
@Component
public class PerfectDayModel implements IPerfectDayModel {

	@Autowired
	JsonFileParser jsonFileParser;
	@Autowired
	CsvFileParser csvFileParser;

	@Override
	public void parseCsvFile(String filePath) {
		try {
			csvFileParser.parseCsvFile(filePath);
		} catch (IOException e) {
			System.out.format("Failed to find file with fileName: %s.", filePath);
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.format("Failed to parse CSV file with fileName: %s.", filePath);
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parseJsonFile(String filePath) {
		try {
			jsonFileParser.parseJsonFile(filePath);
		} catch (IOException e) {
			System.out.format("Failed to find file with fileName: %s.", filePath);
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.format("Failed to parse JSON file with fileName: %s.", filePath);
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
