package Hot100.回溯.exist;

class Solution {
    // 四个方向的偏移量：上、下、左、右
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    /**
     * 判断单词是否存在于二维字符网格中
     * @param board 二维字符网格
     * @param word 要查找的单词
     * @return 如果单词存在于网格中则返回 true，否则返回 false
     */
    public boolean exist(char[][] board, String word) {
        // 创建访问标记数组，记录每个位置是否已被访问
        boolean[][] visited = new boolean[board.length][board[0].length];

        // 遍历网格中的每个位置，作为搜索的起点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 从当前位置开始深度优先搜索
                if (dfs(board, visited, 0, word, i, j)) {
                    return true; // 找到完整单词路径
                }
            }
        }
        return false; // 所有起点都尝试过，未找到
    }

    /**
     * * @param board 二维字符网格
     * @param visited 访问标记数组
     * @param index 当前匹配的单词字符索引
     * @param word 目标单词
     * @param x 当前行坐标
     * @param y 当前列坐标
     * @return 是否找到从当前位置开始的匹配路径
     */
    private boolean dfs(char[][] board, boolean[][] visited, int index, String word, int x, int y) {
        // 剪枝：当前字符不匹配，直接返回 false
        if (board[x][y] != word.charAt(index)) {
            return false;
        }

        // 终止条件：最后一个字符匹配成功
        if (index == word.length() - 1) {
            return true;
        }

        // 标记当前位置为已访问
        visited[x][y] = true;

        // 尝试四个方向
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            // 检查新位置是否在边界内且未被访问
            if (newX >= 0 && newX < board.length &&
                    newY >= 0 && newY < board[0].length &&
                    !visited[newX][newY]) {

                // 递归搜索下一个字符
                if (dfs(board, visited, index + 1, word, newX, newY)) {
                    return true;
                }
            }
        }

        // 回溯：取消当前位置的访问标记
        visited[x][y] = false;

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";

        boolean result = solution.exist(board, word);

        System.out.println(result);
    }
}