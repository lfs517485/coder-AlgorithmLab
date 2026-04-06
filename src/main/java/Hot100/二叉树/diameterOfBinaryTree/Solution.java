package Hot100.二叉树.diameterOfBinaryTree;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans > 0 ? ans - 1 : 0;  // 修正：处理空树情况
    }

    public int dfs(TreeNode root){
        if(root == null) return 0;
        int maxLeft = dfs(root.left);
        int maxRight = dfs(root.right);

        ans = Math.max(ans, maxLeft + maxRight + 1);
        return Math.max(maxLeft, maxRight) + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例：
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        // 直径路径：4 -> 2 -> 1 -> 3 或 5 -> 2 -> 1 -> 3
        // 直径长度 = 4 - 1 = 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int result = solution.diameterOfBinaryTree(root);
        System.out.println("二叉树的直径: " + result);
    }
}
