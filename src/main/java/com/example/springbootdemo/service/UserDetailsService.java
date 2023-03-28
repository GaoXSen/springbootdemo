package com.example.springbootdemo.service;

import com.example.springbootdemo.dao.LoginUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author gaosen
 * @Date 2023/3/28 13:46
 * @Version 1.0
 */
public interface UserDetailsService {

    LoginUser loadUserByUsername(String username) throws UsernameNotFoundException;

}
