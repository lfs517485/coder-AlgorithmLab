package Hot100.链表.isPalindrome;

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){this.val = val;}
}

public class Solution {
    public static boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head);

        ListNode reverseHead = reverseList(mid);

        while(head != null && reverseHead != null){
            if(head.val != reverseHead.val){
                return false;
            }
            head = head.next;
            reverseHead = reverseHead.next;
        }
        return true;
    }

    public static ListNode middleNode(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

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
        ListNode head = createList(new int[]{1, 2, 2, 1});
        printList(head);
        boolean flag = isPalindrome(head);
        System.out.println(flag);
    }
}