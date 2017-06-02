package com.common.jacksonconverter;

import java.io.IOException;

import com.common.utils.rsa.RSAUseUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * rsa手机号加密序列化
 *
 * @author zhaozele
 */
public class RSAMobilePhoneSerializer extends JsonSerializer<String> {

  /**
   * serialize自定义
   */
  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException, JsonProcessingException {
    if (value != null && !value.equals("")) {
      gen.writeString(RSAUseUtil.encrypt(value.toString()));
    } else {
      gen.writeString(value);
    }
  }

}
