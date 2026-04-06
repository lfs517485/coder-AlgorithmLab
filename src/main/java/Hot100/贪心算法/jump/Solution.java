package Hot100.贪心算法.jump;

public class Solution {
    public static int jump(int[] nums) {
        int end = 0; // 当前位置所能到达的边界
        int res = 0; //记录当前能够到达的最大下标位置
        int ans = 0; //跳跃次数

        //测试用例保证一定能跳到终点
        //当遍历到 nums.length - 1 时(最后一个元素)，已经到达终点，不需要再统计跳跃次数。
        for(int i = 0; i < nums.length - 1; i++){
            res = Math.max(res, i + nums[i]);
            if(i == end){ //到达一个边界时，更新边界并将跳跃次数增加 1
                end = res;
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};
        int ans = jump(nums);
        System.out.println(ans);
    }
}
