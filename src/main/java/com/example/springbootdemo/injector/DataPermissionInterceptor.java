package com.example.springbootdemo.injector;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author gaosen
 * @since 2024/2/22 14:56
 */

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class})
})
public class DataPermissionInterceptor implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        String sql = statement.getBoundSql(parameter).getSql();

        // 根据用户权限动态修改 SQL 查询条件
        String modifiedSql = modifySqlWithPermission(sql);

        // 修改 BoundSql 对象中的 SQL
        ReflectionUtils.setFieldValue(statement.getBoundSql(parameter), "sql", modifiedSql);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, (Interceptor) this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以在这里读取配置文件中的属性
    }

    // 根据用户权限动态修改 SQL 查询条件的逻辑
    private String modifySqlWithPermission(String sql) {
        // 这里可以根据用户的权限动态修改 SQL 查询条件
        // 比如根据用户角色、组织等信息进行数据过滤
        return sql + " WHERE user_id = #{userId}";
    }

}
