package Hot100.回溯.permute;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 存储所有排列结果
    List<List<Integer>> ans;
    // 存储当前正在构建的排列
    List<Integer> level;

    /**
     * 主函数：生成数组的所有全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        // 初始化结果列表和当前排列列表
        ans = new ArrayList<>();
        level = new ArrayList<>();
        // 开始深度优先搜索
        dfs(nums);
        return ans;
    }

    /**
     * 深度优先搜索函数，使用回溯法生成排列
     */
    private void dfs(int[] nums) {
        // 终止条件：当前排列长度等于原始数组长度，说明已生成一个完整排列
        if (level.size() == nums.length) {
            // 注意：必须创建新的ArrayList，否则会添加引用导致结果被修改
            ans.add(new ArrayList<>(level));
            return;
        }

        // 遍历所有可能的选择
        for (int i = 0; i < nums.length; i++) {
            // 剪枝：如果当前数字已经在排列中，跳过
            if (level.contains(nums[i])) continue;

            // 做出选择：将当前数字加入排列
            level.add(nums[i]);

            // 递归：继续构建剩余部分的排列
            dfs(nums);

            // 撤销选择：回溯，移除最后一个数字，尝试其他可能性
            level.remove(level.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3};
        // 生成所有排列
        List<List<Integer>> permutations = solution.permute(nums);

        // 输出每个排列
        for (int i = 0; i < permutations.size(); i++) {
            System.out.printf("排列 %d: %s\n", i + 1, permutations.get(i));
        }
    }
}