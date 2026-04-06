package Hot100.二叉树.maxPathSum;

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root){
        if(root == null) return 0;

        int leftMax = Math.max(0, dfs(root.left));
        int rightMax = Math.max(0, dfs(root.right));

        ans = Math.max(ans, leftMax + rightMax + root.val);
        return Math.max(leftMax, rightMax) + root.val;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例1: [-10,9,20,null,null,15,7]
        //        -10
        //        / \
        //       9  20
        //         /  \
        //        15   7
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int result = solution.maxPathSum(root);
        System.out.println("二叉树的最大路径和: " + result);
        System.out.println("最大路径: 15 → 20 → 7，和为 42");
    }
}
