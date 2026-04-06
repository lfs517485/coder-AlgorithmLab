package cn.bugstack.intersectionNode;

import java.util.*;

// 链表节点定义
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    // 为了方便测试，添加一个构造函数
    ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }
}

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode LA = headA; // 定义两个指针, 分别指向AB链表的头结点
        ListNode LB = headB;

        while (LA != LB) {
            if (LA != null)
                LA = LA.next;
            else
                LA = headB; // 意味着此时走完了A链表, 换走B链表

            if(LB != null)
                LB = LB.next;
            else
                LB = headA; // 意味着此时走完了B链表, 换走A链表
        }
        // 循环结束时: LA == LB (有两种情况: 1.指向了公共结点, 2.指向了null)
        return LA;
    }

    // 创建链表的方法
    public static ListNode createLinkedList(int[] values) {
        if (values == null || values.length == 0) return null;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // 创建相交链表的方法
    public static void createIntersection(ListNode headA, ListNode headB, ListNode intersectionNode) {
        if (headA == null || headB == null) return;

        // 找到A链表的最后一个节点
        ListNode tailA = headA;
        while (tailA.next != null) {
            tailA = tailA.next;
        }

        // 找到B链表的最后一个节点
        ListNode tailB = headB;
        while (tailB.next != null) {
            tailB = tailB.next;
        }

        // 将两个链表的尾节点都指向相交节点
        tailA.next = intersectionNode;
        tailB.next = intersectionNode;
    }

    // 打印链表的方法
    public static void printList(ListNode head) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Solution solution = new Solution();

        System.out.println("=== 链表相交检测程序 ===");
        System.out.println("自动测试（预设测试用例）");
        autoTest(solution);

        scanner.close();
    }

    // 自动测试方法
    public static void autoTest(Solution solution) {
        System.out.println("\n=== 自动测试 ===");

        // 测试用例1：有相交节点
        System.out.println("测试用例1：有相交节点");
        ListNode common = createLinkedList(new int[]{8, 9, 10});
        ListNode headA1 = createLinkedList(new int[]{1, 2, 3});
        ListNode headB1 = createLinkedList(new int[]{4, 5});
        createIntersection(headA1, headB1, common);

        System.out.print("链表A: ");
        printList(headA1); // 1 -> 2 -> 3 -> 8 -> 9 -> 10
        System.out.print("链表B: ");
        printList(headB1); // 4 -> 5 -> 8 -> 9 -> 10

        ListNode result1 = solution.getIntersectionNode(headA1, headB1);
        System.out.println("相交节点值: " + (result1 != null ? result1.val : "null"));
    }
}