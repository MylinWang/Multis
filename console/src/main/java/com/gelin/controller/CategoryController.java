package com.gelin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
import com.gelin.domain.CategoryVo;
import com.gelin.domain.ResultVo;
import com.gelin.entity.Category;
import com.gelin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

/**
* <p>
    * 类别表 前端控制器
    * </p>
*
* @author LIH
* @since 2025-02-12
*/
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/category/detail")
    public CategoryVo detail(@RequestParam("id") BigInteger id){
        Category category  = categoryService.getById(id);
        CategoryVo categoryVo=new CategoryVo();
        categoryVo.setId(category.getId());
        categoryVo.setName(category.getName());
        categoryVo.setImage(category.getImage());
    return categoryVo;
    }

    @RequestMapping("/category/insert")
    public ResultVo insert(@RequestParam("name") String name,
    @RequestParam("image") String image) {
        ResultVo resultVo = new ResultVo();
        name = name.trim();
        if (name.equals("")) {
            resultVo.setMessage("name不能为空");
        return resultVo;
        }
        int timestamp = (int) (System.currentTimeMillis() / 1000);
            Category category=new Category();
            category.setName(name);
            category.setImage(image);
            category.setCreateTime(timestamp);
            category.setUpdateTime(timestamp);
        try {
            Integer insert = categoryService.insert(category);
            String s = insert > 0 ? "新增成功" : "新增失败";
            resultVo.setMessage(s);
        } catch (Exception e) {
            resultVo.setMessage(e.getMessage());
        }
    return resultVo;
    }

    @RequestMapping("/category/update")
    public ResultVo update(@RequestParam("id") Long id,
    @RequestParam("name") String name,
    @RequestParam("image") String image) {
        ResultVo resultVo = new ResultVo();
        name = name.trim();
    if (name.equals("")) {
        resultVo.setMessage("name不能为空");
    return resultVo;
    }
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Category category=new Category();
        category.setId(id);
        category.setName(name);
        category.setImage(image);
        category.setUpdateTime(timestamp);
    try{
        Integer update = categoryService.update(category);
        String s = update>0 ? "新增成功" : "新增失败";
        resultVo.setMessage(s);
    }catch (Exception e){
        resultVo.setMessage(e.getMessage());
    }
        return resultVo;
    }

    @RequestMapping("/category/delete")
    public ResultVo delete(@RequestParam("id") BigInteger id){
        ResultVo resultVo = new ResultVo();
        int delete = categoryService.delete(id);
        String s = delete>0 ? "新增成功" : "新增失败";
        resultVo.setMessage(s);
        return resultVo;
    }
}

