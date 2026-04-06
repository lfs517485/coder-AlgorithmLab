package Hot100.链表.deleteDuplicates;


class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;//为空的情况

        ListNode slow = head, fast = head;//快慢指针
        while(fast != null){
            if(slow.val != fast.val){
                slow.next = fast;//nums[slow] = nums[fast];
                slow = slow.next;//slow++;
            }
            fast = fast.next;//不管如何, 快指针都是要走一步的
        }
        slow.next = null;
        return head;
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

        // 测试用例1: [1,1,2]
        ListNode head1 = createList(new int[]{1, 1, 2});

        ListNode result1 = solution.deleteDuplicates(head1);
        printList(result1);
    }
}
