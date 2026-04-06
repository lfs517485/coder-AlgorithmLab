package cn.bugstack.hashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 带TTL的极简 HashMap 实现
 * 数组+链表 + 过期时间 + 定期清理
 */
public class SimpleHashMapWithTTL<K, V> implements Map<K, V> {

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 16;
    // 默认清理间隔（毫秒）
    private static final long DEFAULT_CLEAN_INTERVAL = 1000;

    // 节点类 - 增加过期时间字段
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        long expireTime; // 过期时间戳

        Node(K key, V value, Node<K, V> next, long ttl) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.expireTime = System.currentTimeMillis() + ttl;
        }

        // 检查是否过期
        boolean isExpired() {
            return System.currentTimeMillis() > expireTime;
        }
    }

    // 哈希表数组
    private Node<K, V>[] table;
    // 当前元素数量
    private int size;
    // 默认TTL时间（毫秒）
    private final long defaultTTL;
    // 清理线程
    private final ScheduledExecutorService cleaner;

    // 构造器
    public SimpleHashMapWithTTL() {
        this(60000, DEFAULT_CLEAN_INTERVAL); // 默认TTL 60秒
    }

    public SimpleHashMapWithTTL(long defaultTTL, long cleanInterval) {
        this.defaultTTL = defaultTTL;
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];

        // 启动定期清理任务
        cleaner = Executors.newSingleThreadScheduledExecutor();
        cleaner.scheduleAtFixedRate(this::cleanExpiredEntries,
                cleanInterval, cleanInterval, TimeUnit.MILLISECONDS);
    }

    // 计算哈希值
    private int hash(K key) {
        return key == null ? 0 : key.hashCode() & (table.length - 1);
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        Node<K, V> prev = null;

        // 遍历链表查找key
        while (node != null) {
            // 如果节点过期，删除它
            if (node.isExpired()) {
                removeNode(index, prev, node);
                node = (prev == null) ? table[index] : prev.next;
                continue;
            }

            if (key == null ? node.key == null : key.equals(node.key)) {
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        return put(key, value, defaultTTL);
    }

    // 支持自定义TTL的put方法
    public V put(K key, V value, long ttl) {
        int index = hash(key);
        Node<K, V> node = table[index];
        Node<K, V> prev = null;

        // 遍历链表，检查key是否已存在，同时清理过期节点
        while (node != null) {
            // 清理过期节点
            if (node.isExpired()) {
                removeNode(index, prev, node);
                node = (prev == null) ? table[index] : prev.next;
                continue;
            }

            if (key == null ? node.key == null : key.equals(node.key)) {
                // key已存在，更新值和过期时间
                V oldValue = node.value;
                node.value = value;
                node.expireTime = System.currentTimeMillis() + ttl;
                return oldValue;
            }
            prev = node;
            node = node.next;
        }

        // key不存在，创建新节点并添加到链表头部
        Node<K, V> newNode = new Node<>(key, value, table[index], ttl);
        table[index] = newNode;
        size++;

        return null;
    }

    @Override
    public V delete(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        Node<K, V> prev = null;

        // 遍历链表查找要删除的节点
        while (node != null) {
            // 清理过期节点
            if (node.isExpired()) {
                removeNode(index, prev, node);
                node = (prev == null) ? table[index] : prev.next;
                continue;
            }

            if (key == null ? node.key == null : key.equals(node.key)) {
                // 找到要删除的节点
                V oldValue = node.value;
                removeNode(index, prev, node);
                return oldValue;
            }
            prev = node;
            node = node.next;
        }

        return null;
    }

    // 移除节点的辅助方法
    private void removeNode(int index, Node<K, V> prev, Node<K, V> node) {
        if (prev == null) {
            // 删除的是链表头节点
            table[index] = node.next;
        } else {
            // 删除的是链表中间节点
            prev.next = node.next;
        }
        size--;
    }

    // 定期清理所有过期条目
    private void cleanExpiredEntries() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            Node<K, V> prev = null;

            while (node != null) {
                if (node.isExpired()) {
                    removeNode(i, prev, node);
                    node = (prev == null) ? table[i] : prev.next;
                } else {
                    prev = node;
                    node = node.next;
                }
            }
        }
    }

    // 获取当前大小（包含未过期的元素）
    public int size() {
        return size;
    }

}