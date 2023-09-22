package org.nhnacademy.minju;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .copy a list
 * with the order of the items of the list reversed
 */
public class Exercise3 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise3.class);

    private static class ListNode {
        private int item;
        private ListNode next;
        private int count;

        ListNode(int num) {
            this.item = num;
        }


        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode getNext() {
            return next;
        }

        public int getItem() {
            return item;
        }

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }

    public static void exercise3() {
        ListNode head = addItem(1, 2, 3, 4, 5);
        print(head);
        logger.info("reversed = ");
        ListNode reversed = copyListNode(head);
        print(reversed);
    }

    private static ListNode addItem(int... numbers) {
        ListNode head = null;
        ListNode start = null;

        for (int n : numbers) {
            if (head == null) {
                head = new ListNode(n);
                start = head;
                start.addCount();
                continue;
            }
            while (head.getNext() != null) {
                head = head.getNext();
            }
            head.setNext(new ListNode(n));
            start.addCount();
        }
        return start;
    }

    /**
     * @param head 복사할 리스트 노드
     * @return 복사한 노드
     */
    private static ListNode copyListNode(ListNode head) {
        int count = head.getCount();
        ListNode copy = null;
        ListNode p = null;

        for (int i = count; i > 0; i--) {
            if (copy == null) {
                copy = jump(head, i);
                p = copy; // 가장 처음 노드
                continue;
            }
            while (copy.getNext() != null) {
                copy = copy.getNext();
            }
            copy.setNext(jump(head, i));
        }
        return p;
    }

    /**
     * @param head  기존 리스트노드
     * @param index reverse로 가져올 인덱스
     * @return head의 아이템을 가진 노드
     */
    private static ListNode jump(ListNode head, int index) {
        for (int i = 0; i < index - 1; i++) {
            head = head.getNext();
        }
        return new ListNode(head.getItem());
    }

    private static void print(ListNode reversed) {
        ListNode printer = reversed;
        while (printer != null) {
            logger.info("{}", printer.getItem());
            printer = printer.getNext();
        }
    }
}
