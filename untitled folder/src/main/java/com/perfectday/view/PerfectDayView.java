package com.perfectday.view;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perfectday.controller.PerfectDayController;

/**
 * This class represents the view of the Perfect Day application and contains
 * methods that generates a pop up whereby the user can upload the file to
 * parse. Depending on the type of file chosen by the user, relevant methods to
 * parse the file are called.
 */

@Component
public class PerfectDayView extends JFrame implements IPerfectDayView {

	@Autowired
	PerfectDayController controller;

	private static final long serialVersionUID = 1L;

	public PerfectDayView() {

	}

	public PerfectDayView(String caption) {
		super(caption);
		start();

	}

	public void start() {
		controller.setView(this);

		setSize(500, 300);
		setLocation(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton parseFileButton = new JButton("Parse File");

		parseFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectJsonFileToParse();
			}
		});

		add(parseFileButton);

		pack();
		setVisible(true);

	}

	protected void selectJsonFileToParse() {
		FileDialog fileDialog = new FileDialog((Frame) null, "Select file to parse");
		fileDialog.setMode(FileDialog.LOAD);
		fileDialog.setVisible(true);
		fileDialog.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".json") || name.endsWith(".geojson") || name.endsWith(".csv");
			}
		});

		String directory = fileDialog.getDirectory();
		String file = fileDialog.getFile();

		if (directory == null || file == null) {
			return;
		}

		String filePath = directory + file;

		if (filePath.endsWith(".csv")) {
			controller.parseCsvFile(filePath);
		} else {
			controller.parseJsonFile(filePath);
		}
	}
}
