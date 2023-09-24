package org.nhnacademy.jungbum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 이진 트리 증명하기
 */
public class Quiz5 {
    private static Logger logger = LoggerFactory.getLogger(Quiz5.class);

    /***
     * 1023개의 랜덥값 입력
     * countLeaves
     * sumOfLeafDepths
     * maximumLeafDepth
     * 실행
     */
    public Quiz5() {

        new TreeNode();

        for (int i = 0; i < 1023; i++)
            TreeNode.treeInsert(Math.random());

        int leafCount = TreeNode.countLeaves(TreeNode.getRoot());
        int depthSum = TreeNode.sumOfLeafDepths(TreeNode.getRoot(), 0);
        int depthMax = TreeNode.maximumLeafDepth(TreeNode.getRoot(), 0);
        double averageDepth = ((double) depthSum) / leafCount;

        logger.info("Number of leaves:         {}", leafCount);
        logger.info("Average depth of leaves:  {}", averageDepth);
        logger.info("Maximum depth of leaves:  {}", depthMax);

    }
}

/***
 * TreeNode 클래스 선언
 * 왼쪽값
 * 오른쪽 값
 * 루트 값을 가지고 있음.
 */
class TreeNode {
    private double item;
    private TreeNode left;
    private TreeNode right;

    /***
     * 생성될때 값을 가질수 잇도록.
     * @param x
     */
    public TreeNode(double x) {
        item = x;
    }

    private static TreeNode root;

    /***
     * 루트값 가져오기
     * @return 루트
     */
    public static TreeNode getRoot() {
        return root;
    }

    /***
     * 루트값 설정하기
     * @param treeNode 루트값 입력
     */
    public TreeNode() {
        root = null;
    }

    /***
     * tree에 입력
     * @param x 랜덤한 값
     */
    static void treeInsert(double x) {
        if (root == null) {

            root = new TreeNode(x);
            return;
        }
        TreeNode runner;
        runner = root;
        while (true) {
            if (x < runner.item) {

                if (runner.left == null) {
                    runner.left = new TreeNode(x);
                    return;
                } else
                    runner = runner.left;
            } else {
                if (runner.right == null) {
                    runner.right = new TreeNode(x);
                    return;
                } else
                    runner = runner.right;
            }
        }
    }

    /***
     * 잎 갯수
     * @param node 시작 노드
     * @return 갯수
     */
    static int countLeaves(TreeNode node) {
        if (node == null)
            return 0;
        else if (node.left == null && node.right == null)
            return 1;
        else
            return countLeaves(node.left) + countLeaves(node.right);
    }

    /***
     * 잎의 평균 깊이
     * @param node 루트 노드
     * @param depth 현재 깊이
     * @return 평균 깊이
     */
    static int sumOfLeafDepths(TreeNode node, int depth) {
        if (node == null) {

            return 0;
        } else if (node.left == null && node.right == null) {

            return depth;
        } else {

            return sumOfLeafDepths(node.left, depth + 1)
                    + sumOfLeafDepths(node.right, depth + 1);
        }
    }

    /***
     * 최대 깊이
     * @param node 시작노드
     * @param depth 현재 깊이
     * @return 최대 깊이
     */
    static int maximumLeafDepth(TreeNode node, int depth) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            return depth;
        } else {

            int leftMax = maximumLeafDepth(node.left, depth + 1);
            int rightMax = maximumLeafDepth(node.right, depth + 1);
            if (leftMax > rightMax)
                return leftMax;
            else
                return rightMax;
        }
    }
}
