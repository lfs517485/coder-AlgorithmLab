package Hot100.滑动窗口.findAnagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public static List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> ans = new ArrayList<>();

        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapP = new HashMap<>();

        char[] charP = p.toCharArray();
        for(int i = 0; i < charP.length; i++){
            mapP.put(charP[i], mapP.getOrDefault(charP[i], 0) + 1);
        }

        char[] charS = s.toCharArray();

        int l = 0;
        int r = 0;
        int valid = 0;
        while(r < charS.length){
            if(mapP.containsKey(charS[r])){
                mapS.put(charS[r], mapS.getOrDefault(charS[r], 0) + 1);
                if(mapS.get(charS[r]).equals(mapP.get(charS[r]))){
                    valid++;
                }

                while(valid == mapP.size()){
                    if(r - l + 1 == charP.length){
                        ans.add(l);
                    }
                    if(mapP.containsKey(charS[l])){
                        if(mapP.get(charS[l]).equals(mapS.get(charS[l]))){
                            valid--;
                        }
                        mapS.put(charS[l], mapS.getOrDefault(charS[l], 0) - 1);

                        l++;
                    }else {
                        l++;
                    }

                }
            }
            r++;
        }

        return ans;
    }

    public static void main(String[] args){
        String s = "abab";
        String p = "ab";

        List<Integer> res = findAnagrams(s, p);

        for (Integer re : res) {
            System.out.print(re + ",");
        }
    }
}