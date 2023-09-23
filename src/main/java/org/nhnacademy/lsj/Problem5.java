package org.nhnacademy.lsj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 이진트리는 어느정도 벨런스가 잡혀있음.
 * 이전에 만들어 놧던 이진 트리를 이용해서.
 * 1023개의 노드가 있는 트리를 만듦.
 * 그리고 모든 리프노드의 평균 깊이 , 가장 큰 리프노드 깊이 , 리프노드의 개수 를 찾음.
 */
public class Problem5 {

    private static final Logger logger = LoggerFactory.getLogger(Problem5.class);

    /**
     * 이진정렬트리 만듦 -> 값은 랜덤으로 1~300사이의 실수로 넣어줄꺼야.
     * 이거 자동정렬됨 , 구현은 Problem2와 같음.
     * 이제 tree 순회하면서 leafNode 개수 체크하고 , 더하고 , Max값 찾을꺼임.
     */
    public static void problem5() {


        BinarySearchTreeNumber bst = new BinarySearchTreeNumber(150);


        for (int i = 1; i < 1023; i++) {
            double num = Math.random() * 300 + 1;
            bst.add(bst, (Math.random() * 300 + 1));
        }

        BinarySearchTreeNumber.printPreOrderNumber(bst);


        logger.info("leaf depth 합 {}", BinarySearchTreeNumber.sumLeafNodeDepth(bst, 0));

        logger.info("leaf Average depth {}", BinarySearchTreeNumber.sumLeafNodeDepth(bst, 0)
                / BinarySearchTreeNumber.countLeafNode(bst, 1));

        logger.info("leaf Max Depth  {}", BinarySearchTreeNumber.getMaxDepth(bst, 0));

        logger.info("leaf Node 개수 {}", BinarySearchTreeNumber.countLeafNode(bst, 1));

        SearchTree sumLeafDepth = new SearchTree() {
            @Override
            public double searchTree(BinarySearchTreeNumber bst, double number) {
                return BinarySearchTreeNumber.sumLeafNodeDepth(bst, number);
            }
        };

        SearchTree countLeafNode = new SearchTree() {
            @Override
            public double searchTree(BinarySearchTreeNumber bst, double number) {
                return BinarySearchTreeNumber.countLeafNode(bst, number);
            }
        };

        SearchTree maxLeafDepth = new SearchTree() {
            @Override
            public double searchTree(BinarySearchTreeNumber bst, double number) {
                return BinarySearchTreeNumber.getMaxDepth(bst,number);
            }
        };


        logger.info("{}", BinarySearchTreeNumber.calcLeaf(sumLeafDepth, bst, 0));
        logger.info("{}", BinarySearchTreeNumber.calcLeaf(countLeafNode, bst, 1));
        logger.info("{}", BinarySearchTreeNumber.calcLeaf(maxLeafDepth, bst, 0));


    }

}


class BinarySearchTreeNumber {

    private static final Logger logger = LoggerFactory.getLogger(BinarySearchTree.class);

    private BinarySearchTreeNumber left;

    private BinarySearchTreeNumber right;

    private double value;

    public double getValue() {
        return value;
    }


    public void setLeft(BinarySearchTreeNumber left) {
        this.left = left;
    }

    public void setRight(BinarySearchTreeNumber right) {
        this.right = right;
    }

    public BinarySearchTreeNumber getLeft() {
        return left;
    }

    public BinarySearchTreeNumber getRight() {
        return right;
    }


    public BinarySearchTreeNumber(double number) {
        this.value = number;
        this.left = null;
        this.right = null;
    }

    /**
     * value가 왔을때.
     * root 보다 작으면 ==  left로
     * root 보다 크면 == right로
     *
     * @param bst   이진정렬트리 .
     * @param value add할 value.
     */
    public void add(BinarySearchTreeNumber bst, double value) {

        double temp = bst.getValue();


        if (temp - value > 0) { // 내가 더 커 -> 그럼 left에
            if (bst.getLeft() != null) {
                add(bst.getLeft(), value);

            } else {
                bst.setLeft(new BinarySearchTreeNumber(value));
            }
        } else if (temp - value < 0) { // 내가 더 작아 -> 그럼 right로

            if (bst.getRight() != null) {
                add(bst.getRight(), value);
            } else {
                bst.setRight(new BinarySearchTreeNumber(value));

            }

        }

    }


    /**
     * preOrder 순회 .
     *
     * @param bst 이진정렬트리.
     */
    public static void printPreOrderNumber(BinarySearchTreeNumber bst) {

        if (bst == null) {
            return;
        }
        logger.info("{}", bst.value);

        printPreOrderNumber(bst.left);
        printPreOrderNumber(bst.right);

    }

    public static double countLeafNode(BinarySearchTreeNumber bst, double count) {

        double sum = 0;

        if (bst.getLeft() == null && bst.getRight() == null) {
            return count;
        }

        if (bst.getLeft() != null) {
            sum += countLeafNode(bst.getLeft(), 1);
        }

        if (bst.getRight() != null) {
            sum += countLeafNode(bst.getRight(), 1);
        }

        return sum;

    }

    public static double getMaxDepth(BinarySearchTreeNumber bst, double depth) {

        double maxDepth = 0;

        if (bst.getLeft() == null && bst.getRight() == null) { // leaf 노드면
            return depth;
        }

        if (bst.getLeft() != null) {
            maxDepth = Math.max(maxDepth, getMaxDepth(bst.getLeft(), depth + 1));
        }

        if (bst.getRight() != null) {
            maxDepth = Math.max(maxDepth, getMaxDepth(bst.getRight(), depth + 1));
        }

        return maxDepth;

    }

    public static double sumLeafNodeDepth(BinarySearchTreeNumber bst, double depth) {

        double depthSum = 0;

        if (bst.getLeft() == null && bst.getRight() == null) { // leaf 노드면
            return depth;
        }

        if (bst.getLeft() != null) {
            depthSum += sumLeafNodeDepth(bst.getLeft(), depth + 1);
        }

        if (bst.getRight() != null) {
            depthSum += sumLeafNodeDepth(bst.getRight(), depth + 1);
        }

        return depthSum;
    }

    public static double calcLeaf(SearchTree searchTree, BinarySearchTreeNumber bst, double number) {
        return searchTree.searchTree(bst, number);
    }


}

@FunctionalInterface
interface SearchTree {

    double searchTree(BinarySearchTreeNumber bst, double number);

}



