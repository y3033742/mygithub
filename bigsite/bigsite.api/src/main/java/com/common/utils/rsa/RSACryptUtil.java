package com.common.utils.rsa;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;

import javax.crypto.Cipher;

/**
 * RSACryptUtil
 *
 * @author zhaozele
 *
 */
public class RSACryptUtil {

  static {
    if (Security.getProvider("BC") == null) {
      Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }
  }

  /**
   * Transformation
   *
   * @author zhaozele
   *
   */
  public enum Transformation {
    /**
     * RSA
     */
    RSA("RSA"),
    /**
     * RSA_NONE_PKCS1PADDING
     */
    RSA_NONE_PKCS1PADDING("RSA/None/PKCS1Padding"),
    /**
     * RSA_NONE_NOPADDING
     */
    RSA_NONE_NOPADDING("RSA/None/NoPadding");

    /**
     * algorithm
     */
    private String algorithm;

    /**
     * Transformation
     *
     * @param algorithm
     *        algorithm
     */
    Transformation(String algorithm) {
      this.algorithm = algorithm;
    }

    /**
     * getAlgorithm
     *
     * @return algorithm
     *
     */
    public String getAlgorithm() {
      return this.algorithm;
    }

    /**
     * toString
     *
     * @return algorithm
     *
     */
    public String toString() {
      return this.algorithm;
    }
  }

  /**
   * 采用"RSA/None/NoPadding"算法用公钥加密
   *
   * @param data
   *        待加密的明文
   * @param pubKey
   *        公钥
   * @return 解密后的结果，异常的情况下返回null
   */
  public static byte[] encryptData(byte[] data, PublicKey pubKey) {
    return encryptData(data, pubKey, Transformation.RSA_NONE_NOPADDING.getAlgorithm());
  }

  /**
   * 用指定的算法，如"RSA/None/PKCS1Padding"或"RSA/None/NoPadding"用公钥加密
   *
   * @param data
   *        待加密的明文
   * @param pubKey
   *        公钥
   * @param algorithm
   *        algorithm
   * @return 解密后的结果，异常的情况下返回null
   */
  public static byte[] encryptData(byte[] data, PublicKey pubKey, String algorithm) {
    try {
      Cipher cipher = Cipher.getInstance(algorithm, "BC");
      cipher.init(Cipher.ENCRYPT_MODE, pubKey);

      // 获得加密块大小，如:加密前数据为128个byte，而key_size=1024 加密块大小为127 byte,加密后为128个byte;
      // 因此共有2个加密块，第一个127 byte第二个为1个byte
      int blockSize = cipher.getBlockSize();
      int outputSize = cipher.getOutputSize(data.length);
      int leavedSize = data.length % blockSize;
      int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
      byte[] raw = new byte[outputSize * blocksSize];
      int i = 0;
      while (data.length - i * blockSize > 0) {
        if (data.length - i * blockSize > blockSize) {
          cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
        } else {
          cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
        }
        // 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到ByteArrayOutputStream中
        // ，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
        i++;
      }
      return raw;
    } catch (Exception e) {
      System.err.println("Encrypt Error:" + e);
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 采用"RSA/None/NoPadding"算法用私钥解密
   *
   * @param cipherData
   *        待解密的密文
   * @param priKey
   *        私钥
   * @return 解密后的明文，异常的情况下返回null
   */
  public static byte[] decryptData(byte[] cipherData, PrivateKey priKey) {
    return decryptData(cipherData, priKey, Transformation.RSA_NONE_NOPADDING.getAlgorithm());
  }

  /**
   * 用指定的算法，如"RSA/None/PKCS1Padding"或"RSA/None/NoPadding"用私钥解密
   *
   * @param cipherData
   *        待解密的密文
   * @param priKey
   *        私钥
   * @param algorithm
   *        algorithm
   * @return 解密后的明文，异常的情况下返回null
   */
  public static byte[] decryptData(byte[] cipherData, PrivateKey priKey, String algorithm) {
    try {
      Cipher cipher = Cipher.getInstance(algorithm, "BC");
      cipher.init(Cipher.DECRYPT_MODE, priKey);

      int blockSize = cipher.getBlockSize();
      ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
      int j = 0;
      while (cipherData.length - j * blockSize > 0) {
        bout.write(cipher.doFinal(cipherData, j * blockSize, blockSize));
        j++;
      }
      return bout.toByteArray();
    } catch (Exception e) {
      System.err.println("Decrypt Error:" + e);
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 根据指定私钥对数据进行签名(默认签名算法为"SHA1withRSA")
   *
   * @param data
   *        要签名的数据
   * @param priKey
   *        私钥
   * @return 签名，异常的情况下返回null
   */
  public static byte[] signData(byte[] data, PrivateKey priKey) {
    return signData(data, priKey, "SHA1withRSA");
  }

  /**
   * 根据指定私钥和算法对数据进行签名
   *
   * @param data
   *        要签名的数据
   * @param priKey
   *        私钥
   * @param algorithm
   *        签名算法
   * @return 签名，异常的情况下返回null
   */
  public static byte[] signData(byte[] data, PrivateKey priKey, String algorithm) {
    try {
      Signature signature = Signature.getInstance(algorithm, "BC");
      signature.initSign(priKey);
      signature.update(data);
      return signature.sign();
    } catch (Exception ex) {
      System.err.println("Signature Error:" + ex);
      ex.printStackTrace();
      return null;
    }
  }

  /**
   * 用指定的公钥进行签名验证(默认签名算法为"SHA1withRSA")
   *
   * @param data
   *        数据
   * @param sign
   *        签名结果
   * @param pubKey
   *        公钥
   * @return 验签成功返回true，验签失败或异常情况下返回false
   */
  public static boolean verifySign(byte[] data, byte[] sign, PublicKey pubKey) {
    return verifySign(data, sign, pubKey, "SHA1withRSA");
  }

  /**
   * 用指定的公钥和签名算法进行签名验证
   *
   * @param data
   *        数据
   * @param sign
   *        签名结果
   * @param pubKey
   *        公钥
   * @param algorithm
   *        签名算法
   * @return 验签成功返回true，验签失败或异常情况下返回false
   */
  public static boolean verifySign(byte[] data, byte[] sign, PublicKey pubKey, String algorithm) {
    try {
      Signature signature = Signature.getInstance(algorithm, "BC");
      signature.initVerify(pubKey);
      signature.update(data);
      return signature.verify(sign);
    } catch (Exception ex) {
      System.err.println("Verify Signature Error:" + ex);
      ex.printStackTrace();
      return false;
    }
  }
}