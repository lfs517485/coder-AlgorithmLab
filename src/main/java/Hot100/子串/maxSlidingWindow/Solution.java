package Hot100.子串.maxSlidingWindow;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static int[] maxSlidingWindow(int[] nums, int k) {

        int[] ans = new int[nums.length - k + 1];

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return o2[0] - o1[0];//降序
                }
                return o2[1] - o1[1];//降序
            }
        });

        for(int i = 0; i < k; i++){
            queue.add(new int[] {nums[i],i});
        }

        ans[0] = queue.peek()[0];

        for(int i = k; i < nums.length; i++){
            queue.add(new int[] {nums[i], i});

            while(queue.peek()[1] < i - k + 1){
                queue.remove();
            }

            ans[i - k + 1] = queue.peek()[0];
        }

        return ans;

    }

    public static void main(String[] args){

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] ans = maxSlidingWindow(nums, k);

        for (int an : ans) {
            System.out.print(an + ",");
        }

    }

}