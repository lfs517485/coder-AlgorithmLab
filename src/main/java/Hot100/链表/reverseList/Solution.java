package Hot100.链表.reverseList;

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){ this.val = val;}
}

public class Solution {
    public static ListNode reverseList(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;

        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
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
        if(ints.length == 0 || ints == null){
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
        ListNode head = createList(new int[]{1, 2, 3, 4, 5});
        printList(head);

        ListNode listNode = reverseList(head);
        printList(listNode);
    }

}
