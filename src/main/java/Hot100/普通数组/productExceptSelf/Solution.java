package Hot100.普通数组.productExceptSelf;

public class Solution {
    public static int[] productExceptSelf(int[] nums) {
        int[] pre = new int[nums.length + 1];
        pre[0] = 1;
        for(int i = 1; i < pre.length; i++){
            pre[i] = pre[i - 1] * nums[i - 1];
        }


        int[] next = new int[nums.length + 2];
        next[nums.length + 1] = 1;
        for(int i = nums.length; i > 0; i--){
            next[i] = next[i + 1] * nums[i - 1];
        }


        int[] ans = new int[nums.length];
        for(int i = 0; i < ans.length; i++){
            ans[i] = pre[i] * next[i + 2];
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] ans = productExceptSelf(nums);
        for (int an : ans) {
            System.out.print(an + " ");
        }
    }
}