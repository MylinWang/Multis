
package com.gelin.mapper;

import com.gelin.entity.User;
import org.apache.ibatis.annotations.*;
import java.math.BigInteger;
import java.util.List;

@Mapper
public interface UserMapper {

// 根据ID查询操作
@Select("SELECT * FROM user WHERE user_id =  #{userId} AND is_deleted=0")
User getById(BigInteger userId);

// 根据ID提取操作
@Select("SELECT * FROM user WHERE user_id =  #{userId}")
User extractById(BigInteger userId);

// 插入操作
Integer insert(User user);

// 更新操作
Integer update(User user);

// 删除操作
@Update("UPDATE user SET update_time = #{updateTime} , is_deleted = 1 WHERE user_id = #{userId}")
Integer delete(@Param("updateTime") Integer updateTime, @Param("userId")  BigInteger userId);

@Select("select * from user where is_deleted=0")
List<User> getAll();

}
