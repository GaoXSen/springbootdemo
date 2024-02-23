package com.example.springbootdemo.druid;

import java.util.List;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;



public  class Test {
    public static void main(String[] args) {
        String sql = "BEGIN    if c = d then        set a = 10;    END IF;END;";

        StringBuilder out = new StringBuilder();
//        MySqlOutputVisitor visitor = new MySqlOutputVisitor(out);
        MySql2PgplSqlOutputVisitor visitor = new MySql2PgplSqlOutputVisitor(out);
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> statementList = parser.parseStatementList();

        for (SQLStatement statement : statementList) {
            statement.accept(visitor);
            // 这里的statement就是一颗AST
            visitor.println();
        }


        System.out.println(visitor.getDbType());
        System.out.println(out.toString());
    }
}
