package cn.bugstack.hashmap;

/**
 * 极简 Map 接口
 * 只包含最核心的三个方法
 */
public interface Map<K, V> {
    V get(K key);
    V put(K key, V value);
    V delete(K key);
}