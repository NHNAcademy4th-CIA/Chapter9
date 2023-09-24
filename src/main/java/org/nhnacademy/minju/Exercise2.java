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
        private String item;
        private TreeNode left;
        private TreeNode right;

        TreeNode(String str) {
            item = str;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public String getItem() {
            return item;
        }

    }

    private static TreeNode root;

    /**
     * .BufferedReader로 텍스트 파일을 읽어온 후 각 단어를 공백을 기준으로 split한다.
     * 각각의 단어가 트리에 없다면 트리에 넣는다.
     * 모든 단어를 트리에 넣은 후 inorder로 출력한다
     * try - catch로 IOException을 잡는다.
     */
    public static void exercise2() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/ex2.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.trim().split(" ");
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
            if (newItem.compareTo(runner.getItem()) < 0) { // string
                if (runner.getLeft() == null) {
                    runner.setLeft(new TreeNode(newItem));
                    return;
                }

                runner = runner.getLeft();
                continue;
            }
            if (runner.getRight() == null) {
                runner.setRight(new TreeNode(newItem));
                return;

            }
            runner = runner.getRight();
        }
    }

    private static boolean treeContains(TreeNode root, String item) {
        if (root == null) {
            return false;
        } else if (item.equals(root.getItem())) {
            return true;
        } else if (item.compareTo(root.getItem()) < 0) {
            return treeContains(root.getLeft(), item);
        } else {
            return treeContains(root.getRight(), item);
        }
    }

    /**
     * .print Inorder
     *
     * @param node TreeNode
     */
    private static void treeList(TreeNode node) {
        if (node != null) {
            treeList(node.getLeft());
            logger.info("{}", node.getItem());
            treeList(node.getRight());
        }
    }
}
