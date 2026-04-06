package Hot100.多维动态规划.longestPalindrome;

class Solution {
    /**
     * 寻找字符串中最长的回文子串
     * 使用中心扩展法：遍历每个字符（以及每两个相邻字符）作为回文中心，向两边扩展
     */
    public String longestPalindrome(String s) {
        // 记录最长回文子串的起始和结束位置（包含）
        int start = 0, end = 0;
        char[] charS = s.toCharArray();

        for (int i = 0; i < charS.length; i++) {
            // 奇数长度回文：中心为单个字符
            int lenOdd = expandAroundCenter(charS, i, i);
            // 偶数长度回文：中心为两个字符
            int lenEven = expandAroundCenter(charS, i, i + 1);
            // 取当前中心能得到的最大回文长度
            int len = Math.max(lenOdd, lenEven);

            // 如果找到更长的回文子串，更新起始和结束位置
            if (len > (end - start + 1)) {
                // 计算新回文子串的左右端点
                // 奇数回文：中心索引i，长度len -> 左端点 i - (len-1)/2，右端点 i + len/2
                // 偶数回文：左中心i，长度len -> 左端点 i - len/2 + 1，右端点 i + len/2
                // 统一公式如下：
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // substring 方法左闭右开：[start, end+1)
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(char[] charS, int left, int right) {
        // 向两边扩展，直到不能形成回文
        while (left >= 0 && right < charS.length && charS[left] == charS[right]) {
            left--;
            right++;
        }

        // 循环结束时，left和right分别指向回文串外左侧和右侧的字符
        // 回文串的实际范围是 [left+1, right-1]
        // 长度 = (right-1) - (left+1) + 1 = right - left - 1
        return right - left - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例1：题目示例
        String s1 = "babad";
        String result1 = solution.longestPalindrome(s1);

        System.out.println(result1);
    }
}
