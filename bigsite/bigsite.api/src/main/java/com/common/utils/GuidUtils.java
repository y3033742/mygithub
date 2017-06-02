package com.common.utils;

import java.util.UUID;

/**
 * GuidUtils
 *
 * @author Administrator
 */
public class GuidUtils {

  /**
   * 获取UUid
   *
   * @return newGuid
   */
  public static String newGuid() {
    String guid = UUID.randomUUID().toString();
    return guid.replace("-", "");
  }
}
