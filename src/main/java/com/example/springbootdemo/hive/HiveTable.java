package com.example.springbootdemo.hive;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author gaosen
 * @since 2024/11/18 10:52
 */
public class HiveTable {
    private String tableName;
    private List<Column> columns;
    private List<Partition> partitions;
    private String rowFormat;
    private String fieldsTerminatedBy;
    private String linesTerminatedBy;
    private String storedAs;
    private Map<String, String> tblProperties;
    private String comment;

    // Getters, setters, toString
    public static class Column {
        private String name;
        private String type;
        private String comment;

        // Getters and Setters for Column
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    public static class Partition {
        private String name;
        private String type;

        // Getters and Setters for Partition
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    // Getters and Setters for HiveTable
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<Partition> partitions) {
        this.partitions = partitions;
    }

    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    public String getFieldsTerminatedBy() {
        return fieldsTerminatedBy;
    }

    public void setFieldsTerminatedBy(String fieldsTerminatedBy) {
        this.fieldsTerminatedBy = fieldsTerminatedBy;
    }

    public String getLinesTerminatedBy() {
        return linesTerminatedBy;
    }

    public void setLinesTerminatedBy(String linesTerminatedBy) {
        this.linesTerminatedBy = linesTerminatedBy;
    }

    public String getStoredAs() {
        return storedAs;
    }

    public void setStoredAs(String storedAs) {
        this.storedAs = storedAs;
    }

    public Map<String, String> getTblProperties() {
        return tblProperties;
    }

    public void setTblProperties(Map<String, String> tblProperties) {
        this.tblProperties = tblProperties;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
