package Hot100.普通数组.firstMissingPositive;

public class Solution {
    public static int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            while(0 < nums[i] && nums[i] < nums.length && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        int ans = firstMissingPositive(nums);
        System.out.println(ans);
    }
}