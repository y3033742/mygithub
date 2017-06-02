package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.util.StringUtils;






/**
 * 这是cookie操作的工具类
 *
 * @author jinshielei
 * @version V1.0 2016-7-25
 */
public class CookieHelper {

/*  *//**
   * 将构造函数私有，禁止该类的实例化
   *//*
  private CookieHelper() {
  }
  *//**
   * 对称加密算法的密钥
   *//*
  private static final String DESKEY = "f6t3y9p3";

  *//**
   * 对对称加密串进行解析的方法
   *
   * @param enryptString
   *        要解密的字符串
   * @return 解密后的数据对象
   * @throws UnsupportedEncodingException
   *         异常
   *//*
  public static UserInfoCookie analyzeCookie(String enryptString) throws UnsupportedEncodingException {
    String result = DesUtils.decrypt(enryptString, getDesKey());
    if (!StringUtils.isEmpty(result)) {
      UserInfoCookie userInfo = new UserInfoCookie();
      String[] str = result.split("\\^");
      userInfo.setUserId(StringUtils.isEmpty(str[1]) ? "" : str[1]);
      String userName = URLDecoder.decode(str[2], "UTF-8");
      userInfo.setUserName(StringUtils.isEmpty(userName) ? "" : userName);
      userInfo.setUserType(StringUtils.isEmpty(str[3]) ? "" : str[3]);
      userInfo.setIsValid(StringUtils.isEmpty(str[4]) ? "" : str[4]);
      userInfo.setGenerateTime(StringUtils.isEmpty(str[5]) ? "" : str[5]);
      userInfo.setIpHashcode(StringUtils.isEmpty(str[6]) ? "" : str[6]);
      userInfo.setOsId(StringUtils.isEmpty(str[7]) ? "" : str[7]);
      if (str.length > 8) {
        userInfo.setBrowserId(StringUtils.isEmpty(str[8]) ? "" : str[8]);
      }
      if (str.length > 9) {
        userInfo.setUserAgent(StringUtils.isEmpty(str[9]) ? "" : str[9]);
      }
      if (str.length > 10) {
        userInfo.setService(StringUtils.isEmpty(str[10]) ? "" : str[10]);
      }
      if (str.length > 11) {
        userInfo.setRole(StringUtils.isEmpty(str[11]) ? "" : str[11]);
      }
      return userInfo;
    }
    return null;
  }
  *//**
   * 获取对称加密算法密钥
   *
   * @return 返回加密算法密钥
   *//*
  public static String getDesKey() {
    return DESKEY;
  }*/

}
