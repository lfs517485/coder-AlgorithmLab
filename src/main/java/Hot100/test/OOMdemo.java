package Hot100.test;

public class OOMdemo {
    public static void main(String[] args) {
        // 设置JVM参数: -Xms10m -Xmx10m

        // 立即分配超过堆内存的大数组
        // 20MB > 10MB堆内存，立即触发OOM
        byte[] hugeArray = new byte[20 * 1024 * 1024];
    }
}