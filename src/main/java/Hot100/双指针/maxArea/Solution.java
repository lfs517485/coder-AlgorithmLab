package Hot100.双指针.maxArea;

public class Solution{

    public static int maxArea(int[] height) {

        int ans = 0;

        int l = 0;
        int r = height.length - 1;

        while(l <= r){
            int  Area = (r - l) * Math.min(height[l], height[r]);
            ans = Math.max(Area, ans);

            if(height[l] < height[r]){
                l++;
            }else{
                r--;
            }
        }

        return ans;
    }

    public static void main(String[] args){
        int[] height = {1,8,6,2,5,4,8,3,7};

        System.out.println(maxArea(height));

    }
}