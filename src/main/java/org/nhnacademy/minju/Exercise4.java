package org.nhnacademy.minju;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .이진트리의 항목을 인쇄
 * 스택이나 큐를 사용할 경우 비재귀 서브루틴을 사용하여 인쇄할 수 있다
 * TreeNodes
 */
public class Exercise4 {


    private static final Logger logger = LoggerFactory.getLogger(Exercise4.class);

    /**
     * .add root node to empty queue
     * get node from the queue
     * print the item in the node
     * treeNode have left, right
     */
    public static void exercise4() {
        Scanner scanner = new Scanner(System.in);
        TreeNode treeNode = new TreeNode();
        String input;

        logger.info("Enter items, or Enter Empty Line to Finish Input : ");
        while (!(input = scanner.nextLine()).isEmpty()) {
            treeNode.insertItem(input);
        }
        treeNode.inorderTraversal();
    }
}

class TreeNode {
    private static final Logger logger = LoggerFactory.getLogger(Exercise4.class);

    String item;
    TreeNode left;
    TreeNode right;

    public TreeNode(String newItem) {
        item = newItem;
    }

    public TreeNode() {

    }

    private TreeNode root;

    public void insertItem(String newItem) {
        if (root == null) {
            root = new TreeNode(newItem);
            return;
        }
        // not empty
        TreeNode runner = root;
        while (true) {
            if (newItem.compareTo(runner.item) < 0) { // string
                if (runner.left == null) {
                    runner.left = new TreeNode(newItem);
                    return;
                }
                runner = runner.left;
            } else {
                if (runner.right == null) {
                    runner.right = new TreeNode(newItem);
                    return;
                }
                runner = runner.right;
            }
        }
    }

    void inorderTraversal() {
        if (root == null) {
            return;
        }
        Queue queue = new Queue();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.dequeue();
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            logger.info("{}", node.item);
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }

}

class Queue {
    private static class Node {
        TreeNode item;
        Node next;
    }

    private Node head = null;
    private Node tail = null;

    /**
     * queue에 추가
     */
    void enqueue(TreeNode tree) {
        Node node = new Node();
        node.item = tree;
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    /**
     * queue의 첫 번째 아이템을 리턴
     * 큐가 비어있으면 throw Exception
     */
    TreeNode dequeue() {
        if (head == null) {
            throw new IllegalStateException("can't dequeue from empty queue");
        }

        TreeNode first = head.item;
        head = head.next; // 2->1
        if (head == null) {
            // empty
            tail = null;
        }
        return first;
    }

    boolean isEmpty() {
        return (head == null);
    }
}