package com.example.springbootdemo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gaosen
 * @since 2023/5/24 11:25
 */
public class Test {

    public static void main(String[] args) {
        Menu menu1 = new Menu(2L, "北京", 1L, null);
        Menu menu2 = new Menu(3L, "上海", 1L, null);

        Menu menu3 = new Menu(4L, "东京", 2L, null);
        Menu menu4 = new Menu(5L, "大阪", 2L, null);

        Menu menu5 = new Menu(6L, "华盛顿", 3L, null);

        List<Menu> menus = new ArrayList<>();

        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);


        // 从 0 开始构建整个树
        long parentId = 1L;

        System.out.println(JSON.toJSONString(builderMenuTree(menus, parentId)));
    }
    private static List<Menu> builderMenuTree(List<Menu> menus, long parentId) {
        return menus.stream()
                .filter(menu -> menu.getParentId() != null && menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
    }

    private static List<Menu> getChildren(Menu menu, List<Menu> menus) {
        return menus.stream()
                .filter(m -> menu.getParentId() != null && m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());

    }
}
