package com.example.springbootdemo;

import scala.Int;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaosen
 * @since 2024/1/18 14:57
 */
public class Test5 {

    public static void main(String[] args) {


        int[] nums = {1,1,2,3};
        int[] quantity = {2,2};

        Solution solution = new Solution();
        System.out.println(solution.canDistribute(nums, quantity));

    }

    static class Solution {
        public boolean canDistribute(int[] nums, int[] quantity) {

            int counts = 0;

            Map<Integer, Integer> res = new HashMap<>();
            res.put(nums[0], 1);

            for(int j = 1;j < nums.length;j++){


                System.out.println(nums[j]);
                if(res.get(nums[j]) != null){
                    res.replace(nums[j], res.get(nums[j]) + 1);
                } else {
                    res.put(nums[j], 1);
                }

            }

            System.out.println(res);

            ArrayList<Integer> removeList = new ArrayList<>();

            for(int i = 0;i<quantity.length;i++){
                counts = counts + quantity[i];

                if(counts > 50){
                    return false;
                }else {
                    for(Map.Entry<Integer, Integer> entry: res.entrySet()){
                        if(entry.getValue() == quantity[i]){
                            removeList.add(entry.getKey());
                            break;
                        }
                    }
                }

            }

            if(removeList.size() == quantity.length){
                return true;
            } else {
                return false;
            }
        }
    }
}
