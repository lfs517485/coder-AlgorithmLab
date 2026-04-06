package Hot100.堆.topKFrequent;

import java.util.*;

class Solution {
    /**
     * 前 K 个高频元素
     * 解题思路：
     * 1. 遍历整个数组，使用哈希表存储每个数字出现的次数
     * 2. 使用最小堆（优先队列）找到哈希表中前 k 个高频元素
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 1. 统计每个数字出现的频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            // 使用 getOrDefault 简化代码，如果不存在则返回0，然后+1
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. 使用最小堆（优先队列）来维护前k个高频元素
        // 堆中存储 [数字, 频率] 数组，按照频率从小到大排序
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] a, int[] b) {
                        // 比较频率，小顶堆
                        return a[1] - b[1];
                    }
                }
        );

        // 3. 遍历频率哈希表，维护大小为k的最小堆
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int num = entry.getKey();    // 数字
            int frequency = entry.getValue(); // 频率

            if (minHeap.size() == k) {
                // 堆已满，比较当前元素频率与堆顶元素频率
                if (minHeap.peek()[1] < frequency) {
                    // 当前元素频率 > 堆顶元素频率，替换堆顶
                    minHeap.poll(); // 移除堆顶（当前最小的频率）
                    minHeap.offer(new int[] {num, frequency});
                }
            } else {
                // 堆未满，直接加入
                minHeap.offer(new int[] {num, frequency});
            }
        }

        // 4. 从堆中取出结果（堆中存储的是频率最小的在堆顶）
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            // 堆中元素是 [数字, 频率]，我们只需要数字部分
            result[i] = minHeap.poll()[0];
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] result = solution.topKFrequent(nums, k);

        System.out.println(Arrays.toString(result));
    }

}