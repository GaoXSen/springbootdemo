package com.example.springbootdemo.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.clickhouse.visitor.ClickhouseOutputVisitor;
import com.alibaba.druid.sql.dialect.db2.visitor.DB2OutputVisitor;
import com.alibaba.druid.sql.dialect.hive.parser.HiveStatementParser;
import com.alibaba.druid.sql.dialect.hive.visitor.HiveOutputVisitor;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.dialect.oracle.visitor.OracleOutputVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author gaosen
 * @since 2023/7/27 17:42
 */
public class MyDemoSqlConvert {

    public static void main(String[] args) {
//        String sql = "INSERT INTO base_category2(id,name) select id,name from base_category1 limit 2,3";
        String sql = "create external table ods_order_info (`id` string COMMENT '订单编号',\n" +
                "    `total_amount` decimal(10,2) COMMENT '订单金额',\n" +
                "    `order_status` string COMMENT '订单状态',\n" +
                "    `user_id` string COMMENT '用户id',\n" +
                "    `payment_way` string COMMENT '支付方式',\n" +
                "    `out_trade_no` string COMMENT '支付流水号',\n" +
                "    `create_time` string COMMENT '创建时间',\n" +
                "    `operate_time` string COMMENT '操作时间'\n" +
                ") COMMENT '订单表'\n" +
                "PARTITIONED BY (`dt` string)\n" +
                "row format delimited fields terminated by '\\t'\n" +
                "location '/wavehouse/gmall/ods/ods_order_info/';";

        //        SQLUtils.parseStatements()
        // 新建 MySQL Parser
//        SQLStatementParser parser = new HiveStatementParser(sql);
        SQLStatementParser parser  = new MySqlStatementParser(sql);

        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLStatement statement = parser.parseStatement();


        StringBuilder out = new StringBuilder();
//        MySqlOutputVisitor visitor = new MySqlOutputVisitor(out);
//        OracleOutputVisitor visitor = new OracleOutputVisitor(out);

        HiveOutputVisitor visitor = new HiveOutputVisitor(out);
//        DB2OutputVisitor visitor = new DB2OutputVisitor(out);
//        ClickhouseOutputVisitor visitor = new ClickhouseOutputVisitor(out);


        statement.accept(visitor);
        System.out.println(out);


//        String element = statement.getChildren().get(1).getAttributes().size();

//        System.out.println(statement.getChildren().get(1));

//        Stream<Object> fields = Arrays.stream(statement.getClass().getFields()).map(field -> field.toString());
//
//        fields.forEach(field -> System.out.println(field));





//        System.out.println(out);



    }

}
