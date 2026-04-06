package cn.bugstack.hashmap;

/**
 * 极简 HashMap 实现
 * 只实现数组+链表、get、put、delete 基本功能
 */
public class SimpleHashMap<K, V> implements Map<K, V> {

    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 16;

    // 节点类 - 简单的链表节点
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // 哈希表数组
    private Node<K, V>[] table;
    // 当前元素数量
    private int size;

    // 构造器
    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
    }

    // 计算哈希值 - 简化版
    private int hash(K key) {
        return key == null ? 0 : key.hashCode() & (table.length - 1);
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];

        // 遍历链表查找key
        while (node != null) {
            if (key == null ? node.key == null : key.equals(node.key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key);
        Node<K, V> node = table[index];

        // 遍历链表，检查key是否已存在
        while (node != null) {
            if (key == null ? node.key == null : key.equals(node.key)) {
                // key已存在，更新值
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.next;
        }

        // key不存在，创建新节点并添加到链表头部
        Node<K, V> newNode = new Node<>(key, value, table[index]);
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
            if (key == null ? node.key == null : key.equals(node.key)) {
                // 找到要删除的节点
                V oldValue = node.value;

                if (prev == null) {
                    // 删除的是链表头节点
                    table[index] = node.next;
                } else {
                    // 删除的是链表中间节点
                    prev.next = node.next;
                }

                size--;
                return oldValue;
            }
            prev = node;
            node = node.next;
        }

        return null;
    }

}