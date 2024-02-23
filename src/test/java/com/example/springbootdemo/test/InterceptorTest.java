package com.example.springbootdemo.test;


import com.example.springbootdemo.dao.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author gaosen
 * @since 2024/2/22 15:25
 */

public class InterceptorTest {

    private SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        // 加载 MyBatis 配置文件
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 创建 SqlSession
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void testGetDataWithPermission() {
        // 模拟普通用户，普通用户只能查询自己的数据
        User user = new User();
        user.setId(2l); // 假设用户ID为1

        // 执行查询
        List<User> userDataList = sqlSession.selectList("getUserData", user);

        // 验证查询结果
        System.out.println("Query result with permission:");
        for (User user1 : userDataList) {
            System.out.println(user1);
        }
    }

    @Test
    public void testGetDataWithoutPermission() {
        // 模拟普通用户，普通用户只能查询自己的数据
        User user = new User();
        user.setId(4l); // 假设用户ID为2，但是该用户没有权限查看其他用户的数据

        // 执行查询
        List<User> userDataList = sqlSession.selectList("getUserData", user);

        // 验证查询结果
        System.out.println("Query result without permission:");
        for (User user2 : userDataList) {
            System.out.println(user2);
        }
    }

    @After
    public void tearDown() throws Exception {
        // 关闭 SqlSession
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
