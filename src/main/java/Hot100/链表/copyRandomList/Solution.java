package Hot100.链表.copyRandomList;

import java.util.HashMap;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        // 边界条件：空链表直接返回null
        if (head == null) {
            return null;
        }

        // 哈希表：存储原节点和拷贝节点的映射关系
        HashMap<Node, Node> map = new HashMap<>();

        // 第一阶段：创建所有节点的拷贝，存入哈希表
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));  // 创建新节点（只复制值）
            cur = cur.next;
        }

        // 第二阶段：设置拷贝节点的next和random指针
        cur = head;
        while (cur != null) {
            Node copyNode = map.get(cur);  // 获取当前节点的拷贝

            // 设置next指针（如果原节点的next为null，map.get返回null）
            copyNode.next = map.get(cur.next);

            // 设置random指针（如果原节点的random为null，map.get返回null）
            copyNode.random = map.get(cur.random);

            cur = cur.next;
        }

        // 返回拷贝链表的头节点
        return map.get(head);
    }


    public static void printList(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print("Node(" + cur.val + ")");
            if (cur.random != null) {
                System.out.print("[random->" + cur.random.val + "]");
            } else {
                System.out.print("[random->null]");
            }
            System.out.print(" -> ");
            cur = cur.next;
        }
        System.out.println("null");
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        Node node0 = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        // 设置next指针
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // 设置random指针
        node1.random = node0;     // [13,0]
        node2.random = node4;     // [11,4]
        node3.random = node2;     // [10,2]
        node4.random = node0;     // [1,0]

        System.out.println("原始链表：");
        printList(node0);

        // 执行深拷贝
        Node copyHead = solution.copyRandomList(node0);

        System.out.println("拷贝链表：");
        printList(copyHead);

    }
}