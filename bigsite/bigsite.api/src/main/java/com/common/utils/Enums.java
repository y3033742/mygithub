package com.common.utils;

/**
 * 邮箱信息的枚举
 * @author liyanan
 *
 */
public class Enums {

  /**
   * 邮箱链接类型枚举
   *
   * @author Administrator
   */
  public enum EmailLinkType {
    /**
     * 枚举
     */
    绑定邮箱(1), 换绑邮箱(2), 重置密码(3);

    /**
     * 初始值
     */
    private int value = 1;

    /**
     * 有参构造
     *
     * @param value
     *        值
     */
    EmailLinkType(int value) {
      this.value = value;
    }

    /**
     * getter
     *
     * @return 相对性值
     */
    public int getValue() {
      return this.value;
    }
  }

  /**
   * 修改用户信息枚举
   *
   * @author Administrator
   */
  public enum UserInfoChangeNotify {
    /**
     * 枚举
     */
    换绑(1), 解绑(2), 修改用户信息(3), 注册(4);

    /**
     * 初始值
     */
    private int value = 1;

    /**
     * 有参构造
     *
     * @param value
     *        值
     */
    UserInfoChangeNotify(int value) {
      this.value = value;
    }

    /**
     * getter
     *
     * @return 相对性值
     */
    public int getValue() {
      return this.value;
    }
  }

  /**
   * 邮箱语言枚举
   *
   * @author Administrator
   */
  public enum EmailLanguage {
    /**
     * 枚举
     */
    中文(1), 英文(2);

    /**
     * 初始值
     */
    private int value = 1;

    /**
     * 有参构造
     *
     * @param value
     *        值
     */
    EmailLanguage(int value) {
      this.value = value;
    }

    /**
     * getter
     *
     * @return 相对性值
     */
    public int getValue() {
      return this.value;
    }
  }
}
