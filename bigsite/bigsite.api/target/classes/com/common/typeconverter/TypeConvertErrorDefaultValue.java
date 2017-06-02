/**
 * File：DefaultTypeValue.java
 * Package：com.fang.passport.api.typeconverter
 * Author：jin
 * Date：2017年3月17日 下午1:14:15
 * Copyright (C) 2017-2017 房天下-版权所有
 */
package com.common.typeconverter;

import java.util.Date;

/**
 * <p>
 * Description: 类型转换失败后返回的默认值
 * </p>
 *
 * @author jinshilei
 *         2017年3月17日
 * @version 1.0
 *
 */
public class TypeConvertErrorDefaultValue {

  /**
   * 时间类型转换失败后的默认值
   */
  @SuppressWarnings("deprecation")
  public static final Date DEFAULT_DATE_VALUE = new Date(0, 0, 1);

  /**
   * Long类型转换失败后的默认值
   */
  public static final Long DEFAULT_LONG_VALUE = -1L;

  /**
   * Integer类型转换失败后的默认值
   */
  public static final Integer DEFAULT_INTEGER_VALUE = -1;
}
