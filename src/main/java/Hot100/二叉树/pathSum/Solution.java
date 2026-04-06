package Hot100.二叉树.pathSum;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    HashMap<Long, Integer> pre = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        pre.put(0L, 1);
        return dfs(root, targetSum, 0L);
    }

    public int dfs(TreeNode root, int targetSum, long currSum){
        if(root == null) return 0;
        currSum += root.val;

        int res = pre.getOrDefault(currSum - targetSum, 0);
        pre.put(currSum, pre.getOrDefault(currSum, 0) + 1);
        res += dfs(root.left, targetSum, currSum) + dfs(root.right, targetSum, currSum);
        pre.put(currSum, pre.getOrDefault(currSum, 0) - 1);

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 构建测试用例二叉树:
        //       10
        //      /  \
        //     5   -3
        //    / \    \
        //   3   2   11
        //  / \   \
        // 3  -2   1
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        int targetSum = 8;
        int result = solution.pathSum(root, targetSum);

        System.out.println("路径和等于 " + targetSum + " 的路径数量: " + result);
    }
}