package com.gelin.controller;

import com.gelin.domain.ProductVo;
import com.gelin.domain.ProductListCellVo;
import com.gelin.domain.ProductListVo;
import com.gelin.entity.Category;
import com.gelin.entity.Product;
import com.gelin.service.CategoryService;
import com.gelin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/product/detail")
    public ProductVo listDetail(@RequestParam("id") BigInteger id){
        Product product = productService.getById(id);
        Category category = categoryService.getById(product.getCategoryId());
        String images = product.getImages();
        String[] split = images.split("\\$");
        ProductVo productVo=new ProductVo();
        productVo.setImages(split);

        productVo.setId(product.getId());
        productVo.setName(product.getName());
        productVo.setPrice(product.getPrice());
        productVo.setWidth(product.getWidth());
        productVo.setOrigin(product.getOrigin());
        productVo.setDescription(product.getDescription());
        productVo.setCategoryName(category.getName());
        productVo.setCategoryImage(category.getImage());
        return productVo;
    }



    @RequestMapping("/product/list")
    public ProductListVo list(@RequestParam(value = "keyword",required = false)String keyword,
                              @RequestParam("page") Integer page){
        Integer pageSize=10;
        List<Product> productList = productService.getAllProductInfo(pageSize,page,keyword);

        List<ProductListCellVo> productListCellVo=new ArrayList<>();
        ProductListVo productListVo=new ProductListVo();
        for (Product product : productList) {
            String images = product.getImages();
            String[] split = images.split("\\$");
            product.setImages(split[0]);

            ProductListCellVo productVoList=new ProductListCellVo();
            productVoList.setId(product.getId());
            productVoList.setName(product.getName());
            productVoList.setImages(product.getImages());
            productVoList.setPrice(product.getPrice());
            Category category = categoryService.getById(product.getCategoryId());
            productVoList.setCategoryName(category.getName());
            productListCellVo.add(productVoList);
        }
        //总页数/页码=总数/每页显示的记录数
        //int size = (int) Math.ceil((double) number / pageSize);
        //当前页数/码==总页数/码
        //boolean isEnd = page == size ? true : false;
        boolean isEnd = pageSize > productList.size() ? true : false;
        productListVo.setIsEnd(isEnd);
        productListVo.setList(productListCellVo);
        return productListVo;
    }


   /* @RequestMapping("/product/list")
    public ProductListVo list(){
        List<Product> productList = productService.getAllProductInfo();
        List<ProductListCellVo> productListCellVo=new ArrayList<>();
        ProductListVo productListVo=new ProductListVo();
        for (Product product : productList) {
            String images = product.getImages();
            String[] split = images.split("\\$");
            product.setImages(split[0]);

            ProductListCellVo productVoList=new ProductListCellVo();
            productVoList.setId(product.getId());
            productVoList.setName(product.getName());
            productVoList.setImages(product.getImages());
            productVoList.setPrice(product.getPrice());
            productListCellVo.add(productVoList);
        }
        productListVo.setList(productListCellVo);

        return productListVo;
    }*/
}
