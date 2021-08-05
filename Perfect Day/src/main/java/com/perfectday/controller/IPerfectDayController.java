package com.perfectday.controller;

import com.perfectday.view.IPerfectDayView;

public interface IPerfectDayController {

  /**
   * Begins import of a new JSON file into the application.
   */
  void parseJsonFile(String jsonFileName);

  /**
   * Set the view.
   * @param view view of the application.
   */
  void setView(IPerfectDayView view);
}