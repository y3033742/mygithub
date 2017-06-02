/**
 * File：RSAKeyHelper.java
 * Package：com.fang.passport.core.rsa
 * Author：Administrator
 * Date：2016年12月21日 下午3:14:50
 * Copyright (C) 2003-2016 搜房资讯有限公司-版权所有
 */
package com.common.utils.rsa;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * RSAKeyHelper
 *
 * @author zhaozele
 *
 */
public class RSAKeyHelper {

  static {
    if (Security.getProvider("BC") == null) {
      Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }
  }

  /**
   * 生成RSA密钥对(默认密钥长度为1024， Provider)
   *
   * @return RSA密钥对
   */
  public static KeyPair generateRSAKeyPair() {
    return generateRSAKeyPair(1024);
  }

  /**
   * 生成RSA密钥对
   *
   * @param keyLength
   *        密钥长度，范围：128～2048
   * @return RSA密钥对
   *
   */
  public static KeyPair generateRSAKeyPair(int keyLength) {
    try {
      KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");
      kpg.initialize(keyLength, new SecureRandom());
      return kpg.genKeyPair();
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * trimLeadingZero
   *
   * @param values
   *        values
   * @return values
   *
   */
  static byte[] trimLeadingZero(byte[] values) {
    if ((0x00 == values[0]) && (values.length > 1)) {
      byte[] r = null;
      r = new byte[values.length - 1];
      System.arraycopy(values, 1, r, 0, r.length);
      return r;
    }
    return values;
  }

  /**
   * encodePublicKeyToXml
   *
   * @param key
   *        key
   * @return return
   *
   */
  public static String encodePublicKeyToXml(PublicKey key) {
    if (!RSAPublicKey.class.isInstance(key)) {
      return null;
    }
    RSAPublicKey pubKey = (RSAPublicKey) key;
    StringBuilder sb = new StringBuilder();
    Base64 base64 = new Base64();

    sb.append("<RSAKeyValue>");
    sb.append("<Modulus>")
        .append(base64.encodeToString(trimLeadingZero(pubKey.getModulus().toByteArray())))
        .append("</Modulus>");
    sb.append("<Exponent>")
        .append(base64.encodeToString(trimLeadingZero(pubKey.getPublicExponent().toByteArray())))
        .append("</Exponent>");
    sb.append("</RSAKeyValue>");
    return sb.toString();
  }

  /**
   * decodePublicKeyFromXml
   *
   * @param xml
   *        xml
   * @return PublicKey
   *
   */
  public static PublicKey decodePublicKeyFromXml(String xml) {
    xml = xml.replaceAll("\r", "").replaceAll("\n", "");
    Base64 base64 = new Base64();
    BigInteger modulus = new BigInteger(1, base64.decode(getStringBetween(xml, "<Modulus>",
        "</Modulus>")));
    BigInteger publicExponent = new BigInteger(1, base64.decode(getStringBetween(xml, "<Exponent>",
        "</Exponent>")));

    RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modulus, publicExponent);

    KeyFactory keyf;
    try {
      keyf = KeyFactory.getInstance("RSA", "BC");
      return keyf.generatePublic(rsaPubKey);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * encodePrivateKeyToXml
   *
   * @param key
   *        key
   * @return return
   *
   */
  public static String encodePrivateKeyToXml(PrivateKey key) {
    if (!RSAPrivateCrtKey.class.isInstance(key)) {
      return null;
    }
    RSAPrivateCrtKey priKey = (RSAPrivateCrtKey) key;
    StringBuilder sb = new StringBuilder();
    Base64 base64 = new Base64();

    sb.append("<RSAKeyValue>");
    sb.append("<Modulus>")
        .append(base64.encodeToString(trimLeadingZero(priKey.getModulus().toByteArray())))
        .append("</Modulus>");
    sb.append("<Exponent>")
        .append(base64.encodeToString(trimLeadingZero(priKey.getPublicExponent().toByteArray())))
        .append("</Exponent>");
    sb.append("<P>")
        .append(base64.encodeToString(trimLeadingZero(priKey.getPrimeP().toByteArray())))
        .append("</P>");
    sb.append("<Q>")
        .append(base64.encodeToString(trimLeadingZero(priKey.getPrimeQ().toByteArray())))
        .append("</Q>");
    sb.append("<DP>")
        .append(base64.encodeToString(trimLeadingZero(priKey.getPrimeExponentP().toByteArray())))
        .append("</DP>");
    sb.append("<DQ>")
        .append(base64.encodeToString(trimLeadingZero(priKey.getPrimeExponentQ().toByteArray())))
        .append("</DQ>");
    sb.append("<InverseQ>")
        .append(base64.encodeToString(trimLeadingZero(priKey.getCrtCoefficient().toByteArray())))
        .append("</InverseQ>");
    sb.append("<D>")
        .append(base64.encodeToString(trimLeadingZero(priKey.getPrivateExponent().toByteArray())))
        .append("</D>");
    sb.append("</RSAKeyValue>");
    return sb.toString();
  }

  /**
   * decodePrivateKeyFromXml
   *
   * @param xml
   *        xml
   * @return
   *         return
   */
  public static PrivateKey decodePrivateKeyFromXml(String xml) {
    xml = xml.replaceAll("\r", "").replaceAll("\n", "");
    Base64 base64 = new Base64();

    BigInteger modulus = new BigInteger(1, base64.decode(getStringBetween(xml, "<Modulus>",
        "</Modulus>")));
    BigInteger publicExponent = new BigInteger(1, base64.decode(getStringBetween(xml, "<Exponent>",
        "</Exponent>")));
    BigInteger privateExponent = new BigInteger(1, base64.decode(getStringBetween(xml, "<D>",
        "</D>")));
    BigInteger primeP = new BigInteger(1, base64.decode(getStringBetween(xml, "<P>", "</P>")));
    BigInteger primeQ = new BigInteger(1, base64.decode(getStringBetween(xml, "<Q>", "</Q>")));
    BigInteger primeExponentP = new BigInteger(1, base64.decode(getStringBetween(xml, "<DP>",
        "</DP>")));
    BigInteger primeExponentQ = new BigInteger(1, base64.decode(getStringBetween(xml, "<DQ>",
        "</DQ>")));
    BigInteger crtCoefficient = new BigInteger(1, base64.decode(getStringBetween(xml, "<InverseQ>",
        "</InverseQ>")));

    RSAPrivateCrtKeySpec rsaPriKey = new RSAPrivateCrtKeySpec(modulus, publicExponent,
        privateExponent, primeP, primeQ, primeExponentP, primeExponentQ, crtCoefficient);

    KeyFactory keyf;
    try {
      keyf = KeyFactory.getInstance("RSA", "BC");
      return keyf.generatePrivate(rsaPriKey);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * getStringBetween
   *
   * @param source
   *        source
   * @param strHead
   *        strHead
   * @param strTail
   *        strTail
   * @return
   *         return
   */
  private static String getStringBetween(String source, String strHead, String strTail) {
    return getStringBetween(source, strHead, strTail, false);
  }

  /**
   * getStringBetween
   *
   * @param source
   *        source
   * @param strHead
   *        strHead
   * @param strTail
   *        strTail
   * @param keepHeadAndTail
   *        keepHeadAndTail
   * @return
   *         return
   */
  private static String getStringBetween(String source, String strHead, String strTail,
                                         boolean keepHeadAndTail) {
    try {
      int indexHead, indexTail;

      if (strHead == null || strHead.isEmpty()) {
        indexHead = 0;
      } else {
        indexHead = source.indexOf(strHead);
      }

      if (strTail == null || strTail.isEmpty()) {
        indexTail = source.length();
      } else {
        indexTail = source.indexOf(strTail, indexHead + strHead.length());
      }
      if (indexTail < 0) {
        indexTail = source.length();
      }

      String rtnStr = "";
      if ((indexHead >= 0) && (indexTail >= 0)) {
        if (keepHeadAndTail) {
          rtnStr = source.substring(indexHead, indexTail + strTail.length());
        } else {
          rtnStr = source.substring(indexHead + strHead.length(), indexTail);
        }
      }
      return rtnStr;
    } catch (Exception ex) {
      return "";
    }
  }
}