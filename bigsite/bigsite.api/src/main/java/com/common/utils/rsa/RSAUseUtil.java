package com.common.utils.rsa;

import java.io.UnsupportedEncodingException;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Hex;

/**
 * RSAUseUtil
 *
 * @author zhaozele
 *
 */
public abstract class RSAUseUtil {

  /**
   * 公钥
   */
  private static final PublicKey PUB_KEY;
  static {
    PUB_KEY = RSAKeyHelper
        .decodePublicKeyFromXml("<RSAKeyValue><Modulus>ANucjBlrN35Z2qsrwsYAr+k=</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>");
  }

  /**
   * encrypt
   *
   * @param text
   *        text
   * @return encText
   *
   * @throws UnsupportedEncodingException
   *         UnsupportedEncodingException
   */
  public static String encrypt(String text) throws UnsupportedEncodingException {

    byte[] dataByteArray = text.getBytes("utf-8");
    byte[] cipherDataByteArray = RSACryptUtil.encryptData(dataByteArray, PUB_KEY);
    return Hex.encodeHexString(cipherDataByteArray).toUpperCase();
  }

}
