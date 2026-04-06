package Hot100.二叉树.lowestCommonAncestor;

import java.util.*;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    public boolean dfs(TreeNode node, TreeNode p, TreeNode q){
        if(node == null) return false;
        boolean lson = dfs(node.left, p, q);
        boolean rson = dfs(node.right, p, q);

        if((lson && rson) || ((lson || rson) && (node.val == p.val || node.val == q.val)))
            ans = node;

        return lson || rson || node.val == p.val || node.val == q.val;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 构建题目示例的二叉树
        //         3
        //        / \
        //       5   1
        //      / \ / \
        //     6  2 0  8
        //       / \
        //      7   4
        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // 查找节点 5 和节点 1 的最近公共祖先
        TreeNode p = root.left;     // 节点5
        TreeNode q = root.right;    // 节点1

        TreeNode result = solution.lowestCommonAncestor(root, p, q);

        if(result != null) {
            System.out.println("节点 " + p.val + " 和节点 " + q.val + " 的最近公共祖先是节点 " + result.val);
        } else {
            System.out.println("未找到公共祖先");
        }
    }
}