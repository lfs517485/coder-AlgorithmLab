package Hot100.链表.mergeTwoLists;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0, null);
        ListNode cur = dummy;

        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        if(list1 != null)
            cur.next = list1;
        if(list2 != null)
            cur.next = list2;

        return dummy.next;
    }

    // 辅助方法：创建链表
    public static ListNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode head = new ListNode(values[0]);
        ListNode cur = head;
        for (int i = 1; i < values.length; i++) {
            cur.next = new ListNode(values[i]);
            cur = cur.next;
        }
        return head;
    }

    // 辅助方法：打印链表
    public static void printList(ListNode head) {
        ListNode cur = head;
        System.out.print("[");
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) {
                System.out.print(" -> ");
            }
            cur = cur.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println("=== 测试1: 两个有序链表合并 ===");
        // list1: 1 -> 2 -> 4
        // list2: 1 -> 3 -> 4
        // 合并后: 1 -> 1 -> 2 -> 3 -> 4 -> 4
        ListNode list1 = createList(new int[]{1, 2, 4});
        ListNode list2 = createList(new int[]{1, 3, 4});
        System.out.print("输入: list1 = ");
        printList(list1);
        System.out.print("       list2 = ");
        printList(list2);

        ListNode merged1 = solution.mergeTwoLists(list1, list2);
        System.out.print("输出: ");
        printList(merged1);
        System.out.println("解释: 合并后的链表为 [1 -> 1 -> 2 -> 3 -> 4 -> 4]");
        System.out.println();
    }
}