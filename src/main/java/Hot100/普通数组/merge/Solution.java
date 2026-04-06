package Hot100.普通数组.merge;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> ans = new ArrayList<>();

        for(int i = 0; i < intervals.length; i++){
            int l = intervals[i][0];
            int r = intervals[i][1];

            if(ans.isEmpty() || ans.get(ans.size() - 1)[1] < l){
                ans.add(new int[]{l, r});
            }else{
                if(ans.get(ans.size() - 1)[1] < r){
                    ans.get(ans.size() - 1)[1] = r;
                }
            }
        }

        return ans.toArray(new int[ans.size()][2]);

    }

    public static void main(String[] args){
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};

        int[][] ans = merge(intervals);
        for(int i = 0; i < ans.length; i++){
            System.out.print("[" + ans[i][0] + "," + ans[i][1] + "],");
        }
    }
}