package org.nhnacademy.jungbum;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * LinkedList reverse 구현
 */
public class Quiz3 {
    private final Logger logger = LoggerFactory.getLogger(Quiz3.class);


    /***
     * 새로운 노드 4개생성후 연결
     */
    public Quiz3() {
        ListNode answer = new ListNode();
        ListNode listNode1 = new ListNode(15);
        ListNode listNode2 = new ListNode(10);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(1);

        listNode1.setNext(listNode2);
        listNode2.setNext(listNode3);
        listNode3.setNext(listNode4);

        reverse(listNode1,answer).setNext(new ListNode());
        while (answer.getListNode() != null) {
            logger.info("{}", answer.getItem());
            answer = answer.getListNode();
        }
    }

    /***
     * LinkedList를 뒤집어라.
     * @param head 연결리스트 시작
     * @return 완성된 연결리스트 + 뒤집기 시작한 연결리스트
     */
    public ListNode reverse(ListNode head,ListNode answer) {

        if (head.getListNode() == null) {
            answer = new ListNode(head.getItem());
//            logger.info("{}",head.getItem());
            return answer;
        } else {
            ListNode tmp = reverse(head.getListNode(),answer);
            tmp.setNext(head);
            return tmp.getListNode();
        }

    }
}

/***
 * ListNode
 */
class ListNode {
    private int item;       // An item in the list.
    private ListNode next;  // Pointer to the next node in the list.

    /***
     * 아이템을 넣으면서 생성
     * @param item 숫자값
     */
    public ListNode(int item) {
        this.item = item;
    }

    /***
     * 비어있는 리스트 생성
     */
    public ListNode() {
        next = null;
    }

    /***
     * 다음 노드를 연결
     * @param node 연결할 노드
     */
    public void setNext(ListNode node) {
        next = node;
    }


    /***
     * 노드 가져오기
     * @return 다음노드
     */
    public ListNode getListNode() {
        return this.next;
    }

    /***
     * 노드의 아이템 가져오기
     * @return 노드의 아이템
     */
    public int getItem() {
        return item;
    }
}