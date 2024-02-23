package com.example.springbootdemo;

/**
 * @author gaosen
 * @since 2023/6/21 16:41
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HdfsConnectDemo {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost");

        try {
            FileSystem fs = FileSystem.get(conf);
            Path path = new Path("/");
            boolean exists = fs.exists(path);

            if (exists) {
                System.out.println("Successfully accessed HDFS!");
            } else {
                System.out.println("Failed to access HDFS.");
            }

            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
