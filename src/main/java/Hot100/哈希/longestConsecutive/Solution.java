package Hot100.哈希.longestConsecutive;

import java.util.HashSet;

public class Solution{

    public static int longestConsecutive(int[] nums){

        int ans = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }

        for(int x : set){
            if(set.contains(x - 1)){
                continue;
            }
            else{
                int len = 0;
                int res = x;
                while(set.contains(res)){
                    res++;
                    len++;
                }

                ans = Math.max(ans, len);

            }
        }

        return ans;
    }
    public static void main(String[] args){

        int[] nums = {100, 4, 200, 1, 3, 2};

        System.out.println(longestConsecutive(nums));

    }
}