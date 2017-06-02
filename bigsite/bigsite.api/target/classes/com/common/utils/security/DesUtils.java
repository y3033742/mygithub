package com.common.utils.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 对称加密算法的工具类
 *
 * @author jinshilei
 * @version V1.0 2016-7-22
 */
public class DesUtils {

  /**
   * 将构造函数私有，禁止该类实例化
   */
  private DesUtils() {

  }

  /**
   * 对称解密方法
   *
   * @param input
   *        要解密的加密串
   * @param key
   *        对称算法密钥
   * @return 解密后的字符串
   */
  public static String decrypt(String input, String key) {
    try {
      byte[] bytesrc = convertHexString(input);
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
      IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
      cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
      byte[] retByte = cipher.doFinal(bytesrc);
      return new String(retByte);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 将十六进制字符串转换为byte数组
   *
   * @param ss
   *        十六进制字符串
   * @return 转换后的字节数组
   */
  private static byte[] convertHexString(String ss) {
    byte[] digest = new byte[ss.length() / 2];
    for (int i = 0; i < digest.length; i++) {
      String byteString = ss.substring(2 * i, 2 * i + 2);
      int byteValue = Integer.parseInt(byteString, 16);
      digest[i] = (byte) byteValue;
    }
    return digest;
  }
}
