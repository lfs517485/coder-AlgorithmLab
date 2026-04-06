package Hot100.双指针.trap;


public class Solution {


    public static int trap(int[] height) {

        int maxL = 0;
        int maxR = 0;

        int l = 0;
        int r = height.length - 1;

        int ans = 0;

        while(l <= r) {
            maxL = Math.max(maxL, height[l]);
            maxR = Math.max(maxR, height[r]);

            if (maxL < maxR) {
                ans += maxL - height[l];
                l++;
            } else {
                ans += maxR - height[r];
                r--;
            }
        }

        return ans;

    }

    public static void main(String[] args){
        int[] height ={4,2,0,3,2,5};

        System.out.println(trap(height));

    }
}