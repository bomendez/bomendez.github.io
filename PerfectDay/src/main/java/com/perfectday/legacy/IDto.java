package com.perfectday.legacy;

import org.springframework.stereotype.Component;

@Component
public interface IDto {

  int getKey();

  void setKey(int key);
}
