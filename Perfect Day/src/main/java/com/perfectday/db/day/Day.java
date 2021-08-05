package com.perfectday.db.day;

public class Day implements IDay {
  private String m_date = "";
  private boolean m_rating = false;

  public Day(String date, boolean rating) {
    m_date = date;
    m_rating = rating;
  }


  public Day() {
    super();
    // TODO Auto-generated constructor stub
  }


  @Override
  public boolean getRating() {
    return m_rating;
  }

  @Override
  public void setRating(boolean rating) {
    m_rating = rating;
  }

  @Override
  public String getDate() {
    return m_date;
  }

  @Override
  public void setDate(String date) {
    m_date = date;
  }

}
