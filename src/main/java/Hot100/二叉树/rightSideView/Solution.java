package Hot100.二叉树.rightSideView;

import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        LinkedList<TreeNode> queue = new LinkedList<>();

        LinkedList<Integer> level;
        queue.addLast(root);
        while(queue.size() != 0){
            level = new LinkedList<>();
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode node = queue.removeFirst();
                level.add(node.val);
                if(node.left != null) queue.addLast(node.left);
                if(node.right != null) queue.addLast(node.right);
            }
            ans.add(level.getLast());
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例：
        //        1
        //       / \
        //      2   3
        //       \   \
        //        5   4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        List<Integer> result = solution.rightSideView(root);
        System.out.println("二叉树的右视图: " + result);
        System.out.println("说明：从右侧看，应该看到节点 1, 3, 4");
    }
}