package Hot100.回溯.subsets;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 存储所有子集结果
    List<List<Integer>> ans;
    // 存储当前正在构建的子集
    List<Integer> level;

    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        level = new ArrayList<>();
        // 从索引0开始深度优先搜索
        dfs(0, nums);
        return ans;
    }

    /**
     * 深度优先搜索函数，使用回溯法生成子集
     */
    private void dfs(int index, int[] nums) {
        // 终止条件：已经处理完所有元素
        if (index == nums.length) {
            // 保存当前子集（注意创建新列表）
            ans.add(new ArrayList<>(level));
            return;
        }

        // 情况1：选择当前元素
        level.add(nums[index]);          // 做出选择
        dfs(index + 1, nums);            // 递归处理下一个元素
        level.remove(level.size() - 1);  // 撤销选择（回溯）

        // 情况2：不选择当前元素
        dfs(index + 1, nums);            // 直接递归处理下一个元素
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3};

        List<List<Integer>> result = solution.subsets(nums);

        for (List<Integer> subset : result) {
            System.out.println(subset);
        }

    }
}