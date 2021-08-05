package com.perfectday.controller;

import com.perfectday.model.IPerfectDayModel;
import com.perfectday.view.IPerfectDayView;

public class PerfectDayController implements IPerfectDayController {
  IPerfectDayModel m_model;
  IPerfectDayView m_view;

  public PerfectDayController(IPerfectDayModel model) {
    m_model = model;
  }

  @Override
  public void parseJsonFile(String jsonFileName) {
    m_model.parseJsonFile(jsonFileName);
  }

  @Override
  public void setView(IPerfectDayView view) {
    m_view = view;
  }

}
