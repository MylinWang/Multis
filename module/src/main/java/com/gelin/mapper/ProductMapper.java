package com.gelin.mapper;


import com.gelin.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ProductMapper {
    // @Select("SELECT a.*,c.name as categoryName,c.image as categoryImage FROM product a join category c on a.category_id=c.id where  a.is_deleted = 0 and a.id=#{id}")
    Product getById(@Param("id") BigInteger id);

    @Select("select id,name,images,price,origin,width,description,create_time,update_time from product WHERE id=#{id}")
    Product extractById(@Param("id") BigInteger id);

    int insert(@Param("product")Product product);

    int update(@Param("product")Product product);

    @Update("update product set is_deleted=1,update_time=#{time} where id=#{id} limit 1")
    int delete(@Param("id") BigInteger id,@Param("time") Integer time);

  /*  @Select("select * from product where is_deleted = 0 and name like '%${name}%' limit #{offset},#{pageSize}")
    List<Product> getAll(@Param("pageSize")Integer pageSize,@Param("offset")Integer offset,@Param("name") String name);*/

    List<Product> getAll(@Param("pageSize")Integer pageSize,@Param("offset")Integer offset,@Param("name") String name);

    @Select("select count(*) as total from product where is_deleted = 0")
    int getTotal();


    @Select("update product set is_deleted=1 where category_id=#{categoryId}")
    int productId(@Param("categoryId") BigInteger categoryId);

}
