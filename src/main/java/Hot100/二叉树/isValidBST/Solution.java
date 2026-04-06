package Hot100.二叉树.isValidBST;

import java.util.*;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    List<Integer> ans;

    public boolean isValidBST(TreeNode root) {
        ans = new ArrayList<>();
        dfs(root);
        for(int i = 0; i < ans.size() - 1; i++){
            if(ans.get(i) >= ans.get(i + 1))
                return false;
        }
        return true;
    }

    public void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        ans.add(root.val);
        dfs(root.right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例：有效的二叉搜索树
        //        2
        //       / \
        //      1   3
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        boolean result = solution.isValidBST(root);
        System.out.println("二叉搜索树验证结果: " + result);
    }
}