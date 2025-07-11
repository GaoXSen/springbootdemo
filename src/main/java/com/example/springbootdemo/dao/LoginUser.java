package com.example.springbootdemo.dao;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author gaosen
 * @Date 2023/3/28 14:07
 * @Version 1.0
 */
public class LoginUser implements UserDetails {

    private User user;

    // 存储权限信息
    private List<String> permissions;

    public LoginUser(User user, List<String> permissions){
        this.user = user;
        this.permissions = permissions;
    }

    // 存储SpringSecurity所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        if(authorities != null){
            return authorities;
        }
        // 把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public String getUsername(){
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
