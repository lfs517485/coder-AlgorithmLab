package Hot100.动态规划.generate;


import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> generate(int numRows) {

        int[][] dp = new int[numRows][numRows];

        for(int i = 0; i < numRows; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0 || i == j){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();


        for(int i = 0; i < numRows; i++){
            List<Integer> level = new ArrayList<>();
            for(int j = 0; j <= i; j++){
                level.add(dp[i][j]);
            }
            ans.add(level);
        }
        return ans;

    }

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> ans = generate(numRows);
        for (List<Integer> an : ans) {
            System.out.println(an);
        }
    }
}