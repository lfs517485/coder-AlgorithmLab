package Hot100.链表.LRUCache;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 双向链表节点类
 */
class DLinkedNode {
    int key;          // 键，用于在哈希表中快速查找
    int value;        // 值，实际存储的数据
    DLinkedNode pre;  // 前驱指针，指向前一个节点
    DLinkedNode next; // 后继指针，指向后一个节点

    // 无参构造器（用于创建虚拟头尾节点）
    public DLinkedNode() {}

    // 带参构造器（用于创建实际存储数据的节点）
    public DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * LRU缓存实现类
 * 使用双向链表维护访问顺序 + 哈希表实现O(1)访问
 * LRU (Least Recently Used) 缓存淘汰策略：当缓存空间不足时，淘汰最久未使用的数据
 */
class LRUCache {
    private int size;                    // 当前缓存中实际存储的元素数量
    private int capacity;                // 缓存容量上限
    private final DLinkedNode head;
    private final DLinkedNode tail;      // 双向链表的虚拟头节点和虚拟尾节点
    private final Map<Integer, DLinkedNode> cache; // 哈希表，用于O(1)时间访问节点

    /**
     * 构造函数：初始化LRU缓存
     */
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // 初始化虚拟头节点和尾节点
        head = new DLinkedNode();
        tail = new DLinkedNode();

        // 连接虚拟头尾节点，形成空双向链表
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 获取缓存值
     * 如果键存在，将对应节点移动到链表头部（标记为最近使用）并返回值
     * 如果键不存在，返回-1
     */
    public int get(int key) {
        DLinkedNode node = cache.get(key);

        // 键不存在的情况
        if (node == null) {
            return -1;
        }

        // 键存在：将节点移动到链表头部（标记为最近使用）
        moveToHead(node);

        // 返回节点的值
        return node.value;
    }

    /**
     * 添加或更新缓存
     * 如果键不存在，创建新节点并添加到链表头部，如果超出容量则淘汰最久未使用的节点
     * 如果键已存在，更新节点的值并将其移动到链表头部
     */
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            // 键不存在：创建新节点
            DLinkedNode newNode = new DLinkedNode(key, value);

            // 将新节点添加到哈希表和链表头部
            cache.put(key, newNode);
            addToHead(newNode);

            // 更新缓存大小
            this.size++;

            // 如果超出容量，需要淘汰最久未使用的节点（链表尾部节点）
            if (this.size > capacity) {
                // 移除链表尾部节点
                DLinkedNode tailNode = removeTail();
                // 同时从哈希表中移除对应的键
                cache.remove(tailNode.key);
                // 更新缓存大小
                this.size--;
            }
        } else {
            // 键已存在：更新节点的值
            node.value = value;
            // 将节点移动到链表头部（标记为最近使用）
            moveToHead(node);
        }
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);  // 从当前位置移除节点
        addToHead(node);   // 将节点添加到链表头部
    }

    private DLinkedNode removeTail() {
        // 尾部节点是虚拟尾节点的前一个节点
        DLinkedNode tailNode = tail.pre;
        removeNode(tailNode);
        return tailNode;
    }

    private void removeNode(DLinkedNode node) {
        // 更新前驱节点的后继指针
        node.pre.next = node.next;
        // 更新后继节点的前驱指针
        node.next.pre = node.pre;
    }

    private void addToHead(DLinkedNode node) {
        // 设置新节点的前驱和后继
        node.pre = head;
        node.next = head.next;

        // 更新原有节点的指针
        head.next.pre = node;
        head.next = node;
    }
}

public class Solution {
    public static void main(String[] args) {
        // 测试用例输入：["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
        // 对应操作：容量2，put(1,1)，put(2,2)，get(1)，put(3,3)，get(2)，put(4,4)，get(1)，get(3)，get(4)

        // 1. 创建容量为2的LRU缓存
        LRUCache lruCache = new LRUCache(2);
        // 2. put(1, 1)
        lruCache.put(1, 1);
        // 3. put(2, 2)
        lruCache.put(2, 2);
        // 4. get(1) - 应该返回1
        int result1 = lruCache.get(1);
        // 5. put(3, 3) - 这会淘汰键2（最久未使用）
        lruCache.put(3, 3);
        // 6. get(2) - 应该返回-1（已被淘汰）
        int result2 = lruCache.get(2);
        // 7. put(4, 4) - 这会淘汰键1（最久未使用）
        lruCache.put(4, 4);
        // 8. get(1) - 应该返回-1（已被淘汰）
        int result3 = lruCache.get(1);
        // 9. get(3) - 应该返回3
        int result4 = lruCache.get(3);
        // 10. get(4) - 应该返回4
        int result5 = lruCache.get(4);

        // 验证输出
        int[] expected = {1, -1, -1, 3, 4};
        int[] actual = {result1, result2, result3, result4, result5};

        System.out.println(Arrays.toString(actual));

    }
}