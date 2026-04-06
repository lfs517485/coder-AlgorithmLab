package Hot100.回溯.solveNQueens;

import java.util.*;

class Solution {
    // 存储所有N皇后问题的解决方案
    List<List<String>> ans;
    // 记录哪些列已经被占用
    Set<Integer> col;
    // queens[i]表示第i行的皇后放置在第几列
    int[] queens;
    // 记录左上到右下对角线是否被占用 (row - col 为常量)
    Set<Integer> diags1;
    // 记录左下到右上对角线是否被占用 (row + col 为常量)
    Set<Integer> diags2;

    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();

        // 初始化数据结构
        col = new HashSet<>();
        queens = new int[n];
        Arrays.fill(queens, -1);  // -1表示该行尚未放置皇后
        diags1 = new HashSet<>();
        diags2 = new HashSet<>();

        // 从第0行开始深度优先搜索
        dfs(0, n);

        return ans;
    }

    /**
     * 深度优先搜索函数，使用回溯法放置皇后
     * @param row 当前正在处理的行号
     * @param n 棋盘大小
     */
    private void dfs(int row, int n) {
        // 终止条件：所有行都已放置皇后
        if (row == n) {
            ans.add(generateBoard(queens, n));
            return;
        }

        // 尝试在当前行的每一列放置皇后
        for (int col = 0; col < n; col++) {
            // 检查当前位置是否合法
            if (this.col.contains(col)) continue;  // 同一列已有皇后

            int diag1 = row - col;  // 左上-右下对角线标识
            if (diags1.contains(diag1)) continue;

            int diag2 = row + col;  // 左下-右上对角线标识
            if (diags2.contains(diag2)) continue;

            // 当前位置合法，放置皇后
            queens[row] = col;
            this.col.add(col);
            diags1.add(diag1);
            diags2.add(diag2);

            // 递归放置下一行的皇后
            dfs(row + 1, n);

            // 回溯：撤销当前选择
            queens[row] = -1;
            this.col.remove(col);
            diags1.remove(diag1);
            diags2.remove(diag2);
        }
    }

    /**
     * 根据皇后位置生成棋盘表示
     */
    private List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '.');
            rowChars[queens[i]] = 'Q';  // 在第queens[i]列放置皇后
            board.add(new String(rowChars));
        }
        return board;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 4;
        List<List<String>> result = solution.solveNQueens(n);

        for (int i = 0; i < result.size(); i++) {
            System.out.println("\n方案 " + (i + 1) + ":");
            List<String> board = result.get(i);
            for (String row : board) {
                System.out.println(row);
            }
        }


    }
}