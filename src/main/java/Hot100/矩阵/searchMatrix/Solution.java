package Hot100.矩阵.searchMatrix;

public class Solution {
    public static boolean searchMatrix(int[][] matrix, int target) {
        //从矩阵的左下角开始
        int i = matrix.length - 1;
        int j = 0;

        while(0 <= i && j <= matrix[0].length - 1){
            if(target == matrix[i][j]){
                return true;
            }else if(target < matrix[i][j]){
                i--;
            }else if(target > matrix[i][j]){
                j++;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 1;
        boolean ans = searchMatrix(matrix, target);
        System.out.println(ans);
    }
}