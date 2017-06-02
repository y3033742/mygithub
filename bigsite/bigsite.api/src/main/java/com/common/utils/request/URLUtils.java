package com.common.utils.request;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 访问链接获取工具类
 *
 * @author zhaozele
 */
public class URLUtils {

  /**
   * 获取访问的链接
   *
   * @return url
   *
   * @throws Exception
   *         异常
   */
  public static String getVisitUrl() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes()).getRequest();
    StringBuffer url = request.getRequestURL();
    if (request.getQueryString() != null) {
      url.append("?" + request.getQueryString());
    }
    try {
      return url.toString();
    } catch (Exception e) {
      return "";
    }

  }
}
