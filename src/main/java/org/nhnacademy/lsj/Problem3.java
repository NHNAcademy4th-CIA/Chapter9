package org.nhnacademy.lsj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 연결리스트를 만든 후 그 리스트의 reverse를 저장하는 프로그램.
 */
public class Problem3 {


    private static final Logger logger = LoggerFactory.getLogger(Problem3.class);

    /**
     * addNode를 통해서 리스트의 끝에 원소 넣어줌.
     * reverseListNode 메서드를 통해 reverseList 만듦.
     * reverseListNode 메서드는 내부적으로 getNodeByIndex 메서드 사용하는데.
     * 이는 List의 특정 index를 return 하는 메서드 .
     * 이를통해서 순차적으로 끝에서부터 처음 head까지 넣어준다.
     */
    public static void problem3() {


        // ListNode listNode = new ListNode(0);


//        for (int i = 1; i <= 10; i++) {
//            addNode(listNode, new ListNode(i));
//        }

        ListNode listNode = addAllNode(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        printListNode(listNode);

        ListNode answer = reverseListNode(listNode);


        printListNode(answer);


    }

    /**
     * 역방향 List만드는 메서드 .
     * getNodeByIndex를 통해서 맨 끝부터 , 맨 처음 head까지 넣어줌.
     *
     * @param listNode reverseList를 만들 기준이 되는 기존 list.
     * @return reverseList.
     */
    public static ListNode reverseListNode(ListNode listNode) {

        ListNode answer = null;

        int index = listNode.getListSize();

        for (int i = index; i > 0; i--) {

            if (answer == null) {
                answer = getNodeByIndex(listNode, i);
                continue;
            }

            addNode(answer, getNodeByIndex(listNode, i));
        }

        return answer;

    }

    /**
     * List의 index번째 node를 반환함. get메서드와 같음.
     *
     * @param listNode 기준이되는 list.
     * @param index    반환할 index.
     * @return index번째 list .
     */
    public static ListNode getNodeByIndex(ListNode listNode, int index) {

        // 이거 index가 음수인 경우에는 에러 터쳐줘야 함

        if (index < 0) {
            throw new IllegalArgumentException("0보다 작은 index는 올 수 없습니다.");
        }


        for (int i = 0; i < index - 1; i++) {
            listNode = listNode.getNext();
        }

        return new ListNode(listNode.getItem());

    }

    /**
     * List에 node를 추가하는 메서드 , next가 더 존재하지 않을때 까지 가서 추가된다.
     *
     * @param listNode 추가될 List.
     * @param node     추가될 노드.
     */
    public static void addNode(ListNode listNode, ListNode node) {

        while (listNode.getNext() != null) {
            listNode = listNode.getNext();
        }
        listNode.setNext(node);
    }

    /**
     * List를 출력한다.
     * List의 next가 존재하지 않을 때 까지 next로 보냄.
     *
     * @param listNode 출력할 List.
     */
    public static void printListNode(ListNode listNode) {

        while (listNode != null) {
            logger.info("{}", listNode.getItem());
            listNode = listNode.getNext();
        }

    }


    public static ListNode addAllNode(int... num) {

        ListNode head = null;
        ListNode start = null;

        for (int i = 0; i < num.length; i++) {

            if (head == null) {
                head = new ListNode(num[i]);
                start = head;
                continue;
            }

            while (head.getNext() != null) {
                head = head.getNext();
            }

            head.setNext(new ListNode(num[i]));

        }
        return start;

    }


}


class ListNode {

    private int item;
    private ListNode next;

    public ListNode(int item) {
        this.item = item;
        this.next = null;
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

    public int getListSize() {

        int count = 0;

        ListNode listNode = this;

        while (listNode != null) {
            count++;
            listNode = listNode.getNext();
        }
        return count;
    }


}