package Hot100.链表.hasCycle;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {this.val = x;}
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    // 创建带环的链表
    public static ListNode createCycleList(int[] values, int pos) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode cur = head;
        ListNode cycleNode = null;

        // 记录环的入口节点
        if (pos == 0) {
            cycleNode = head;
        }

        // 创建链表节点
        for (int i = 1; i < values.length; i++) {
            cur.next = new ListNode(values[i]);
            cur = cur.next;
            if (i == pos) {
                cycleNode = cur; // 记录环的入口
            }
        }

        // 如果有环，将最后一个节点连接到环入口
        if (pos >= 0 && pos < values.length) {
            cur.next = cycleNode;
        }

        return head;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head1 = createCycleList(new int[]{3, 2, 0, -4}, 1);
        System.out.println("输入: head = [3,2,0,-4], pos = 1");
        boolean result1 = solution.hasCycle(head1);
        System.out.println("输出: " + result1);
        System.out.println();
    }
}