package Hot100.子串.subarraySum;

import java.util.HashMap;

public class Solution {
    public static int subarraySum(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;

        // 构建前缀和数组pre，pre[i]表示前i个元素的和（即索引0到i-1的和）
        int[] pre = new int[n + 1];
        for(int i = 1; i <= n; i++){
            pre[i] = pre[i - 1] + nums[i - 1];
        }

        // 创建哈希表，key为前缀和的值，value为该前缀和出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);// 初始化哈希表：前缀和为0出现1次（即没有任何元素时，前缀和为0）

        // 遍历前缀和数组（从索引1开始，即第一个元素的前缀和）
        for(int i = 1; i <= n; i++){
            int target = pre[i] - k;// 计算需要查找的前缀和值：pre[j] - k

            if(map.containsKey(target)){ // 如果哈希表中存在该值，则说明存在若干个子数组满足条件
                ans += map.get(target); // 增加结果计数
            }
            // 将当前前缀和pre[j]加入哈希表，并更新出现次数
            map.put(pre[i], map.getOrDefault(pre[i], 0) + 1);
        }

        return ans;

    }
    public static void main(String[] args){
        int[] nums = {1,1,1};
        int k = 1;
        System.out.println(subarraySum(nums, k));
    }
}