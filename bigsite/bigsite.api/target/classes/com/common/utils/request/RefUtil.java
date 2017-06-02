package com.common.utils.request;

/**
 * RefUtil
 *
 * @author Administrator
 * @param <T>
 *        类型
 */
public class RefUtil<T> {

  /**
   * value
   */
  private T value;

  /**
   * value
   *
   * @param value
   *        value
   */
  public RefUtil(T value) {
    this.value = value;
  }

  /**
   * get
   *
   * @return return
   */
  public T get() {
    return value;
  }

  /**
   * set
   *
   * @param anotherValue
   *        anotherValue
   */
  public void set(T anotherValue) {
    value = anotherValue;
  }

  /**
   * toString
   *
   * @return String
   */
  public String toString() {
    return value.toString();
  }

  /**
   * equals
   *@param obj obj
   * @return boolean
   */
  public boolean equals(Object obj) {
    return value.equals(obj);
  }

  /**
   * hashCode
   *
   * @return int
   */
  public int hashCode() {
    return value.hashCode();
  }
}
