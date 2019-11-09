package com.ffms.domain;/*
  @auther WJW129
  @date 2019/11/9 - 22:26
*/

import lombok.Data;

import java.util.Date;

/**
 * 分类占比实体类
 *
 */
@Data
public class CategoryResponse {
    private int id;
    private String name;
    private double allPrice;//分类总金额
    private double account;//占比
}
