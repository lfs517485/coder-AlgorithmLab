package Hot100.链表.detectCycle;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {this.val = x;}
}

public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow)
                break;
        }
        if(fast == null || fast.next == null)
            return null;

        ListNode slowCopy = head;
        while(slow != slowCopy){
            slow = slow.next;
            slowCopy = slowCopy.next;
        }

        return slow;
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

    // 获取节点在链表中的索引位置
    public static int getNodeIndex(ListNode head, ListNode target) {
        if (head == null || target == null) {
            return -1;
        }
        ListNode cur = head;
        int index = 0;
        int maxNodes = 100; // 防止环造成死循环
        while (cur != null && index < maxNodes) {
            if (cur == target) {
                return index;
            }
            cur = cur.next;
            index++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head1 = createCycleList(new int[]{3, 2, 0, -4}, 1);

        ListNode result1 = solution.detectCycle(head1);
        if (result1 != null) {
            System.out.println("输出: 返回索引为 " + getNodeIndex(head1, result1) + " 的链表节点");
            System.out.println("解释: 链表中有一个环，其尾部连接到第二个节点（值为" + result1.val + "的节点）。");
        } else {
            System.out.println("输出: null");
        }
        System.out.println();

    }
}