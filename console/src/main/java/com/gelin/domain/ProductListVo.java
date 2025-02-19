package com.gelin.domain;

import lombok.Data;

import java.util.List;

@Data
public class ProductListVo {
    private List<ProductListCellVo> list;

    private Integer pageSize;

    private Integer total;

}
