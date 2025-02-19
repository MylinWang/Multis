package com.gelin.service;


import com.gelin.entity.Product;
import com.gelin.mapper.ProductMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductMapper productMapper;

    public Product getById(BigInteger id) {
        return productMapper.getById(id);
    }

    public Product extractById(BigInteger id) {
        return productMapper.extractById(id);
    }

    public Integer insert(Product product) {
        return productMapper.insert(product);
    }

    public Integer update(Product product) {
        return productMapper.update(product);
    }

    public int delete(BigInteger id) {
        return productMapper.delete(id, (int) (System.currentTimeMillis() / 1000));
    }

    public List<Product> getAllProductInfo(Integer pageSize, Integer pageIndex, String name) {
        int offset = pageSize * (pageIndex - 1);
        return productMapper.getAll(pageSize, offset, name);
    }

    public int getTotal() {
        int count = productMapper.getTotal();
        return count;
    }

    public BigInteger edit(BigInteger id, String name, String images,BigInteger categoryId, Integer price, String origin, Integer width, String description) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        if (name == null || images == null || price == null || origin == null || width == null || description == null) {
            throw new RuntimeException("不能为空");
        }
        if (categoryId==null){
            throw new RuntimeException("categoryId不为空");
        }
        if (categoryService.getById(categoryId)==null){
            throw new RuntimeException("category不存在");
        }
        Product product = new Product();
        product.setName(name);
        product.setImages(images);
        product.setCategoryId(categoryId);
        product.setPrice(price);
        product.setOrigin(origin);
        product.setWidth(width);
        product.setDescription(description);
        product.setUpdateTime(timestamp);
        if (id == null) {
            product.setCreateTime(timestamp);
            insert(product);
            return product.getId();
        } else {
            Product old = getById(id);
            if (old==null){
                throw new RuntimeException("id不存在");
            }
            product.setId(id);
            update(product);
            return product.getId();
        }
    }

    public int deleteProduct(BigInteger id){
        int i = productMapper.productId(id);
        return i;
    }


   /* public int insert(String name,String images,Integer price,String origin,Integer width,String description){
        //int timestamp = (int) (System.currentTimeMillis() / 1000);
        int timestamp = (int) (System.currentTimeMillis()/1000);
        Product product=new Product();
        product.setName(name);
        product.setImages(images);
        product.setPrice(price);
        product.setOrigin(origin);
        product.setWidth(width);
        product.setDescription(description);
        product.setCreateTime(timestamp);
        product.setUpdateTime(timestamp);
        return insert(product);
    }*/




    /*public int updateProduct(BigInteger id,String name,String images,Integer price,String origin,Integer width,String description){
        int timestamp = (int) (System.currentTimeMillis()/1000);
        Product product=new Product();
        product.setId(id);
        product.setName(name);
        product.setImages(images);
        product.setPrice(price);
        product.setOrigin(origin);
        product.setWidth(width);
        product.setDescription(description);
        product.setUpdateTime(timestamp);
        return update(product);
    }*/



   /* public int updateProduct(Product product){
        return update(product);
    }*/


}
