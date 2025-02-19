package com.gelin.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductVo{
    private BigInteger id;
    private String name;
    private String[] images;
    private Integer price;
    private String origin;
    private Integer width;
    private String description;
    private String createTime;
    private String updateTime;

}
