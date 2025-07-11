package com.example.springbootdemo.hive;

import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ASTNode;

/**
 * @author gaosen
 * @since 2024/11/18 9:32
 */
public class HiveDDLParser {

    public static void main(String[] args) throws Exception {
        // 示例：复杂的 Hive DDL 语句
        String ddl = "CREATE TABLE IF NOT EXISTS orders (\n" +
                "    order_id INT COMMENT 'Order ID',\n" +
                "    customer_name STRING COMMENT 'Customer Name',\n" +
                "    order_date DATE COMMENT 'Order Date'\n" +
                ") COMMENT 'Orders table'\n" +
                "PARTITIONED BY (region STRING, country STRING) \n" +
                "ROW FORMAT DELIMITED\n" +
                "FIELDS TERMINATED BY ','\n" +
                "LINES TERMINATED BY '\\n'\n" +
                "STORED AS PARQUET \n" +
                "TBLPROPERTIES ('creator'='admin', 'last_modified'='2024-11-18')";

        // 初始化 ParseDriver
        ParseDriver parseDriver = new ParseDriver();

        // 使用 ParseDriver 解析 DDL，生成 ASTNode
        ASTNode astNode = parseDriver.parse(ddl);

        // 打印 AST 树的结构
        printAST(astNode, 0);
    }

    /**
     * 打印 AST 树的递归方法
     *
     * @param node AST 节点
     * @param level 当前递归深度（用于打印缩进）
     */
    public static void printAST(ASTNode node, int level) {
        // 打印当前节点
        System.out.println("  ".repeat(level) + "Node: " + node.getText());

        // 递归处理子节点
        for (int i = 0; i < node.getChildCount(); i++) {
            printAST((ASTNode) node.getChild(i), level + 1);
        }
    }

}
