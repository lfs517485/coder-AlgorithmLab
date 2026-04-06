package Hot100.哈希.twoSum;

import java.util.HashMap;

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                return new int[] {map.get(target - nums[i]), i};
            }else{
                map.put(nums[i], i);
            }
        }
        return new int[] {-1, -1};
    }
    public static void main(String[] args) {
        int[] arr = {1, -2, 1, 1, 1};
        int t = 2;

        int[] result = twoSum(arr, t);

        if (result[0] == -1 && result[1] == -1) {
            System.out.println("未找到满足条件的两个数");
        } else {
            int num1 = arr[result[0]];
            int num2 = arr[result[1]];
            System.out.printf("索引[%d, %d] 对应的数值[%d, %d]，和为 %d%n",
                    result[0], result[1], num1, num2, num1 + num2);
        }
    }
}
