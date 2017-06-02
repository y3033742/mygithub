package com.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间转换工具类
 *
 * @author zhaozele
 */
public class DateTimeUtils {

  /**
   * yyyy-MM-dd HH:mm:ss 格式
   */
  public static final String DEFAULT_DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

  /**
   * yyyy-MM-dd 格式
   */
  public static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

  /**
   * 将字符串转换为date类型
   *
   * @param dateString
   *        时间字符串
   * @return date
   * @throws Exception
   *         异常
   */
  public static Date stringToDate(String dateString) throws Exception {
    // 返回值
    Date date = null;
    // 时间格式转换类
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    // 转换格式
    List<String> format = new ArrayList<String>();
    format.add("yyyy-M-d");
    format.add("yyyy-M-d HH:mm:ss");
    format.add("yyyy-MM-dd");
    format.add("yyyy-MM-dd HH:mm:ss");
    format.add("yyyy/M/d");
    format.add("yyyy/M/d H:m:ss");

    for (String pattern : format) {
      try {
        simpleDateFormat.applyPattern(pattern);
        date = simpleDateFormat.parse(dateString);
        if (date != null) {
          break;
        }
      } catch (Exception e) {

      }
    }
    if (date == null) {
      throw new Exception("时间转换失败");
    }
    return date;
  }
}
