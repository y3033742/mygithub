package com.common.utils;

/**
 * 处理inmodel
 *
 * @author liyanan
 *
 */
public class UserNameHandler {

  /**
   * 将用户名转换为小写
   *
   * @param name
   *        name
   * @return String
   */
  public static String handle(String name) {
    if (name != null && name != "") {
      return name.toLowerCase();
    }
    return null;
  }
}
