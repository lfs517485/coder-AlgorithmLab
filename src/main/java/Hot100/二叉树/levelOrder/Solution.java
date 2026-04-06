package Hot100.二叉树.levelOrder;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;  // 直接返回空列表，不要new

        List<Integer> level;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while(queue.size() != 0){
            level = new ArrayList<>();
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode node = queue.removeFirst();
                level.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            ans.add(level);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例：
        //        3
        //       / \
        //      9   20
        //         /  \
        //        15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = solution.levelOrder(root);
        System.out.println("二叉树的层序遍历结果:");
        for(List<Integer> level : result) {
            System.out.println(level);
        }
    }
}