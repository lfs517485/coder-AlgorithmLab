package Hot100.矩阵.setZeroes;

public class Solution {
    public static void setZeroes(int[][] matrix) {
        // 边界条件：处理空矩阵
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length;      // 矩阵行数
        int n = matrix[0].length;   // 矩阵列数

        // 标记数组：记录哪些行和列需要置零
        // row[i] = true 表示第i行需要全部置零
        boolean[] row = new boolean[m];
        // col[j] = true 表示第j列需要全部置零
        boolean[] col = new boolean[n];

        // 第一次遍历：扫描矩阵，记录0元素的位置
        // 时间复杂度 O(m×n)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 状态转移方程：遇到0元素，标记其行和列
                if (matrix[i][j] == 0) {
                    row[i] = true;      // 标记第i行需要置零
                    col[j] = true;      // 标记第j列需要置零
                }
            }
        }

        // 第二次遍历：根据标记数组，将对应行和列的元素置零
        // 时间复杂度 O(m×n)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 状态转移方程：如果当前行或列被标记，则元素置零
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;   // 将元素置零
                }
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println(" ");
        }
    }
}