package Hot100.链表.removeNthFromEnd;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode l = dummy;
        ListNode r = dummy;

        for(int i = 0; i < n; i++){
            r = r.next;
        }

        while(r.next != null){
            r = r.next;
            l = l.next;
        }

        l.next = l.next.next;

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

        // 测试用例: [1,2,3,4,5], n=2 → 删除倒数第2个节点(4)
        ListNode head = createList(new int[]{1, 2, 3, 4, 5});

        ListNode result = solution.removeNthFromEnd(head, 2);
        printList(result);
    }
}
