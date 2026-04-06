package Hot100.链表.swapPairs;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);

        ListNode preCur = dummy;
        ListNode cur1 = null;
        ListNode cur2 = null;
        ListNode curNext = null;

        while(preCur.next != null && preCur.next.next != null){
            cur1 = preCur.next;
            cur2 = cur1.next;
            curNext = cur2.next;

            cur2.next = cur1;
            cur1.next = curNext;
            preCur.next = cur2;

            preCur = cur1;
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

        // 测试用例1: [1,2,3,4] → 两两交换 → [2,1,4,3]
        ListNode head1 = createList(new int[]{1, 2, 3, 4});
        System.out.print("原链表: ");
        printList(head1);

        ListNode result1 = swapPairs(head1);
        System.out.print("两两交换后: ");
        printList(result1);
    }
}