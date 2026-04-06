package Hot100.二叉树.invertTree;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public static TreeNode invertTree(TreeNode root) {
        if(root == null) return null;

        // 递归翻转左右子树
        invertTree(root.left);
        invertTree(root.right);

        // 交换左右子节点
        if(root.left != null || root.right != null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        return root;
    }

    // 辅助方法：前序遍历打印二叉树
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        // 测试用例：构建一个二叉树
        //       4
        //      / \
        //     2   7
        //    / \ / \
        //   1  3 6  9
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.print("原二叉树前序遍历: ");
        printTree(root);
        System.out.println();

        // 翻转二叉树
        TreeNode inverted = invertTree(root);

        System.out.print("翻转后前序遍历: ");
        printTree(inverted);
        System.out.println();
    }
}