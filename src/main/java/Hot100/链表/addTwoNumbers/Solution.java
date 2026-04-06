package Hot100.链表.addTwoNumbers;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, null);
        ListNode cur = dummy;
        int res = 0;

        while(l1 != null || l2 != null){
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            ListNode newNode = new ListNode((val1 + val2 + res)%10, null);
            cur.next = newNode;
            cur = cur.next;
            res = val1 + val2 + res >= 10 ? 1 : 0;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(res != 0){
            ListNode newNode = new ListNode(res, null);
            cur.next = newNode;
            cur = cur.next;
        }

        return dummy.next;
    }

    public static void printList(ListNode head){
        ListNode cur = head;
        while(cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static ListNode createList(int[] ints) {
        if(ints == null || ints.length == 0){
            return null;
        }
        ListNode head = new ListNode(ints[0]);
        ListNode cur = head;
        for(int i = 1; i < ints.length; i++){
            cur.next = new ListNode(ints[i]);
            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试1: 342 + 465 = 807
        ListNode l1 = createList(new int[]{2, 4, 3});
        ListNode l2 = createList(new int[]{5, 6, 4});
        ListNode result1 = solution.addTwoNumbers(l1, l2);

        System.out.print("测试1 (342 + 465 = 807): ");
        printList(result1);
    }
}
