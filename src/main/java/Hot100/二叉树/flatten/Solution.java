package Hot100.二叉树.flatten;

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
    ArrayList<TreeNode> ans;

    public void flatten(TreeNode root) {
        if(root == null) return;  // 添加空树检查

        ans = new ArrayList<>();
        dfs(root);

        for(int i = 1; i < ans.size(); i++){
            TreeNode pre = ans.get(i - 1);
            TreeNode cur = ans.get(i);

            pre.left = null;
            pre.right = cur;
        }
    }

    public void dfs(TreeNode root){
        if(root == null) return;
        ans.add(root);
        dfs(root.left);
        dfs(root.right);
    }

    // 辅助方法：打印展平后的链表
    public static void printFlattened(TreeNode root) {
        TreeNode cur = root;
        System.out.print("展平后的链表: ");
        while(cur != null) {
            System.out.print(cur.val);
            if(cur.right != null) {
                System.out.print(" -> ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例：
        //        1
        //       / \
        //      2   5
        //     / \   \
        //    3   4   6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        solution.flatten(root);
        printFlattened(root);
    }
}