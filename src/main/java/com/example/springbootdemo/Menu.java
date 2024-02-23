package com.example.springbootdemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author gaosen
 * @since 2023/5/26 10:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Menu {

    private Long id;
    //菜单名称
    private String menuName;
    //父菜单ID
    private Long parentId;

    private List<Menu> children;
}
