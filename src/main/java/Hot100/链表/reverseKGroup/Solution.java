package Hot100.链表.reverseKGroup;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    /**
     * K个一组翻转链表
     * @param head 链表头节点
     * @param k 每组节点数
     * @return 翻转后的链表头节点
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 边界条件处理
        if (head == null || k <= 1) return head;

        // 虚拟头结点，方便处理头节点的翻转
        ListNode dummy = new ListNode(0, head);
        int len = getLength(head); // 获取链表总长度

        ListNode pre = dummy; // 指向当前组的前一个节点，用于连接翻转后的组
        ListNode cur = dummy.next; // 指向当前组的第一个节点

        // 每 k 个节点一组进行翻转
        while (cur != null) {
            if (len < k) break; // 剩余节点不足 k 个，直接退出循环

            // 翻转当前组的 k 个节点
            pre.next = reverseBetween(cur, 1, k);

            // 移动 pre 和 cur 到下一组的起始位置
            for (int i = 0; i < k; i++)
                pre = pre.next; // pre 移动到当前组的最后一个节点
            cur = pre.next; // cur 移动到下一组的第一个节点

            len -= k; // 更新剩余未翻转节点数量
        }

        return dummy.next; // 返回新链表的头节点
    }

    /**
     * 翻转链表的指定区间 [left, right]
     * @param head 链表头节点
     * @param left 区间左边界
     * @param right 区间右边界
     * @return 翻转区间后的链表头节点
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 虚拟头结点，方便处理头节点的翻转
        ListNode dummy = new ListNode(0, head);

        // 1. 找到四个关键节点：
        //    leftPre: left-1 位置的节点
        //    leftNode: left 位置的节点
        //    rightNode: right 位置的节点
        //    rightNext: right+1 位置的节点
        ListNode leftPre = dummy;
        for (int i = 0; i < left - 1; i++)
            leftPre = leftPre.next;

        ListNode rightNode = dummy;
        for (int i = 0; i < right; i++)
            rightNode = rightNode.next;

        ListNode leftNode = leftPre.next;
        ListNode rightNext = rightNode.next;

        // 2. 断开连接，将 [left, right] 区间从原链表分离
        leftPre.next = null;   // 断开 leftPre 与 leftNode 的连接
        rightNode.next = null; // 断开 rightNode 与 rightNext 的连接

        // 3. 翻转分离出来的 [left, right] 区间
        reverseList(leftNode);

        // 4. 重新连接翻转后的区间到原链表
        leftPre.next = rightNode; // leftPre 连接翻转后的头节点（原rightNode）
        leftNode.next = rightNext; // 翻转后的尾节点（原leftNode）连接 rightNext

        return dummy.next;
    }

    /**
     * 翻转整个链表
     * @param head 链表头节点
     * @return 翻转后的链表头节点
     */
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;    // 前驱节点
        ListNode cur = head;    // 当前节点

        while (cur != null) {
            ListNode curNext = cur.next; // 保存下一个节点
            cur.next = pre;     // 当前节点指向前驱节点
            pre = cur;          // 前驱节点后移
            cur = curNext;      // 当前节点后移
        }
        return pre; // 返回新的头节点
    }

    /**
     * 获取链表长度
     * @param head 链表头节点
     * @return 链表长度
     */
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * 创建链表（使用你提供的方法）
     * @param ints 节点值数组
     * @return 链表头节点
     */
    public static ListNode createList(int[] ints) {
        if (ints == null || ints.length == 0) {
            return null;
        }
        ListNode head = new ListNode(ints[0]);
        ListNode cur = head;
        for (int i = 1; i < ints.length; i++) {
            cur.next = new ListNode(ints[i]);
            cur = cur.next;
        }
        return head;
    }

    /**
     * 打印链表
     * @param head 链表头节点
     */
    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 主方法 - 测试用例
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        int k = 2;
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createList(arr);

        ListNode result = solution.reverseKGroup(head, k);

        printList(result);

    }
}