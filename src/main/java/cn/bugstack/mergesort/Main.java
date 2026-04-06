package cn.bugstack.mergesort;

/**
 * Definition for singly-linked list.
 */
// ACM 模式测试类
public class Main {
    public static void main(String[] args) {
        // 测试用例6: 包含重复元素
        System.out.println("测试用例6 - 包含重复元素:");
        ListNode head6 = new ListNode(3,
                new ListNode(2,
                        new ListNode(4,
                                new ListNode(2,
                                        new ListNode(1)))));
        printList(new Solution().sortList(head6));
    }

    // 辅助方法：打印链表
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        int len = listLength(head);
        ListNode dummy = new ListNode(0, head);// 虚拟头结点

        // 归并排序--->从小到大(遍历log(n)次)
        for (int subLen = 1; subLen < len; subLen <<= 1) {
            ListNode sortNode = dummy;// 有序部分虚拟头结点
            ListNode cur = sortNode.next;

            while (cur != null) {
                // 第一个长度为sublen的子链表头节点指针
                ListNode head_1 = cur;
                for (int i = 1; i < subLen && cur != null; i++) cur = cur.next;// 当前cur指向:第一个链表尾节点


                // 第二个长度为sublen的子链表头节点指针
                ListNode head_2 = null;
                if (cur != null) {
                    head_2 = cur.next;
                    cur.next = null;// 断开
                }
                cur = head_2;
                for (int i = 1; i < subLen && cur != null; i++) cur = cur.next;// 当前cur指向:第二个链表尾节点


                ListNode Next = null;// 剩余部分的链表头节点指针
                if (cur != null) {
                    Next = cur.next;
                    cur.next = null;// 断开
                }

                sortNode.next = mergeTwoLists(head_1, head_2);//合并前两段
                while (sortNode.next != null)//移动到有序末尾
                    sortNode = sortNode.next;
                //处理剩余无序部分
                cur = Next;
            }
        }

        return dummy.next;
    }

    private int listLength(ListNode head) {
        int length = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            length++;
        }

        return length;
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0, null);
        ListNode cur = dummy;

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

        cur.next = list1 != null ? list1 : list2;

        return dummy.next;
    }
}