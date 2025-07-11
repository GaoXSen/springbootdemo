package com.example.springbootdemo.hive;

import java.util.Map;

/**
 * @author gaosen
 * @since 2024/11/18 10:54
 */
public class HiveDDLGenerator {

    public static String generateDDL(HiveTable hiveTable) {
        StringBuilder ddl = new StringBuilder();

        // 基本表定义
        ddl.append("CREATE TABLE IF NOT EXISTS ").append(hiveTable.getTableName()).append(" (\n");
        for (HiveTable.Column column : hiveTable.getColumns()) {
            ddl.append("    ").append(column.getName())
                    .append(" ").append(column.getType());
            if (column.getComment() != null) {
                ddl.append(" COMMENT '").append(column.getComment()).append("',\n");
            }
        }
        ddl.deleteCharAt(ddl.length() - 2).append("\n)");
        ddl.append(" COMMENT ").append(hiveTable.getComment()).append("\n");

        // 分区字段
        if (!hiveTable.getPartitions().isEmpty()) {
            ddl.append("PARTITIONED BY (\n");
            for (HiveTable.Partition partition : hiveTable.getPartitions()) {
                ddl.append("    ").append(partition.getName())
                        .append(" ").append(partition.getType());
                ddl.append(",\n");
            }
            ddl.deleteCharAt(ddl.length() - 2).append(")\n");
        }

        // 存储格式
        ddl.append("ROW FORMAT DELIMITED").append("\n");
        if (hiveTable.getFieldsTerminatedBy() != null) {
            ddl.append("FIELDS TERMINATED BY ").append(hiveTable.getFieldsTerminatedBy()).append("\n");
        }
        if (hiveTable.getLinesTerminatedBy() != null) {
            ddl.append("LINES TERMINATED BY ").append(hiveTable.getLinesTerminatedBy()).append("\n");
        }
        // 存储类型
        if (hiveTable.getLinesTerminatedBy() != null) {
            ddl.append("STORED AS ").append(hiveTable.getStoredAs()).append("\n");
        }else {
            ddl.append("STORED AS PARQUET").append("\n");
        }

        // 表属性
        if (!hiveTable.getTblProperties().isEmpty()) {
            ddl.append("TBLPROPERTIES (\n");
            for (Map.Entry<String, String> entry : hiveTable.getTblProperties().entrySet()) {
                ddl.append("    ").append(entry.getKey())
                        .append("=").append(entry.getValue()).append(",\n");
            }
            ddl.deleteCharAt(ddl.length() - 2).append(");\n");
        }

        return ddl.toString();
    }

}
