package com.common.jacksonconverter;

import java.io.IOException;
import java.text.MessageFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * idNumber自定义序列化
 *
 * @author zhaozele
 */
public class SubIdNumberSerializer extends JsonSerializer<String> {

  /**
   * serialize
   */
  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException, JsonProcessingException {
    if (value != null && !value.equals("")) {
      if (value.length() > 16) {
        gen.writeString(MessageFormat.format("{0}***{1}", value.substring(0, 10),
            value.substring(16, 18)));
      } else {
        gen.writeString(value);
      }
    } else {
      gen.writeString(value);
    }
  }

}
