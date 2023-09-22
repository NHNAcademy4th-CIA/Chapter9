package org.nhnacademy.jungbum;

import java.util.LinkedList;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 이진트리 보조데이터 구조로 큐를 사용해보자.
 */
public class Quiz4 {

    private static Logger logger = LoggerFactory.getLogger(Quiz4.class);
    static TreeNodes root;

    /***
     * 트리 초기화후 트리형태로 출력.
     */
    public Quiz4() {
        treeNodesInit();
        levelOrderPrint(root);
    }

    /***
     * 트리 초기화
     */
    public void treeNodesInit() {
        String[] words = {"apple", "Banana", "Choice", "Dark", "Orange", "You", "Man", "Try", "home", "Bye", "Hello"};
        for (String word : words) {
            treeInsert(word);
        }
    }

    /***
     * 트리 출력
     * @param root 시작값
     */
    public void levelOrderPrint(TreeNodes root) {
        if (root == null) return;
        Queue<TreeNodes> queue;
        queue = new LinkedList<>();
        queue.add(root);
        while (queue.isEmpty() == false) {
            TreeNodes node = queue.poll();
            logger.info(node.getRoot());
            if (node.getLeftNode() != null) queue.add(node.getLeftNode());
            if (node.getRightNode() != null) queue.add(node.getRightNode());
        }
    }

    /***
     * 트리구조에 노드 추가
     * @param newItem 추가 값
     */
    public void treeInsert(String newItem) {
        if (root == null) {
            root = new TreeNodes(newItem);
            return;
        }
        TreeNodes runner;
        runner = root;
        while (true) {
            if (newItem.compareTo(runner.getRoot()) < 0) {
                if (runner.getLeftNode() == null) {
                    runner.setLeftNode(new TreeNodes(newItem));
                    return;
                } else runner = runner.getLeftNode();
            } else {
                if (runner.getRightNode() == null) {
                    runner.setRightNode(new TreeNodes(newItem));
                    return;
                } else runner = runner.getRightNode();
            }
        }
    }
}

/***
 * 트리 노드
 */
class TreeNodes {
    private TreeNodes leftNode;
    private String root;
    private TreeNodes rightNode;

    /***
     * 생성될때 입력
     * @param str
     */
    public TreeNodes(String str) {
        root = str;
    }

    /***
     * 왼쪽노드 블러오기
     * @return 왼쪽노드
     */
    public TreeNodes getLeftNode() {
        return leftNode;
    }

    /***
     * 왼쪽노드 설정
     * @param leftNode 왼쪽노드 입력
     */
    public void setLeftNode(TreeNodes leftNode) {
        this.leftNode = leftNode;
    }

    /***
     * 루트 노드 가져오기
     * @return 루트 노드
     */
    public String getRoot() {
        return root;
    }

    /***
     * 오른쪽 노드 가져오기
     * @return 오른쪽노드
     */
    public TreeNodes getRightNode() {
        return rightNode;
    }

    /***
     * 오른쪽 노드 설정
     * @param rightNode 오른쪽 노드 입력
     */
    public void setRightNode(TreeNodes rightNode) {
        this.rightNode = rightNode;
    }
}