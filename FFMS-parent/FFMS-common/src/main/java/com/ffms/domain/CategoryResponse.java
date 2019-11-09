package com.ffms.domain;/*
  @auther WJW129
  @date 2019/11/9 - 22:26
*/

import lombok.Data;

import java.util.Date;

@Data
public class CategoryResponse {
    private int id;
    private String name;
    private Date creatTime;
}
