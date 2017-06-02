package com.common.jacksonconverter;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

/**
 *
 * <p>
 * Description: MyPropertyNamingStrategy
 * </p>
 *
 * @author jinshilei
 *         2017年2月28日
 * @version 1.0
 *
 */
public class MyPropertyNamingStrategy extends PropertyNamingStrategy {

  /**
   *
   */
  private static final long serialVersionUID = 2842711422044328847L;

  @Override
  public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method,
                                    String defaultName) {
    if (defaultName.equals("name")) {
      return super.nameForGetterMethod(config, method, "NAME");
    }
    return super.nameForGetterMethod(config, method, defaultName);
  }

}
