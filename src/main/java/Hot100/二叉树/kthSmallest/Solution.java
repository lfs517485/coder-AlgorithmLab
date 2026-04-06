package Hot100.二叉树.kthSmallest;

import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.removeFirst();
            k--;
            if(k == 0)  break;

            root = root.right;
        }

        return root.val;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例：二叉搜索树
        //        3
        //       / \
        //      1   4
        //       \
        //        2
        // 中序遍历结果：[1, 2, 3, 4]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        int k = 3; // 找第3小的元素
        int result = solution.kthSmallest(root, k);
        System.out.println("二叉搜索树中第 " + k + " 小的元素是: " + result);
        System.out.println("中序遍历: [1, 2, 3, 4]，所以第3小是 3");
    }
}