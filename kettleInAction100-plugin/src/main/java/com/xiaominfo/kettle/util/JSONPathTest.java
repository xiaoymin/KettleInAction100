/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.xiaominfo.kettle.util;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

/***
 *
 * @since:kettleInAction100-plugin 1.0
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2019/08/12 12:55
 */
public class JSONPathTest {

  public static void main(String[] args) {
    String json="{\n" +
      "  \"name\": \"Rose Kolodny\",\n" +
      "  \"phoneNumbers\": [\n" +
      "    {\n" +
      "      \"type\": \"home\",\n" +
      "      \"number\": \"954-555-1234\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"type\": \"work\",\n" +
      "      \"number\": \"754-555-5678\"\n" +
      "    }\n" +
      "  ]\n" +
      "}";

    System.out.println("JSON:"+json);

    List<String> numbers= JsonPath.read(json,"$..number");
    for (String num:numbers){
      System.out.println(num);
    }
    String name=JsonPath.read(json,"$.name");
    System.out.println("name:"+name);
    once(json);

  }

  static void once(String json){
    System.out.println("初始化一次");
    //初始化创建Document对象
    Object document= Configuration.defaultConfiguration().jsonProvider().parse(json);
    List<String> numbers= JsonPath.read(document,"$..number");
    for (String num:numbers){
      System.out.println(num);
    }
    String name=JsonPath.read(document,"$.name");
    System.out.println("name:"+name);


  }
}
