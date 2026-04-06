package Hot100.堆.MedianFinder;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class MedianFinder {
    // A: 小顶堆，存储较大的一半元素（堆顶是较小的一半中的最大值）
    // B: 大顶堆，存储较小的一半元素（堆顶是较小的一半中的最大值）
    Queue<Integer> minHeap;  // 原本的A
    Queue<Integer> maxHeap;  // 原本的B

    public MedianFinder() {
        // 最小堆（默认）：存储较大的一半元素
        minHeap = new PriorityQueue<>();
        // 最大堆（自定义比较器）：存储较小的一半元素
        maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a; // 降序排列，实现大顶堆
            }
        });
    }

    public void addNum(int num) {
        // 保持两个堆的平衡：
        // 1. minHeap.size() >= maxHeap.size()
        // 2. minHeap.size() - maxHeap.size() <= 1

        if (minHeap.size() != maxHeap.size()) {
            // 当前minHeap比maxHeap多一个元素
            // 新元素先加入minHeap，然后从minHeap取出最小值加入maxHeap
            // 这样可以保证minHeap中的所有元素 >= maxHeap中的所有元素
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            // 两个堆元素数量相等
            // 新元素先加入maxHeap，然后从maxHeap取出最大值加入minHeap
            // 这样可以保证minHeap的堆顶 >= maxHeap的堆顶
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        // 如果两个堆元素数量不相等，说明总元素数为奇数，中位数为minHeap的堆顶
        // 如果相等，说明总元素数为偶数，中位数为两个堆顶的平均值
        return minHeap.size() != maxHeap.size() ?
                minHeap.peek() :
                (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

}
class Solution {
    public static void main(String[] args) {
        // 测试用例演示：["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]

        MedianFinder finder = new MedianFinder();

        finder.addNum(1);
        finder.addNum(2);
        double median1 = finder.findMedian();
        finder.addNum(3);
        double median2 = finder.findMedian();

        System.out.println(median1);
        System.out.println(median2);
    }

}