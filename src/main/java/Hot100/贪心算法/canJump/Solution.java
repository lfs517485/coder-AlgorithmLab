package Hot100.贪心算法.canJump;


public class Solution {
    public static boolean canJump(int[] nums) {
        int res = 0;//定义当前位置所能到达的最远的位置

        for(int i = 0; i < nums.length; i++){
            if(i <= res){//最远位置， i能走到
                res = Math.max(res, i + nums[i]);
            }
            if(res >= nums.length - 1){//已经走到或走过了终点
                return true;
            }
        }
        return false; //走不到终点
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        boolean ans = canJump(nums);
        System.out.println(ans);
    }
}