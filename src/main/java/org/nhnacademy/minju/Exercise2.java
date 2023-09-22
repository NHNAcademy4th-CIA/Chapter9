package org.nhnacademy.minju;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .파일의 모든 단어를 알파벳순으로 정렬
 */
public class Exercise2 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise2.class);

    private static class TreeNode {
        String item;
        TreeNode left;
        TreeNode right;

        TreeNode(String str) {
            item = str;
        }
    }

    private static TreeNode root;

    /**
     *
     */
    public static void exercise2() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/ex2.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.trim().toLowerCase().split(" ");
                for (String word : splitLine) {
                    if (!treeContains(root, word)) {
                        // 있으면 넣지 않는다
                        treeInsert(word);
                    }
                }
            }
            treeList(root);
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }

    private static void treeInsert(String newItem) {
        if (root == null) { // tree is empty
            root = new TreeNode(newItem);
            return;
        }
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

    private static boolean treeContains(TreeNode root, String item) {
        if (root == null) {
            return false;
        } else if (item.equals(root.item)) {
            return true;
        } else if (item.compareTo(root.item) < 0) {
            return treeContains(root.left, item);
        } else {
            return treeContains(root.right, item);
        }
    }

    /**
     * .print Inorder
     *
     * @param node TreeNode
     */
    private static void treeList(TreeNode node) {
        if (node != null) {
            treeList(node.left);
            logger.info("{}", node.item);
            treeList(node.right);
        }
    }
}
