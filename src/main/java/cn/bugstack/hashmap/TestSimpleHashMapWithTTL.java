package cn.bugstack.hashmap;

public class TestSimpleHashMapWithTTL {
    public static void main(String[] args) throws InterruptedException {
        // 创建TTL为3秒的HashMap，清理间隔1秒
        SimpleHashMapWithTTL<String, Integer> map = new SimpleHashMapWithTTL<>(3000, 1000);

        // 测试 put 和 get
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3, 5000); // 单独设置5秒TTL

        System.out.println("初始状态:");
        System.out.println("one: " + map.get("one"));     // 1
        System.out.println("two: " + map.get("two"));     // 2
        System.out.println("three: " + map.get("three"));
        System.out.println("size: " + map.size());        // 3

        // 等待2秒
        Thread.sleep(2000);
        System.out.println("\n2秒后:");
        System.out.println("one: " + map.get("one"));     // 1 (未过期)
        System.out.println("two: " + map.get("two"));     // 2 (未过期)
        System.out.println("three: " + map.get("three"));
        System.out.println("size: " + map.size());        // 3

        // 等待2秒（总共4秒，前两个应该过期了）
        Thread.sleep(2000);
        System.out.println("\n4秒后:");
        System.out.println("one: " + map.get("one"));     // null (已过期)
        System.out.println("two: " + map.get("two"));     // null (已过期)
        System.out.println("three: " + map.get("three")); // 3 (未过期，TTL 5秒)
        System.out.println("size: " + map.size());        // 1

        // 测试删除
        map.delete("three");
        System.out.println("\n删除three后:");
        System.out.println("three: " + map.get("three")); // null
        System.out.println("size: " + map.size());        // 0

    }
}