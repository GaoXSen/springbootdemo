package com.example.springbootdemo.util;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gaosen
 * @since 2024/9/23 14:44
 */
public class BitMapUtil {

    public static void main(String[] args) {

        BitSet bitSet1 = new BitSet();
        Set res = new HashSet<>();

        // 使用 IntStream 生成 1 到 100 万的整数列表
        List<Integer> list = IntStream.rangeClosed(1, 100000000)
                .boxed()
                .collect(Collectors.toList());

        list.addAll(Arrays.asList(1,2,3,200,500, 909999, 12343523));

        for (int num : list) {
            if (!bitSet1.get(num)) {  // 检查该数字是否已经存在
                bitSet1.set(num);// 如果不存在，标记为存在
//                System.out.println("N ew number: " + num);  // 输出新发现的数字
            } else {
                res.add(num); // 保存重复数字
            }
        }

        for(Object num : res){
            System.out.println(num);
        }

    }

}


