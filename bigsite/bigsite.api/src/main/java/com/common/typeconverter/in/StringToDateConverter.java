package com.common.typeconverter.in;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.common.typeconverter.TypeConvertErrorDefaultValue;
import com.common.utils.PropertiesUtils;

/**
 *
 * <p>
 * Description: StringToDateConverter
 * </p>
 *
 * @author jinshilei
 *         2017年2月26日
 * @version 1.0
 *
 */
public class StringToDateConverter implements Converter<String, Date> {

  /**
   * 支持的时间格式字符串
   */
  private static List<SimpleDateFormat> dateStyleList = new ArrayList<SimpleDateFormat>();

  /**
   * 是否支持unix时间戳
   */
  private static Boolean isSupportTimestamp = false;

  /**
   * 静态代码块
   */
  static {
    String isSupportTimestampString = PropertiesUtils.getPropertyConfigValue("isSupportTimestamp");
    if (!StringUtils.isEmpty(isSupportTimestampString)
        && "true".equalsIgnoreCase(isSupportTimestampString)) {
      isSupportTimestamp = true;
    }
    String dateStyleString = PropertiesUtils.getPropertyConfigValue("dateStyle");
    if (!StringUtils.isEmpty(dateStyleString)) {
      String[] dateStyles = dateStyleString.split(",");
      if (dateStyles.length > 0) {
        for (int i = 0; i < dateStyles.length; i++) {
          SimpleDateFormat dateFormat = new SimpleDateFormat(dateStyles[i]);
          dateStyleList.add(dateFormat);
        }
      }
    }
  }

  /**
   * 将字符串时间转换为Date类型
   *
   * @param source
   *        字符串
   *
   * @return 时间
   */
  @Override
  public Date convert(String source) {
    if (!StringUtils.isEmpty(source)) {
      if (dateStyleList.size() > 0) {
        for (SimpleDateFormat simpleDateFormat : dateStyleList) {
          try {
            return simpleDateFormat.parse(source);
          } catch (Exception ex) {
            System.out.println("字符串转换为时间失败：" + source);
          }
        }
      }
      if (isSupportTimestamp) {
        try {
          Long timestamp = Long.parseLong(source);
          return new Date(timestamp);
        } catch (Exception ex) {
          System.out.println("时间戳转换为时间失败：" + source);
        }
      }
      return TypeConvertErrorDefaultValue.DEFAULT_DATE_VALUE;
    }
    return null;
  }
}
