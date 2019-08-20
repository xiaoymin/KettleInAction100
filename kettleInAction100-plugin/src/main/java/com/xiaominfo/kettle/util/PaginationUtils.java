/*
 * Copyright (C) 2018 Zhejiang xiaominfo Technology CO.,LTD.
 * All rights reserved.
 * Official Web Site: http://www.xiaominfo.com.
 * Developer Web Site: http://open.xiaominfo.com.
 */

package com.xiaominfo.kettle.util;

import java.math.BigDecimal;

/***
 * 计算分页
 * @since:kettleInAction100-plugin 1.0
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2019/08/10 11:07
 */
public class PaginationUtils {

  /**
   * 计算得到总页码
   * @param totalRecords 总记录数
   * @param pageSize 分页大小
   * @return 总页码
   */
  public static int totalPage(String totalRecords,String pageSize){
    int totalPage=0;
    try{
      BigDecimal records=new BigDecimal(totalRecords);
      BigDecimal size=new BigDecimal(pageSize);
      BigDecimal _tmp=records.add(size).subtract(new BigDecimal(1));
      BigDecimal _tp=_tmp.divide(size).setScale(0,BigDecimal.ROUND_HALF_UP);
      totalPage=_tp.intValue();
    }catch (Exception e){
      //error
      e.printStackTrace();
    }
    return totalPage;
  }


  /**
   * 计算分页offset
   * @param currentPage
   * @param pageSize
   * @return
   */
  public static long offset(String currentPage,String pageSize){
    long offset=0L;
    try{
      //offset从0开始
      offset=new BigDecimal(currentPage).multiply(new BigDecimal(pageSize)).longValue();
    }catch (Exception e){
      e.printStackTrace();
    }
    return offset;
  }

  public static void main(String[] args) {
    System.out.println(totalPage("22433","2000"));
  }

}
