package com.example.springbootdemo.mappers;

import com.example.springbootdemo.dao.User;
import jakarta.annotation.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author gaosen
 * @Date 2023/3/17 17:09
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{
}
