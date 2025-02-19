package com.gelin.service;
import com.gelin.entity.Category;
import com.gelin.entity.Product;
import com.gelin.mapper.CategoryMapper;
import com.gelin.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
* <p>
    * 类别表 服务实现类
    * </p>
*
* @author xxxx
* @since 2025-02-10
*/
@Service
public class CategoryService {
   /* @Autowired
    private ProductService productService;*/
    @Resource
    private CategoryMapper mapper;

    public Category getById(BigInteger id) {
    return mapper.getById(id);
    }

    public Category extractById(BigInteger id) {
    return mapper.extractById(id);
    }

    public List<Category> getListCategory() {
    return mapper.getListCategory();
    }

    //创建商品类目
    public int insert(Category category){

    return mapper.insert(category);
    }

    //更新商品类目
    public int update(Category category){

    return mapper.update(category);

    }

    // 删除商品类目
    public int delete(BigInteger id) {

        return mapper.delete( (int) (System.currentTimeMillis() / 1000),id);
    }

    public int deleteProductAndCategory(BigInteger id){
        int delete = delete(id);
        return delete;
    }



}
