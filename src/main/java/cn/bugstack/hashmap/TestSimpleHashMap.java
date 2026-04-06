package cn.bugstack.hashmap;

public class TestSimpleHashMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new SimpleHashMap<>();

        // 测试 put
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // 测试 get
        System.out.println("one: " + map.get("one"));     // 1
        System.out.println("two: " + map.get("two"));     // 2

        // 测试更新
        map.put("two", 22);
        System.out.println("two after update: " + map.get("two")); // 22

        // 测试 delete
        map.delete("one");
        System.out.println("one after delete: " + map.get("one")); // null

        // 测试 null key
        map.put(null, 999);
        System.out.println("null key: " + map.get(null)); // 999
    }
}