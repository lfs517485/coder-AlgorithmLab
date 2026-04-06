package Hot100.图论.orangesRotting;
import java.util.*;

class Solution {
    // 方向数组：上下左右四个方向的坐标偏移量
    int[] dx = { -1, 1, 0, 0 }; // 上下移动：行坐标变化
    int[] dy = { 0, 0, -1, 1 }; // 左右移动：列坐标变化

    // 记录腐烂过程所需的时间（分钟数）
    int ans = 0;

    public int orangesRotting(int[][] grid) {
        int fresh = 0; // 统计新鲜橘子的数量
        ArrayList<int[]> queue = new ArrayList<>(); // 存储腐烂橘子的坐标（BFS队列）

        // 第一次遍历：统计新鲜橘子数量，并将所有腐烂橘子加入队列
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    fresh++; // 新鲜橘子计数
                } else if (grid[i][j] == 2) {
                    queue.add(new int[] { i, j }); // 腐烂橘子加入队列（起始点）
                }
            }
        }

        // BFS（广度优先搜索）过程
        // 退出条件：
        // 1. 还有新鲜橘子，但腐烂队列为空 -> 返回 -1（不可能全部腐烂）
        // 2. 没有新鲜橘子 -> 返回正常答案（所有橘子已腐烂）
        while (fresh != 0 && !queue.isEmpty()) {
            ans++; // 时间增加一分钟
            int len = queue.size(); // 当前分钟需要处理的腐烂橘子数量

            // 处理当前分钟的所有腐烂橘子（同时传播）
            for (int i = 0; i < len; i++) {
                int[] pos = queue.remove(0); // 从队列头部取出一个腐烂橘子

                // 向四个方向传播腐烂
                for (int j = 0; j < 4; j++) {
                    int x = pos[0] + dx[j]; // 计算新位置的行坐标
                    int y = pos[1] + dy[j]; // 计算新位置的列坐标

                    // 检查新位置是否有效且是新鲜橘子
                    if (0 <= x && x < grid.length && 0 <= y && y < grid[0].length && grid[x][y] == 1) {
                        grid[x][y] = 2; // 新鲜橘子变腐烂
                        fresh--; // 新鲜橘子数量减少
                        queue.add(new int[] { x, y }); // 新腐烂橘子加入队列
                    }
                }
            }
        }

        // 如果还有新鲜橘子，返回 -1；否则返回所需时间
        return fresh != 0 ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        System.out.println("初始网格状态（0:空, 1:新鲜, 2:腐烂）：");

        int result = solution.orangesRotting(grid);

        System.out.println("\n所有橘子腐烂所需的最小时间: " + result + " 分钟");

    }

}
