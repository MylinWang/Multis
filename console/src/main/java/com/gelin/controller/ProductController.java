package com.gelin.controller;

import com.gelin.domain.*;
import com.gelin.entity.Product;
import com.gelin.service.CategoryService;
import com.gelin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/product/deleteProductAndCategory")
    public int deleteProductAndCategory(@RequestParam("id") BigInteger id){
        int i = categoryService.deleteProductAndCategory(id);
        productService.deleteProduct(id);
        return i;
    }
    @RequestMapping("/product/insert")
    public ResultVo insert(@RequestParam("name") String name,
                           @RequestParam("images") String images,
                           @RequestParam("categoryId") BigInteger categoryId,
                           @RequestParam("price") Integer price,
                           @RequestParam("origin") String origin,
                           @RequestParam("width") Integer width,
                           @RequestParam("description") String description) {
        ResultVo resultVo = new ResultVo();
        name = name.trim();
        if (name.equals("")) {
            resultVo.setMessage("name不能为空");
            return resultVo;
        }
        origin = origin.trim();
        if (origin.equals("")) {
            resultVo.setMessage("origin不能为空");
            return resultVo;
        }
        try {
            BigInteger i = productService.edit(null, name, images,categoryId, price, origin, width, description);
            resultVo.setMessage(i.toString());
        } catch (Exception e) {
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/product/update")
    public ResultVo update(@RequestParam("id") BigInteger id,
                           @RequestParam("name") String name,
                           @RequestParam("images") String images,
                           @RequestParam("categoryId") BigInteger categoryId,
                           @RequestParam("price") Integer price,
                           @RequestParam("origin") String origin,
                           @RequestParam("width") Integer width,
                           @RequestParam("description") String description) {
        ResultVo resultVo = new ResultVo();
        name = name.trim();
        if (name.equals("")) {
            resultVo.setMessage("name不能为空");
            return resultVo;
        }
        origin = origin.trim();
        if (origin.equals("")) {
            resultVo.setMessage("origin不能为空");
            return resultVo;
        }
        try{
            BigInteger i = productService.edit(id, name, images ,categoryId, price, origin, width, description);
            resultVo.setMessage(i.toString());
        }catch (Exception e){
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }


    @RequestMapping("/product/delete")
    public ResultVo studentDelete(@RequestParam(name = "id") BigInteger id) {
        int result = productService.delete(id);
        ResultVo productVo = new ResultVo();
        String s = result > 0 ? "删除成功" : "删除失败";
        productVo.setMessage(s);
        return productVo;
    }

    @RequestMapping("/product/detail")
    public ProductVo listDetail(@RequestParam("id") BigInteger id) {
        Product product = productService.getById(id);
        String images = product.getImages();
        String[] split = images.split("\\$");
        ProductVo productVo = new ProductVo();
        productVo.setImages(split);
        //获取创建时间
        Integer createTime = product.getCreateTime();
        //时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //输出格式
        String createFormat = sdf.format(new Date(createTime * 1000l));
        //获取更新时间
        Integer updateTime = product.getUpdateTime();
        //输出格式
        String updateTimeFormat = sdf.format(new Date(updateTime * 1000l));
        //映射
        productVo.setId(product.getId());
        productVo.setName(product.getName());
        productVo.setPrice(product.getPrice());
        productVo.setWidth(product.getWidth());
        productVo.setOrigin(product.getOrigin());
        productVo.setDescription(product.getDescription());
        productVo.setCreateTime(createFormat);
        productVo.setUpdateTime(updateTimeFormat);
        return productVo;
    }

    @RequestMapping("/product/list")
    public ProductListVo list(@RequestParam("page") Integer page, @RequestParam(value = "keyword", required = false) String keyword) {
        //定义pageSize
        Integer pageSize = 10;
        List<Product> productList = productService.getAllProductInfo(pageSize, page, keyword);
        //总数
        int total = productService.getTotal();
        //实例化ProductListCellVo
        List<ProductListCellVo> productListCellVo = new ArrayList<>();
        //实例化对象
        ProductListVo productListVo = new ProductListVo();
        for (Product product : productList) {
            String images = product.getImages();
            String[] split = images.split("\\$");
            product.setImages(split[0]);

            Integer createTime = product.getCreateTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //输出格式
            String createFormat = sdf.format(new Date(createTime * 1000l));
            //获取更新时间
            Integer updateTime = product.getUpdateTime();
            //输出格式
            String updateTimeFormat = sdf.format(new Date(updateTime * 1000l));

            ProductListCellVo productVoList = new ProductListCellVo();

            productVoList.setId(product.getId());
            productVoList.setName(product.getName());
            productVoList.setImages(product.getImages());
            productVoList.setPrice(product.getPrice());
            productVoList.setCreateTime(createFormat);
            productVoList.setUpdateTime(updateTimeFormat);
            productListCellVo.add(productVoList);
        }
        productListVo.setList(productListCellVo);
        productListVo.setPageSize(pageSize);
        productListVo.setTotal(total);
        return productListVo;
    }
    /*@RequestMapping("/product/list")
    public ProductListVo list(ProductReq productReq){
        List<Product> productList = productService.getAllProductInfo(productReq.getPageSize(),productReq.getPage());
        int total = productService.getNumber();
        List<ProductListCellVo> productListCellVo=new ArrayList<>();
        ProductListVo productListVo=new ProductListVo();
        for (Product product : productList) {
            String images = product.getImages();
            String[] split = images.split("\\$");
            product.setImages(split[0]);

            Integer createTime = product.getCreateTime();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //输出格式
            String createFormat = sdf.format(new Date(createTime*1000l));
            //获取更新时间
            Integer updateTime = product.getUpdateTime();
            //输出格式
            String updateTimeFormat = sdf.format(new Date(updateTime*1000l));

            ProductListCellVo productVoList=new ProductListCellVo();

            productVoList.setId(product.getId());
            productVoList.setName(product.getName());
            productVoList.setImages(product.getImages());
            productVoList.setPrice(product.getPrice());
            productVoList.setCreateTime(createFormat);
            productVoList.setUpdateTime(updateTimeFormat);
            productListCellVo.add(productVoList);
        }
        productListVo.setList(productListCellVo);
        productListVo.setPageSize(productReq.getPageSize());
        productListVo.setTotal(total);
        return productListVo;
    }*/

    //改良接口
    /* @RequestMapping("/product/update")
    public ResultVo update(ProductReq productReq){
        ResultVo resultVo = new ResultVo();
        String names = productReq.getName().trim();
        if (names.equals("")){
            resultVo.setMessage("name不能为空");
            return resultVo;
        }
        String origins = productReq.getOrigin().trim();
        if (origins.equals("")){
            resultVo.setMessage("origin不能为空");
            return resultVo;
        }
        int timestamp = (int) (System.currentTimeMillis()/1000);
        Product product=new Product();
       product.setUpdateTime(timestamp);
        BeanUtils.copyProperties(productReq,product);
        int i = productService.updateProduct(product);
        String s = i > 0 ? "更新成功" : "更新失败";
        resultVo.setMessage(s);
        return resultVo;
    }*/

}
