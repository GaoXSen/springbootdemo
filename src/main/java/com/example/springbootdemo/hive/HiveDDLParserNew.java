package com.example.springbootdemo.hive;

import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaosen
 * @since 2024/11/18 10:53
 */
public class HiveDDLParserNew {

    public static HiveTable parseDDL(String ddl) throws ParseException {
        // 去除末尾空格和分号
        ddl = HiveDDLParserNew.removeTrailingSemicolon(ddl);

        // 初始化 ParseDriver
        ParseDriver parseDriver = new ParseDriver();

        // 使用 ParseDriver 解析 DDL，生成 ASTNode
        ASTNode rootNode = (ASTNode) parseDriver.parse(ddl).getChild(0);

        // 打印 AST 树的结构
        printAST(rootNode, 0);

        HiveTable hiveTable = new HiveTable();
        List<HiveTable.Column> columns = new ArrayList<>();
        List<HiveTable.Partition> partitions = new ArrayList<>();
        Map<String, String> tblProperties = new HashMap<>();

        for (int i = 0; i < rootNode.getChildCount(); i++) {
            ASTNode child = (ASTNode) rootNode.getChild(i);
            String nodeName = child.getText();

            switch (nodeName.toUpperCase()) {
                case "TOK_TABNAME": // 表名
                    hiveTable.setTableName(child.getChild(0).getText());
                    break;
                case "TOK_TABCOLLIST": // 字段列表
                    for (int j = 0; j < child.getChildCount(); j++) {
                        ASTNode colNode = (ASTNode) child.getChild(j);
                        HiveTable.Column column = new HiveTable.Column();
                        column.setName(colNode.getChild(0).getText());
                        column.setType(colNode.getChild(1).getText().replace("TOK_", ""));
                        if (colNode.getChildCount() > 2) {
                            column.setComment(colNode.getChild(2).getText().replace("'", "").replace("\"", ""));
                        }
                        columns.add(column);
                    }
                    break;
                case "TOK_TABLECOMMENT": // 表注释
                    hiveTable.setComment(child.getChild(0).getText());
                    break;
                case "TOK_TABLEPARTCOLS": // 分区字段
                    for (int j = 0; j < child.getChildCount(); j++) {
                        ASTNode partColNode = (ASTNode) child.getChild(j);
                        HiveTable.Partition partition = new HiveTable.Partition();
                        partition.setName(partColNode.getChild(0).getText());
                        partition.setType(partColNode.getChild(1).getText().replace("TOK_", ""));
                        partitions.add(partition);
                    }
                    break;
                case "TOK_TABLEROWFORMAT": // 行格式化
                    ASTNode rowFormatNode = (ASTNode) child.getChild(0);
                    for (int j = 0; j < rowFormatNode.getChildCount(); j++) {
                        ASTNode serdepropsNode = (ASTNode) rowFormatNode.getChild(j);
                        String serdepropsName = serdepropsNode.getText();
                        switch (serdepropsName.toUpperCase()) {
                            case "TOK_TABLEROWFORMATFIELD": // 字段分隔符
                                hiveTable.setFieldsTerminatedBy(serdepropsNode.getChild(0).getText());
                                break;
                            case "TOK_TABLEROWFORMATLINES": // 行分隔符
                                hiveTable.setLinesTerminatedBy(serdepropsNode.getChild(0).getText());
                                break;
                        }
                    }
                    break;
                case "TOK_FILEFORMAT_GENERIC": // 表注释
                    hiveTable.setStoredAs(child.getChild(0).getText());
                    break;

                case "TOK_TABLEPROPERTIES": // 表属性
                    ASTNode propertitesNode = (ASTNode) child.getChild(0);
                    for (int j = 0; j < propertitesNode.getChildCount(); j++) {
                        ASTNode propertiteNode = (ASTNode) propertitesNode.getChild(j);
                        String key = propertiteNode.getChild(0).getText();
                        String value = propertiteNode.getChild(1).getText();
                        tblProperties.put(key, value);
                    }
                    break;
//                case "TOK_TBLPROPERTIES": // 表属性
//                    for (int j = 0; j < child.getChildCount(); j++) {
//                        ASTNode propertyNode = (ASTNode) child.getChild(j);
//                        String key = propertyNode.getChild(0).getText();
//                        String value = propertyNode.getChild(1).getText();
//                        tblProperties.put(key, value);
//                    }
//                    break;
                // 其他属性根据需要扩展
            }
        }

        hiveTable.setColumns(columns);
        hiveTable.setPartitions(partitions);
        hiveTable.setTblProperties(tblProperties);

        return hiveTable;
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

    /**
     * 去除 SQL 语句末尾的分号
     * @param sql 原始 SQL 语句
     * @return 去除末尾分号的 SQL 语句
     */
    public static String removeTrailingSemicolon(String sql) {
        if (sql == null) {
            return null;
        }
        // 去除尾部多余空白并检查是否以分号结尾
        sql = sql.trim();
        if (sql.endsWith(";")) {
            return sql.substring(0, sql.length() - 1);
        }
        return sql;
    }

}
