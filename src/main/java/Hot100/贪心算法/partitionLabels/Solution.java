package Hot100.贪心算法.partitionLabels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public static List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();

        char[] charS = s.toCharArray();
        for(int i = 0; i < charS.length; i++){
            map.put(charS[i], i);
        }

        int l = 0;
        int r = 0;

        for(int i = 0; i < charS.length; i++){
            r = Math.max(r, map.get(charS[i]));
            if(r == i){
                ans.add(r - l + 1);
                l = r + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> ans = partitionLabels(s);
        System.out.println(ans);
    }
}