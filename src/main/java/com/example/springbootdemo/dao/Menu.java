package com.example.springbootdemo.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 菜单表（Menu）实体类
 *
 * @Author gaosen
 * @Date 2023/3/29 15:22
 * @Version 1.0
 */

@TableName(value="sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {

    @TableId
    private Long id;

    private String menuName;

    private String path;

    private String component;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag;

    private String remark;


}
