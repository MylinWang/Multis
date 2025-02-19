
package com.gelin.mapper;

import com.gelin.entity.Category;
import org.apache.ibatis.annotations.*;
import java.math.BigInteger;
import java.util.List;

@Mapper
public interface CategoryMapper {

// 根据ID查询操作
@Select("SELECT * FROM category WHERE id =  #{categoryId} AND is_deleted=0")
Category getById(BigInteger categoryId);

// 根据ID提取操作
@Select("SELECT * FROM category WHERE id =  #{categoryId}")
Category extractById(BigInteger categoryId);

// 插入操作
Integer insert(Category category);

// 更新操作
Integer update(Category category);

// 删除操作
@Update("UPDATE category SET update_time = #{updateTime} , is_deleted = 1 WHERE id = #{categoryId}")
Integer delete(@Param("updateTime") Integer updateTime, @Param("categoryId")  BigInteger categoryId);

@Select("select * from category where is_deleted=0")
List<Category> getListCategory();

}
