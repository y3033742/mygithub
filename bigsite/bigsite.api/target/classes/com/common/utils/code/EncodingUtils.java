/**
 * File：EncodingUtils.java
 * Package：com.fang.passport.core.util
 * Author：Administrator
 * Date：2016年12月8日 下午5:22:56
 * Copyright (C) 2003-2016 搜房资讯有限公司-版权所有
 */
package com.common.utils.code;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EncodingUtils
 *
 * @author ldb
 *
 */
public class EncodingUtils {

  /**
   * 判断是否是简体中文城市名
   *
   * @param cityName
   *        cityName
   * @return
   *         return
   */
  public static boolean isChineseCityName(String cityName) {

    Pattern compile = Pattern.compile("^[\u4E00-\u9FA5\uf900-\ufa2d]{1,10}$");

    Matcher matcher = compile.matcher(cityName);

    boolean ret = matcher.matches();

    if (!ret) {
      return false;
    }

    char[] charArray = cityName.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      if (!isSimpleChinese(charArray[i])) {
        return false;
      }
    }

    return true;

  }

  /**
   * 判断是否是简体中文
   *
   * @param c
   *        c
   * @return
   *         return
   */
  public static boolean isSimpleChinese(char c) {

    String s = String.valueOf(c);

    byte[] bytes = s.getBytes(Charset.forName("GB2312"));

    if (bytes.length != 2) {
      return false;
    }

    if (bytes[0] >= (0xB0 - 256) && bytes[0] <= (0xF7 - 256) && bytes[1] >= (0xA1 - 256) && bytes[1] <= (0xFE - 256)) {
      return true;
    }
    return false;
  }

}
