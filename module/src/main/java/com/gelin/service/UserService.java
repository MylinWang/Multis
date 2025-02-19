package com.gelin.service;
import com.gelin.entity.User;
import com.gelin.mapper.UserMapper;
import com.gelin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
* <p>
    *  服务实现类
    * </p>
*
* @author xxxx
* @since 2025-02-10
*/
@Service
    public class UserService {
    @Resource
    private UserMapper mapper;

    public User getById(BigInteger id) {
    return mapper.getById(id);
    }

    public User extractById(BigInteger id) {
    return mapper.extractById(id);
    }

    public List<User> getAll() {
    return mapper.getAll();
    }

    //创建商品类目
    public int insert(User user){

    return mapper.insert(user);
    }

    //更新商品类目
    public int update(User user){

    return mapper.update(user);

    }

    // 删除商品类目
    public int delete(BigInteger id) {
    return mapper.delete( (int) (System.currentTimeMillis() / 1000),id);
    }
    }
