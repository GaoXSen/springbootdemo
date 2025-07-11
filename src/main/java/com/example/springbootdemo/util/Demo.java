package com.example.springbootdemo.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaosen
 * @since 2024/9/24 15:09
 */
public class Demo {

    public static void main(String[] args) {

        Map test = new HashMap<String, String>();
        System.out.println(test.get("222"));


        "".equals("");

        " afdfa  afdsfa dsa fas fasdfa ".replace(" ", "");
        System.out.println(" afdfa  afdsfa dsa fas fasdfa ".replace(" ", "").length());

        try {
            System.out.println("你好".getBytes("UTF-8").length);
            System.out.println("你好A".getBytes("UTF-8").length);
            System.out.println("11".getBytes("UTF-8").length);
            System.out.println("不可以".getBytes("UTF-8").length);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}
