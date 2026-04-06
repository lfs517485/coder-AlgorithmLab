package Hot100.哈希.groupAnagrams;

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // key:单词的字典序， value: 字典序相同的单词列表
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            // String->char数组
            char[] charS = strs[i].toCharArray();
            // 按照字典序进行排序
            Arrays.sort(charS);
            // char数组->String
            String key = new String(charS);
            // 单词列表添加
            ArrayList<String> level = map.getOrDefault(key, new ArrayList<String>());
            level.add(strs[i]);

            map.put(key, level);
        }

        return new ArrayList<List<String>>(map.values());
    }

    // 补充的main方法：用于测试验证
    public static void main(String[] args) {
        // 1. 定义测试用例（经典的字母异位词分组场景）
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        // 2. 创建Solution实例并调用核心方法
        Solution solution = new Solution();
        List<List<String>> result = solution.groupAnagrams(strs);

        // 3. 格式化打印结果，直观展示分组效果
        // 遍历每一组异位词并打印
        for (int i = 0; i < result.size(); i++) {
            List<String> group = result.get(i);
            System.out.printf("第%d组：%s%n", i + 1, group);
        }
    }
}