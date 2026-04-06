package Hot100.矩阵.rotate;


public class Solution {
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // 第一步：上下翻转（垂直翻转）
        // 将第i行与第n-1-i行交换
        // i < n/2：只需遍历上半部分的行，防止重复交换
        for (int i = 0; i < n / 2; i++) {
            // 遍历当前行的所有列
            for (int j = 0; j < n; j++) {
                // 交换(i,j)与(n-1-i,j)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }

        // 第二步：沿主对角线翻转（矩阵转置）
        // 交换matrix[i][j]与matrix[j][i]，其中i > j（或i < j）
        for (int i = 0; i < n; i++) {
            // j < i：只遍历下三角矩阵（不包括对角线），避免重复交换
            for (int j = 0; j < i; j++) {
                // 交换(i,j)与(j,i)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix);
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

    }
}