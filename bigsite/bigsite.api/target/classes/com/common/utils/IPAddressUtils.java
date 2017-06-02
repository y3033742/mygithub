package com.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * ip获取工具类
 *
 * @author YZT_SouFun
 */
public class IPAddressUtils {

  /**
   * getIpAddr
   *
   * @param request
   *        request
   * @return return
   */
  public static String getIpAddr(HttpServletRequest request) {
    String ipAddress = null;
    ipAddress = request.getHeader("x-forwarded-for");
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
      if (ipAddress.equals("127.0.0.1")) {
        // 根据网卡取本机配置的IP
        InetAddress inet = null;
        try {
          inet = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
          e.printStackTrace();
        }
        ipAddress = inet.getHostAddress();
      }

    }

    // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
    if (ipAddress != null && ipAddress.length() > 15) {
      if (ipAddress.indexOf(",") > 0) {
        ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
      }
    }
    return ipAddress;
  }

  /**
   * ip转换为Long值
   *
   * @param ip
   *        ip
   * @return
   *         long ip
   */
  public static long toNumber(String ip) {
    try {
      String[] ips = ip.split("\\.");
      return Long.parseLong(ips[0]) * 256 * 256 * 256 + Long.parseLong(ips[1]) * 256 * 256
          + Long.parseLong(ips[2]) * 256 + Long.parseLong(ips[3]);
    } catch (Exception e) {
      return -1;
    }
  }

}
