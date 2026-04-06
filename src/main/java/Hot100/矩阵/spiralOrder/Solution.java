package Hot100.矩阵.spiralOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法思路概述：
 * 问题分析：本题要求按照顺时针螺旋顺序遍历矩阵。螺旋遍历的过程可以理解为：
 *          从左到右遍历上边界 → 从上到下遍历右边界 → 从右到左遍历下边界 → 从下到上遍历左边界，
 *          然后收缩边界，重复上述过程，直到所有元素都被遍历。
 *          核心难点在于边界条件的控制和循环终止条件的判断。
 *
 * 核心思想：设定四个边界变量：左边界l、右边界r、上边界up、下边界down。
 *          每遍历完一条边，就收缩对应的边界（up++、r--、down--、l++），
 *          同时用计数器res记录已遍历的元素个数，当res == 总元素数(m * n)时结束循环。
 *
 * 关键处理：
 *          1. 边界收缩时机：每遍历完一条边，立即收缩，避免重复遍历。
 *          2. 循环中的防越界判断：每个for循环的条件中都加上 res < m * n 的判断，
 *             防止在最后一次遍历时因边界收缩导致重复添加元素。
 *          3. 边界条件的顺序：先遍历上边，右边，下边，左边，然后收缩左边界，形成闭环。
 *
 * 时空复杂度：
 *          - 时间复杂度：O(m * n)，每个元素恰好被访问一次。
 *          - 空间复杂度：O(1)，除了存储结果的列表外，只使用了常数个变量。
 *
 * 边界条件：
 *          - 空矩阵：matrix.length == 0 或 matrix[0].length == 0，返回空列表。
 *          - 单行矩阵：例如 [[1,2,3]]，需要防止下边界上移后左边遍历重复。
 *          - 单列矩阵：例如 [[1],[2],[3]]，需要防止右边界左移后上边遍历重复。
 *          - 矩阵只有一个元素：循环能正确处理。
 */

public class Solution {
    public static List<Integer> spiralOrder(int[][] matrix) {
        // 边界条件1：处理空矩阵
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }

        // 获取矩阵的行数m和列数n
        int m = matrix.length;
        int n = matrix[0].length;

        // 计数器：记录已经添加到结果列表中的元素个数
        int res = 0;
        // 存储结果的列表
        List<Integer> ans = new ArrayList<>();

        // 定义四个边界：
        // 左边界l，右边界r，上边界up，下边界down
        // 边界变量的作用：表示当前尚未遍历的矩阵区域的边界
        int l = 0;          // 左边界初始为0列
        int r = n - 1;      // 右边界初始为最后一列
        int up = 0;         // 上边界初始为第0行
        int down = m - 1;   // 下边界初始为最后一行

        // 循环条件：当已遍历元素数小于总元素数时继续
        // 这里使用res < m * n作为循环条件，比用边界比较更直观且不易出错
        while (res < m * n) {

            // 第一步：从左到右遍历上边界
            // i从左边界的索引l开始，到右边界的索引r结束
            // 关键：每次移动指针前都要检查res < m * n，防止最后一轮重复添加
            for (int i = l; i <= r && res < m * n; i++) {
                ans.add(matrix[up][i]);     // 添加当前元素
                res++;                      // 计数器+1
            }
            // 上边界遍历完毕，收缩上边界（向下移动一行）
            up++;

            // 第二步：从上到下遍历右边界
            // j从当前上边界up开始，到下边界down结束
            for (int j = up; j <= down && res < m * n; j++) {
                ans.add(matrix[j][r]);      // 添加当前元素
                res++;
            }
            // 右边界遍历完毕，收缩右边界（向左移动一列）
            r--;

            // 第三步：从右到左遍历下边界
            // i从当前右边界r开始，到左边界l结束，注意方向是递减
            // 边界条件：需要检查上边界是否已经超过下边界（防止单行矩阵重复遍历）
            for (int i = r; i >= l && res < m * n; i--) {
                ans.add(matrix[down][i]);   // 添加当前元素
                res++;
            }
            // 下边界遍历完毕，收缩下边界（向上移动一行）
            down--;

            // 第四步：从下到上遍历左边界
            // j从当前下边界down开始，到上边界up结束，方向递减
            // 边界条件：需要检查左边界是否已经超过右边界（防止单列矩阵重复遍历）
            for (int j = down; j >= up && res < m * n; j--) {
                ans.add(matrix[j][l]);      // 添加当前元素
                res++;
            }
            // 左边界遍历完毕，收缩左边界（向右移动一列）
            l++;
        }

        return ans;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> ans = spiralOrder(matrix);
        System.out.println(ans);
    }
}
