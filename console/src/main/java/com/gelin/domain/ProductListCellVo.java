package com.gelin.domain;

import com.gelin.entity.Category;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

@Data
public class ProductListCellVo {
    private BigInteger id;
    private String name;
    private String images;
    private Integer price;
    private String createTime;
    private String updateTime;
}
