package com.test.controller.po;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 738753509400870478L;

  private Long userid;

  private String username;

  private Date datetime;

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Date getDatetime() {
    return datetime;
  }

  public void setDatetime(Date datetime) {
    this.datetime = datetime;
  }
}
