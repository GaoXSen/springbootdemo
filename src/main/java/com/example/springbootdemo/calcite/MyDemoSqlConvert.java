package com.example.springbootdemo.calcite;

import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.HiveSqlDialect;
import org.apache.calcite.sql.dialect.MysqlSqlDialect;
import org.apache.calcite.sql.dialect.SparkSqlDialect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

/**
 * @author gaosen
 * @since 2023/7/27 16:44
 */
public class MyDemoSqlConvert {

    public static void main(String[] args) throws SqlParseException {

        String sql = "INSERT INTO base_category2(id,name) select id,name from base_category1";

        //构建config，有默认的构造也有带参数的config   返回ConfigImpl   有默认的实现
        SqlParser.Config config = SqlParser.configBuilder().build();

        // 根据sql和SqlParser.Config构造一个SqlParser，这里的Config可以配置一些引用标识符、大小写保留等参数；
        SqlParser sqlParser = SqlParser.create(sql, config);

        // 调用parseStmt方法，就可以得到一个parse tree，这里的sqlNode是树的root节点，一般就是SqlSelect。
        SqlNode sqlNode = sqlParser.parseStmt();

        // 调用toSqlString方法，就可以传入指定的SqlDialect类，实现特定的方言转换。这里我们就传入了PrestoSqlDialect，将SQL转成presto的SQL输出。
        sqlNode.toSqlString(HiveSqlDialect.DEFAULT);
        System.out.println(sqlNode.toSqlString(SparkSqlDialect.DEFAULT));

    }
}
