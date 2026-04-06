package Hot100.堆.findKthLargest;

import java.util.PriorityQueue;

class Solution {
    /**
     * 使用最小堆（Min-Heap）找到数组中第k个最大的元素。
     * 算法思路：
     * 1. 维护一个大小为k的最小堆，堆顶是堆中最小的元素。
     * 2. 遍历数组中的每个元素，将其加入堆中。
     * 3. 如果堆的大小超过k，则弹出堆顶元素（当前堆中的最小值），确保堆中始终保留最大的k个元素。
     * 4. 遍历完成后，堆顶元素即为第k个最大的元素，因为堆中保存了最大的k个元素，而堆顶是这k个元素中最小的那个。
     *
     * 时间复杂度：O(n log k)，其中n是数组长度，每次堆操作的时间复杂度为O(log k)。
     * 空间复杂度：O(k)，用于存储堆。
     */
    public int findKthLargest(int[] nums, int k) {
        // 创建一个最小堆（PriorityQueue默认是最小堆）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 遍历数组中的每个元素
        for (int num : nums) {
            // 将当前元素加入堆中
            minHeap.offer(num);

            // 如果堆的大小超过k，则弹出堆顶元素（最小值）
            // 这样可以确保堆中始终只保留最大的k个元素
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 遍历完成后，堆顶元素就是第k个最大的元素
        // 因为堆中保存了最大的k个元素，而堆顶是这些元素中最小的，即第k个最大的元素
        return minHeap.peek();
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        int result = solution.findKthLargest(nums, k);
        System.out.println(result);
    }
}