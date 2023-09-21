package org.nhnacademy.lsj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem3 {


    private static final Logger logger = LoggerFactory.getLogger(Problem3.class);

    public static void problem3() {


        ListNode listNode = new ListNode(0);


        for (int i = 1; i <= 10; i++) {
            addNode(listNode, new ListNode(i));
        }

        printListNode(listNode);

        System.out.println(listNode.getCount());

        ListNode answer = reverseListNode(listNode);

        printListNode(answer);


    }

    public static ListNode reverseListNode(ListNode listNode) {

        ListNode answer = null;

        int index = listNode.getCount();

        for (int i = index; i >= 0; i--) {

            if (answer == null) {
                answer = getNodeByIndex(listNode, i);
                answer.addCount();
                continue;
            }

            addNode(answer, getNodeByIndex(listNode, i));
        }
        return answer;

    }

    public static ListNode getNodeByIndex(ListNode listNode, int index) {

        for (int i = 0; i < index; i++) {
            listNode = listNode.getNext();
        }
        return new ListNode(listNode.getItem());
    }

    public static void addNode(ListNode listNode, ListNode node) {

        listNode.addCount();
        while (listNode.getNext() != null) {
            listNode = listNode.getNext();
        }
        listNode.setNext(node);
    }

    public static void printListNode(ListNode listNode) {

        while (listNode != null) {
            logger.info("{}", listNode.getItem());
            listNode = listNode.getNext();
        }

    }

}

class ListNode {

    private int item;
    private int count;
    private ListNode next;

    public ListNode(int item) {
        this.item = item;
        this.next = null;
    }

    public int getCount() {
        return count;
    }

    public int getItem() {
        return item;
    }

    public ListNode getNext() {
        return this.next;
    }

    public void setNext(ListNode listNode) {
        this.next = listNode;
    }

    public void addCount() {
        this.count++;
    }


}