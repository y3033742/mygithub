/**
 * File：MyXmlMapper.java
 * Package：com.fang.passport.api.jacksonconverter
 * Author：jin
 * Date：2017年3月14日 下午4:09:55
 * Copyright (C) 2017-2017 房天下-版权所有
 */
package com.common.jacksonconverter;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * <p>
 * Description: MyXmlMapper
 * </p>
 *
 * @author jinshilei
 *         2017年3月14日
 * @version 1.0
 *
 */
public class MyXmlMapper extends XmlMapper {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -5716263938111506651L;

  /**
   *
   * 该bean初始化后，调用父类方法，让jackson支持jaxb注解
   */
  @PostConstruct
  public void postContructor() {
    this.registerModule(new JaxbAnnotationModule());
  }
}
