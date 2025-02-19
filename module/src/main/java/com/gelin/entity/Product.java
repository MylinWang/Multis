package com.gelin.entity;





import lombok.Data;

import java.math.BigInteger;

@Data
public class Product {

    private BigInteger id;
    private String name;
    private String images;
    private BigInteger categoryId;
    private Integer price;
    private String origin;
    private Integer width;
    private String description;

    private Integer createTime;
    private Integer updateTime;


}
