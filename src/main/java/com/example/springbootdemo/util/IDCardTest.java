package com.example.springbootdemo.util;

import cn.hutool.core.util.IdcardUtil;

/**
 * @author gaosen
 * @since 2024/9/19 15:58
 */
public class IDCardTest {

    public static void main(String[] args) {

        String card1 = "37062819621212000X";
        String card2 = "370221967011068319";
        String card3 = "372109198605163322";
        String card4 = "830000200106040010";
        String card5 = "330381198700201220";
        String card6 = "370602196304251623";

        String card7 = "511122197408277457";


        isCard(card1);
        isCard(card2);
        isCard(card3);
        isCard(card4);
        isCard(card5);
        isCard(card6);

        isCard(card7);


    }

    static void isCard(String card){
        System.out.println(card + " is "
                + IdcardUtil.isValidCard(card) + " 归属地 "
                + IdcardUtil.getProvinceByIdCard(card));
    }
}
