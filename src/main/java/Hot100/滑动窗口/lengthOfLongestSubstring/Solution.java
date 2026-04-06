package Hot100.滑动窗口.lengthOfLongestSubstring;

import java.util.HashMap;

public class Solution {
    public static int lengthOfLongestSubstring(String s) {

        int ans = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        char[] chars = s.toCharArray();
        int l = 0;
        int r = 0;

        while(r < chars.length){
            map.put(chars[r],map.getOrDefault(chars[r], 0) + 1);

            while(map.get(chars[r]) > 1){
                map.put(chars[l], map.getOrDefault(chars[l], 0) - 1);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        return ans;
    }

    public static void main(String[] args){
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}