package Hot100.二叉树.buildTree;

import java.util.*;

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
    int[] preorder;     // 这里为啥用作成员变量，目的是省的传递参数
    HashMap<Integer, Integer> map = new HashMap<>(); // key:节点值, value:中序遍历位置{用先序查中序}

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;        // 这里为啥用作成员变量，目的是省的传递参数
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }

        return dfs(0, 0, preorder.length - 1);
    }

    // 传入值: 先序遍历: (根节点位置{下标}), 中序遍历： (左子树左边界{下标}, 右子树右边界{下标})
    public TreeNode dfs(int root, int l, int r){
        if(l > r)  return null; // l == r 时,是叶子结点

        TreeNode node = new TreeNode(preorder[root]); // 创建根节点
        int mid = map.get(preorder[root]); // 得到根节点在中序遍历的位置

        node.left = dfs(root + 1, l, mid - 1); // 左子树
        node.right = dfs(root + (mid - l) + 1, mid + 1, r); // 右子树

        return node;
    }

    // 辅助方法：前序遍历打印二叉树
    public static void printPreorder(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // 辅助方法：中序遍历打印二叉树
    public static void printInorder(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例：
        // 前序遍历: [3, 9, 20, 15, 7]
        // 中序遍历: [9, 3, 15, 20, 7]
        // 构建的二叉树:
        //        3
        //       / \
        //      9  20
        //        /  \
        //       15   7
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = solution.buildTree(preorder, inorder);

        System.out.print("重建后二叉树的前序遍历: ");
        printPreorder(root);
        System.out.println();

        System.out.print("重建后二叉树的中序遍历: ");
        printInorder(root);
        System.out.println();
    }
}
