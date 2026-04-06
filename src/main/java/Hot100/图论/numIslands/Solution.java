package Hot100.图论.numIslands;

class Solution {
    // 方向数组：上下左右四个方向的坐标偏移量
    // dx[i] 和 dy[i] 组合表示一个方向
    // 上：(-1, 0), 下：(1, 0), 左：(0, -1), 右：(0, 1)
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    // 记录岛屿的数量
    int ans = 0;

    public int numIslands(char[][] grid) {
        // 遍历整个网格
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') { // 发现未访问的陆地
                    ans++; // 发现一个新的岛屿
                    dfs(grid, i, j); // 通过DFS将整个岛屿标记为已访问
                }
            }
        }

        return ans;
    }

    private void dfs(char[][] grid, int x, int y) {
        // 检查坐标是否越界，如果越界则返回（递归结束条件）
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return;

        // 如果当前格子不是陆地，则返回
        if(grid[x][y] != '1') return;

        // 将当前陆地标记为已访问（改为'0'，表示水）
        grid[x][y] = '0';

        // 遍历四个方向：上、下、左、右
        for(int i = 0; i < 4; i++) {
            int newX = x + dx[i]; // 计算新的行坐标
            int newY = y + dy[i]; // 计算新的列坐标

            // 递归访问相邻的陆地
            dfs(grid, newX, newY);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        int result = solution.numIslands(grid);

        System.out.println("\n岛屿数量: " + result);
    }
}