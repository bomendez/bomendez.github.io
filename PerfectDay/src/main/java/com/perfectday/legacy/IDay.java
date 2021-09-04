package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public interface IDay {

  boolean getRating();

  void setRating(boolean rating);

  String getDate();

  void setDate(String date);
}
