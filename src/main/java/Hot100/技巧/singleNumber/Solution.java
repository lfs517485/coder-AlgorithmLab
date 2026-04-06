package Hot100.技巧.singleNumber;

class Solution {
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) { // 32位数据一位一位的计算
            int res = 0;
            for (int j = 0; j < nums.length; j++) {
                // 取出第i位的值（0或1）
                // nums[j] >> i: 将数字右移i位，使得第i位变成最低位
                // & 1: 通过与1进行按位与操作，取出最低位的值
                res += (nums[j] >> i) & 1;
            }
            // 因为其他数字都出现2次，所以第i位上的1的个数应该是偶数
            // 如果对2取余不为0，说明单独出现的数字在这一位上是1
            // 将这一位恢复到原位置（左移i位），然后通过按位或操作加入到结果中
            ans |= (res % 2) << i; // 这里用 res % 2 是正确的！
        }
        return ans;
    }

    public static void main(String[] args) {

        // 测试用例
        int[] nums = {4, 1, 2, 1, 2};
        int result = singleNumber(nums);
        System.out.println("数组 [4, 1, 2, 1, 2] 中只出现一次的数字是: " + result);

        // 验证：4 ^ 1 ^ 2 ^ 1 ^ 2 = 4
        // 因为 1^1=0, 2^2=0, 所以结果是4
    }
}
