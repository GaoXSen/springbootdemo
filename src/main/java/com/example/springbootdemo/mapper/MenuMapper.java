package com.example.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.dao.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author gaosen
 * @Date 2023/3/29 15:29
 * @Version 1.0
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}
