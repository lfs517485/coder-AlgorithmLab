package Hot100.链表.mergeKLists;


class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    /**
     * 合并K个升序链表的主方法
     * 使用分治策略将问题分解为合并两个链表的子问题
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 调用递归合并方法，处理整个链表数组
        return merge(lists, 0, lists.length - 1);
    }

    /**
     * 递归合并方法：使用分治策略合并链表数组中的链表
     * 将链表数组不断二分，直到只剩一个或两个链表，然后合并
     */
    private ListNode merge(ListNode[] lists, int l, int r) {
        // 递归基准条件1：当左右边界相等时，说明只有一个链表，直接返回该链表
        if (l == r) return lists[l];

        // 递归基准条件2：当左边界大于右边界时，说明没有链表，返回null
        if (l > r) return null;

        // 计算中间位置，使用位运算避免溢出且效率更高
        int mid = l + ((r - l) >> 1);

        // 分治递归：合并左半部分链表和右半部分链表
        // 1. 递归合并左半部分 [l, mid]
        // 2. 递归合并右半部分 [mid+1, r]
        // 3. 将左右两部分合并结果再次合并
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 合并两个有序链表的辅助方法
     * 使用迭代方式合并，维护一个虚拟头节点以简化操作
     */
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建虚拟头节点，简化链表操作（避免处理空链表的情况）
        ListNode dummy = new ListNode(0, null);
        // 当前指针，用于构建新链表
        ListNode cur = dummy;

        // 同时遍历两个链表，比较节点值，将较小值的节点接入新链表
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                // list1的当前节点值较小，将其接入新链表
                cur.next = list1;
                list1 = list1.next; // 移动list1指针到下一个节点
            } else {
                // list2的当前节点值较小或相等，将其接入新链表
                cur.next = list2;
                list2 = list2.next; // 移动list2指针到下一个节点
            }
            cur = cur.next; // 移动新链表的当前指针
        }

        // 处理剩余节点：将非空链表的剩余部分直接接入新链表
        // 因为输入链表都是升序的，剩余节点一定大于等于已合并的部分
        cur.next = list1 != null ? list1 : list2;

        // 返回虚拟头节点的下一个节点，即合并后链表的实际头节点
        return dummy.next;
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

        // 测试用例：lists = [[1,4,5],[1,3,4],[2,6]]

        // 创建三个链表
        ListNode list1 = createList(new int[]{1, 4, 5});
        ListNode list2 = createList(new int[]{1, 3, 4});
        ListNode list3 = createList(new int[]{2, 6});

        // 创建链表数组
        ListNode[] lists = new ListNode[]{list1, list2, list3};

        // 合并K个链表
        ListNode result = solution.mergeKLists(lists);

        printList(result);
    }
}