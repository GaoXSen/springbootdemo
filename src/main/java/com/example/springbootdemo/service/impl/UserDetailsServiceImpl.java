package com.example.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springbootdemo.dao.LoginUser;
import com.example.springbootdemo.dao.User;
import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author gaosen
 * @Date 2023/3/28 13:48
 * @Version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException{
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        // 如果查询不到数据就通过抛出异常来给提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名货密码错误");
        }

        // TODO 根据用户查询权限信息添加到LoginUser中

        // 封装成UserDetails对象返回
        return new LoginUser(user);
    }
}
