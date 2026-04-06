package cn.bugstack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, -2, 1, 1, 1};
        int k = 0;
       /* int[] arr = {0, 1, 2, 3};
        int k = 3;*/
       /* int[] arr = {3};
        int k = 3;*/
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;

        System.out.println(a == b);// true
        System.out.println(c == d);//false
        System.out.println(a.equals(b));//true
        System.out.println(c.equals(d));//true

        System.out.println(maxSubArrayLen(arr, k));
    }

    private static int maxSubArrayLen(int[] arr, int k) {
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, -1);  // 初始前缀和为0的位置为-1
        int sum = 0, maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            int target = sum - k;

            // 检查是否存在目标前缀和
            if (prefixSum.containsKey(target)) {
                maxLen = Math.max(maxLen, i - prefixSum.get(target));
            }

            // 只保留最早出现的前缀和
            if(!prefixSum.containsKey(sum)){
                prefixSum.put(sum, i);
            }
        }
        return maxLen;
    }
}