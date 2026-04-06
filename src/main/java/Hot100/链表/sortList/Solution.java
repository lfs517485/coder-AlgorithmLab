package Hot100.链表.sortList;


class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    /**
     * 链表排序（归并排序）
     * 使用自底向上的归并排序，避免递归的栈空间开销
     */
    public ListNode sortList(ListNode head) {
        // 边界条件：空链表或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 获取链表长度
        int len = listLength(head);

        // 创建虚拟头节点，简化链表操作
        ListNode dummy = new ListNode(0, head);

        // 归并排序：子链表长度从1开始，每次翻倍
        for (int subLen = 1; subLen < len; subLen <<= 1) {
            // sortNode：已排序部分的尾节点
            ListNode sortNode = dummy;
            // cur：当前处理的未排序部分的起始节点
            ListNode cur = dummy.next;

            while (cur != null) {
                // === 步骤1：切分第一个子链表 ===
                ListNode head1 = cur;  // 第一个子链表的头节点
                for (int i = 1; i < subLen && cur != null; i++) {
                    cur = cur.next;  // 移动到第一个子链表的尾节点
                }

                // === 步骤2：切分第二个子链表 ===
                ListNode head2 = null;
                if (cur != null) {
                    head2 = cur.next;  // 第二个子链表的头节点
                    cur.next = null;   // 断开第一个子链表
                }
                cur = head2;

                // 移动cur到第二个子链表的尾节点
                for (int i = 1; i < subLen && cur != null; i++) {
                    cur = cur.next;
                }

                // === 步骤3：保存剩余部分并断开连接 ===
                ListNode nextPart = null;  // 剩余未处理部分的头节点
                if (cur != null) {
                    nextPart = cur.next;  // 保存剩余部分
                    cur.next = null;      // 断开第二个子链表
                }

                // === 步骤4：合并两个子链表 ===
                ListNode merged = mergeTwoLists(head1, head2);

                // === 步骤5：将合并后的链表连接到已排序部分 ===
                sortNode.next = merged;

                // 移动sortNode到已排序部分的末尾
                while (sortNode.next != null) {
                    sortNode = sortNode.next;
                }

                // === 步骤6：准备处理下一对子链表 ===
                cur = nextPart;
            }
        }

        return dummy.next;
    }

    private int listLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建虚拟头节点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // 合并两个有序链表
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        // 将剩余部分连接到结果链表
        cur.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }


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


    public static void printList(ListNode head) {
        ListNode cur = head;
        System.out.print("[");
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) {
                System.out.print(",");
            }
            cur = cur.next;
        }
        System.out.println("]");
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] arr = {4, 2, 1, 3};
        ListNode head = createList(arr);

        // 执行排序
        ListNode sortedHead = solution.sortList(head);

        printList(sortedHead);


    }
}