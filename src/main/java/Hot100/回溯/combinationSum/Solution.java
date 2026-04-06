package Hot100.回溯.combinationSum;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 存储所有符合条件的组合
    List<List<Integer>> ans;
    // 存储当前正在构建的组合
    List<Integer> level;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        level = new ArrayList<>();

        // 从索引0开始深度优先搜索
        dfs(candidates, 0, target);

        return ans;
    }

    /**
     * 深度优先搜索函数，使用回溯法寻找所有组合
     * @param candidates 候选数组
     * @param index 当前处理的候选元素索引
     * @param target 剩余目标值
     */
    private void dfs(int[] candidates, int index, int target) {
        // 终止条件1：剩余目标值为0，找到有效组合
        if (target == 0) {
            ans.add(new ArrayList<>(level));
            return;
        }

        // 终止条件2：已经遍历完所有候选元素
        if (index == candidates.length) {
            return;
        }

        // 情况1：跳过当前元素，直接考虑下一个元素
        dfs(candidates, index + 1, target);

        // 情况2：选择当前元素（前提是选择后目标值不会为负数）
        if (target - candidates[index] >= 0) {
            level.add(candidates[index]);  // 选择当前元素
            dfs(candidates, index, target - candidates[index]);  // 继续考虑当前元素
            level.remove(level.size() - 1);  // 回溯，撤销选择
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = solution.combinationSum(candidates, target);

        for (List<Integer> combination : result) {
            System.out.println(combination);
        }

    }
}