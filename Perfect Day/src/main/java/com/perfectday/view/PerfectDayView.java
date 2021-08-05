package com.perfectday.view;

import com.perfectday.controller.IPerfectDayController;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.*;

/**
 * Implementation of the view.
 */
public class PerfectDayView extends JFrame implements IPerfectDayView {
  private static final long serialVersionUID = 1L;
  IPerfectDayController m_controller;

  public PerfectDayView(String caption, IPerfectDayController controller) {
    super(caption);

    m_controller = controller;
    m_controller.setView(this);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton parseFileButton = new JButton("Parse File");

    parseFileButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        selectJsonFileToParse();
      }
    });

    add(parseFileButton);

    pack();
    setVisible(true);
  }

  protected void selectJsonFileToParse() {
    FileDialog fileDialog = new FileDialog((Frame)null, "Select JSON file to parse");
    fileDialog.setMode(FileDialog.LOAD);
    fileDialog.setVisible(true);
    fileDialog.setFilenameFilter(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        return name.endsWith(".json");
      }
    });
    
    String directory = fileDialog.getDirectory();
    String file = fileDialog.getFile();
    
    if (directory == null || file == null) {
      return;
    }
    
    String jsonFile = directory + file;
    m_controller.parseJsonFile(jsonFile);
  }
}
