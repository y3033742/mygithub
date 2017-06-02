package com.common.utils.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5Utils
 *
 * @author Administrator
 */
public class Md5Utils {

  /**
   * 哈希值
   *
   * @param input
   *        input
   * @return return
   * @throws NoSuchAlgorithmException
   *         NoSuchAlgorithmException
   */
  public static String hashPassword(String input) throws NoSuchAlgorithmException {
    MessageDigest m = MessageDigest.getInstance("MD5");
    byte[] digest = null;
    try {
      digest = m.digest(input.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    String hash = new BigInteger(1, digest).toString(16);

    while (hash.length() < 32) {
      hash = "0" + hash;
    }
    return hash.toUpperCase();
  }
}
