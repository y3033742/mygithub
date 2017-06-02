package com.common.utils.security;

import java.math.BigInteger;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Pbkdf2Util
 *
 * @author Administrator
 */
public class Pbkdf2Util {

  /**
   * ITERATIONS
   */
  private static final int ITERATIONS = 1000;

  /**
   * LENGTH
   */
  private static final int LENGTH = 128;

  /**
   * ALGORITHM
   */
  private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

  /**
   * 获取密码的md5值为基值的慢哈希值
   *
   * @param passwordMd5
   *        passwordMd5
   * @param salt
   *        salt
   * @return return
   * @throws Exception
   *         Exception
   */
  public static String hashPassword(String passwordMd5, String salt) throws Exception {
    PBEKeySpec pbeKeySpec = new PBEKeySpec(passwordMd5.toUpperCase().toCharArray(), salt
        .toLowerCase().getBytes("UTF-8"), ITERATIONS, LENGTH);
    SecretKeyFactory kFactory = SecretKeyFactory.getInstance(ALGORITHM);
    SecretKey secretKey = kFactory.generateSecret(pbeKeySpec);
    byte[] buff = secretKey.getEncoded();
    String hash = new BigInteger(1, buff).toString(16);
    if (hash.length() < 32) {
      hash = "0" + hash;
    }
    return hash.toUpperCase();
  }
}
