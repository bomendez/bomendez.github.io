package com.perfectday.db;

public abstract class Dto implements IDto {
  private int m_key;

  @Override
  public int getKey() {
    return m_key;
  }

  @Override
  public void setKey(int key) {
    m_key = key;
  }

}
