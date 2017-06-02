/**
 * File：PropertyUtils.java
 * Package：com.fang.passport.api.util
 * Author：jin
 * Date：2017年3月10日 下午1:16:01
 * Copyright (C) 2017-2017 房天下-版权所有
 */
package com.common.utils;

import java.util.ResourceBundle;

/**
 * <p>
 * Description: PropertyUtils
 * </p>
 *
 * @author jinshilei
 *         2017年3月10日
 * @version 1.0
 *
 */
public class PropertiesUtils {

  /**
   * 运行状态
   */
  private static String runState;

  /**
   * 常量配置的bundle
   */
  private static ResourceBundle constantsBundle;

  /**
   * 静态代码块
   */
  static {
    ResourceBundle bundleRunState = ResourceBundle.getBundle("config/runState");
    runState = bundleRunState.getString("runState");
    constantsBundle = ResourceBundle.getBundle("config/constants_" + runState);
  }

  /**
   * 获取运行状态
   *
   * @return String 返回类型
   */
  public static String getRunstate() {
    return runState;
  }

  /**
   * 获取constants文件配置的属性值
   *
   * @param key
   *        属性名
   * @return String 返回类型
   */
  public static String getPropertyConfigValue(String key) {
    return constantsBundle.getString(key);
  }
}
