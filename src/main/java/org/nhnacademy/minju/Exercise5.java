package org.nhnacademy.minju;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .1023개의 노드가 있는 임의 이진 정렬 트리
 * 트리에 있는 모든 리프의 평균 깊이와 모든 리프의 최대 깊이를 계산
 * 재귀 서브루틴 :
 * 1. 나뭇잎 수 세기(leafCount)
 * 2. 모든 나뭇잎 깊이의 합(sumLeaf)
 * 3. 최대 깊이 찾기(findMaxDepth)
 * depth = 0, depth++;
 */
public class Exercise5 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise5.class);
    private static TreeNode root;

    private static class TreeNode {
        int item;
        TreeNode left;
        TreeNode right;

        TreeNode(int n) {
            item = n;
        }
    }

    public static void exercise5() {
        root = null;
        Random random = new Random();

        for (int i = 0; i < 1023; i++) {
            treeInsert(random.nextInt());
        }

        logger.info("count the leaves : {}", leafCount(root));
        logger.info("find the sum of depths of all the leaves : {}",
                sumLeaf(root, 0));
        logger.info("find the maximum depth : {}", findMaxDepth(root, 0));
    }

    private static int findMaxDepth(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return depth;
        }
        int leftMax = findMaxDepth(root.left, depth + 1);
        int rightMax = findMaxDepth(root.right, depth + 1);
        return Math.max(leftMax, rightMax);
    }

    private static int sumLeaf(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return depth;
        }
        return sumLeaf(root.left, depth + 1) + sumLeaf(root.right, depth + 1);
    }

    private static int leafCount(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        }
        return leafCount(root.left) + leafCount(root.right);
    }

    private static void treeInsert(int newItem) {
        if (root == null) {
            root = new TreeNode(newItem);
        }
        TreeNode runner = root;
        while (true) {
            if (newItem < runner.item) {
                if (runner.left == null) {
                    runner.left = new TreeNode(newItem);
                    return;
                } else {
                    runner = runner.left;
                }
            } else {
                if (runner.right == null) {
                    runner.right = new TreeNode(newItem);
                    return;
                } else {
                    runner = runner.right;
                }
            }
        }
    }
}
