package Hot100.链表.getIntersectionNode;

class ListNode {
   int val;
   ListNode next;
   ListNode(int x) {val = x; next = null;}
}

public class Solution {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode LA = headA;
        ListNode LB = headB;

        while(LA != LB){
            if(LA != null)
                LA = LA.next;
            else
                LA = headB;

            if(LB != null)
                LB = LB.next;
            else
                LB = headA;
        }

        return LA;
    }

    public static void main(String[] args) {
// 根据题目输入创建链表
        // 先创建相交部分节点
        ListNode commonNode1 = new ListNode(8);
        ListNode commonNode2 = new ListNode(4);
        ListNode commonNode3 = new ListNode(5);
        commonNode1.next = commonNode2;
        commonNode2.next = commonNode3;

        // 创建链表A: [4,1,8,4,5]
        ListNode headA = new ListNode(4);
        ListNode a1 = new ListNode(1);
        headA.next = a1;
        a1.next = commonNode1;  // 这里连接到公共部分

        // 创建链表B: [5,6,1,8,4,5]
        ListNode headB = new ListNode(5);
        ListNode b1 = new ListNode(6);
        ListNode b2 = new ListNode(1);
        headB.next = b1;
        b1.next = b2;
        b2.next = commonNode1;  // 这里连接到公共部分

        // 调用方法找到相交节点
        ListNode intersection = getIntersectionNode(headA, headB);
        if(intersection != null)
            System.out.println(intersection.val);
        else
            System.out.println("null");
    }
}