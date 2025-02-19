package com.gelin.domain;

import lombok.Data;

import java.math.BigInteger;


@Data
public class ProductListCellVo {
    private BigInteger id;
    private String name;
    private String images;
    private Integer price;
    private String categoryName;
}
