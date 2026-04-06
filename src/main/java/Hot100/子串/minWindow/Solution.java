package Hot100.子串.minWindow;

import java.util.HashMap;

public class Solution {
    public static String minWindow(String s, String t) {
        String ans = "";
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();

        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();

        for(int i = 0; i < charT.length; i++){
            mapT.put(charT[i], mapT.getOrDefault(charT[i],0) + 1);
        }
        int l = 0;
        int r = 0;
        int valid = 0;
        int len = Integer.MAX_VALUE;

        while(r < charS.length){

            mapS.put(charS[r], mapS.getOrDefault(charS[r], 0) + 1);

            if(mapS.get(charS[r]).equals(mapT.get(charS[r]))){
                valid++;
            }

            while(valid == mapT.size()){
                if(r - l + 1 < len){
                    len = r - l + 1;
                    ans = s.substring(l,r + 1);
                }

                if(mapT.containsKey(charS[l])){
                    if(mapT.get(charS[l]).equals(mapS.get(charS[l]))){
                        valid--;
                    }
                    mapS.put(charS[l], mapS.getOrDefault(charS[l], 0) - 1);
                }
                l++;
            }


            r++;
        }

        return ans;
    }

    public static void main(String[] args){
        String s = "a";
        String t = "aa";
        String res = minWindow(s, t);
        System.out.println(res);
    }
}