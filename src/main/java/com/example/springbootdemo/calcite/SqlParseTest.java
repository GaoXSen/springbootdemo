package com.example.springbootdemo.calcite;


// https://blog.csdn.net/skyyws/article/details/124828049


import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlDialect;
import org.apache.calcite.sql.SqlDialect.DatabaseProduct;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.OracleSqlDialect;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.SqlParserImplFactory;
import org.apache.calcite.sql.parser.impl.SqlParserImpl;
import org.apache.calcite.util.SourceStringReader;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.util.function.UnaryOperator;

import static org.apache.calcite.sql.SqlDialect.DatabaseProduct.*;

/**
 * @author gaosen
 * @since 2023/7/25 17:02
 */
public class SqlParseTest {

    @Test
    public void testnormal() throws Exception{
//        String sql = "select a from t1";

        String sql = "select * from emps where id = 1 limit 2,3";



        SqlParser sqlParser = getSqlParser(sql, null);

        SqlNode sqlNode = sqlParser.parseStmt();

        System.out.println(sqlNode.toString());



//        // 解析配置
//        SqlParser.Config mysqlConfig = SqlParser.configBuilder().setLex(Lex.MYSQL).build();
//        // 创建解析器
//        SqlParser parser = SqlParser.create(sql, mysqlConfig);
//        // 解析sql
//        SqlNode sqlNode = parser.parseQuery();
//        // 还原某个方言的SQL
//        System.out.println(sqlNode.toSqlString(OracleSqlDialect.DEFAULT));

    }

    // SQLDialect是一种用于关系型数据库的查询语言
    public SqlParser getSqlParser(String sql, SqlDialect sqlDialect) {
        return getSqlParser(new SourceStringReader(sql), getTransform(sqlDialect));
    }

    protected SqlParser getSqlParser(Reader source,
                                     UnaryOperator<SqlParser.Config> transform) {
        final SqlParser.Config configBuilder =
                SqlParser.config()
                        .withParserFactory(parserImplFactory());
        final SqlParser.Config config = transform.apply(configBuilder);
        return SqlParser.create(source, config);
    }

    private static UnaryOperator<SqlParser.Config> getTransform(SqlDialect dialect) {
        return dialect == null ? UnaryOperator.identity() : dialect::configureParser;
    }

    protected SqlParserImplFactory parserImplFactory() {
        return SqlParserImpl.FACTORY;
    }

}
