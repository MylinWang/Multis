package com.gelin.controller;


import com.gelin.domain.CategoryListCellVo;
import com.gelin.domain.CategoryListVo;
import com.gelin.entity.Category;
import com.gelin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category/list")
    public CategoryListVo list(){
        List<Category> categoryList = categoryService.getListCategory();
        List<CategoryListCellVo> categoryListCellVo=new ArrayList<>();
        CategoryListVo categoryListVo=new CategoryListVo();
        for (Category category : categoryList) {
            CategoryListCellVo categoryVoList=new CategoryListCellVo();
            categoryVoList.setId(category.getId());
            categoryVoList.setName(category.getName());
            categoryVoList.setImage(category.getImage());
            categoryListCellVo.add(categoryVoList);
        }
        categoryListVo.setList(categoryListCellVo);
        return categoryListVo;
    }



}
