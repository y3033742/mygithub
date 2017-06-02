package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/host")
public class Testcontroller {

  public static JsonGenerator jsonGenerator = null;

  private static ObjectMapper mapper = new ObjectMapper();

  @RequestMapping("/getid")
  @ResponseBody
  public String getid() {
    return "";
  }
}
