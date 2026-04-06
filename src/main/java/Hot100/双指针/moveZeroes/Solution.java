package Hot100.双指针.moveZeroes;

public class Solution {

    public static void moveZeroes(int[] nums) {

        int l = 0;
        int r = 0;

        while(r < nums.length){
            if(nums[r] != 0){
                nums[l] = nums[r];
                l++;
                r++;
            }else{
                r++;
            }
        }

        while(l < nums.length){
            nums[l] = 0;
            l++;
        }
    }

    public static void main(String[] args){

        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
    }
}