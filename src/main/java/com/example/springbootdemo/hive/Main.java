package com.example.springbootdemo.hive;

/**
 * @author gaosen
 * @since 2024/11/18 14:26
 */
public class Main {
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
                "TBLPROPERTIES ('creator'='admin', 'last_modified'='2024-11-18');";

        // 解析 DDL
        HiveTable hiveTable = HiveDDLParserNew.parseDDL(ddl);
        System.out.println("Parsed HiveTable: " + hiveTable);

        // 生成 DDL
        String generatedDDL = HiveDDLGenerator.generateDDL(hiveTable);
        System.out.println("Generated DDL:\n" + generatedDDL);
    }
}
