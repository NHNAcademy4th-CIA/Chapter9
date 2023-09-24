package org.nhnacademy.jungbum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 파일 읽고 트리로 다시 쓰기.
 */
public class Quiz2 {
    private static Logger logger = LoggerFactory.getLogger(Quiz2.class);

    public Quiz2() {
        File note = new File("src/main/resources/jb.txt");
        try (
                BufferedReader br = new BufferedReader(new FileReader(note))) {
            String line;

            while (((line = br.readLine()) != null)) {
                if (SortTreeDemo.treeContains(SortTreeDemo.getRoot(), line))
                    continue;
                SortTreeDemo.treeInsert(line);
            }
        } catch (IOException e) {
            logger.info(e.toString());
        }
        SortTreeDemo.treeList(SortTreeDemo.getRoot());
    }
}

/**
 * This program demonstrates a few routines for processing binary
 * sort trees.  It uses a binary sort tree of strings.  The user
 * types in strings.  The user's string is converted to lower case, and --
 * if it is not already in the tree -- it is inserted into the tree.
 * Then the number of nodes in the tree and a list of items in the tree
 * are output.  The program ends when the user enters an empty string.
 */
class SortTreeDemo {

    private static Logger logger = LoggerFactory.getLogger(Quiz2.class);
    /**
     * An object of type TreeNode represents one node in a binary tree of strings.
     */
    private static class TreeNode {
        String item;      // The data in this node.
        TreeNode left;    // Pointer to left subtree.
        TreeNode right;   // Pointer to right subtree.

        TreeNode(String str) {
            // Constructor.  Make a node containing the specified string.
            // Note that left and right pointers are initially null.
            item = str;
        }
    }  // end nested class TreeNode

    public static TreeNode getRoot() {
        return root;
    }

    private static TreeNode root;  // Pointer to the root node in a binary tree.

    // This tree is used in this program as a
    // binary sort tree.  When the tree is empty,
    // root is null (as it is initially).
    public static void treeInsert(String newItem) {
        if (root == null) {
            // The tree is empty.  Set root to point to a new node containing
            // the new item.  This becomes the only node in the tree.
            root = new TreeNode(newItem);
            return;
        }
        TreeNode runner;  // Runs down the tree to find a place for newItem.
        runner = root;   // Start at the root.
        while (true) {
            if (newItem.compareTo(runner.item) < 0) {
                // Since the new item is less than the item in runner,
                // it belongs in the left subtree of runner.  If there
                // is an open space at runner.left, add a new node there.
                // Otherwise, advance runner down one level to the left.
                if (runner.left == null) {
                    runner.left = new TreeNode(newItem);
                    return;  // New item has been added to the tree.
                } else
                    runner = runner.left;
            } else {
                // Since the new item is greater than or equal to the item in
                // runner it belongs in the right subtree of runner.  If there
                // is an open space at runner.right, add a new node there.
                // Otherwise, advance runner down one level to the right.
                if (runner.right == null) {
                    runner.right = new TreeNode(newItem);
                    return;  // New item has been added to the tree.
                } else
                    runner = runner.right;
            }
        } // end while
    }  // end treeInsert()


    /**
     * Return true if item is one of the items in the binary
     * sort tree to which root points.  Return false if not.
     */
    public static boolean treeContains(TreeNode root, String item) {
        if (root == null) {
            // Tree is empty, so it certainly doesn't contain item.
            return false;
        } else if (item.equals(root.item)) {
            // Yes, the item has been found in the root node.
            return true;
        } else if (item.compareTo(root.item) < 0) {
            // If the item occurs, it must be in the left subtree.
            return treeContains(root.left, item);
        } else {
            // If the item occurs, it must be in the right subtree.
            return treeContains(root.right, item);
        }
    }  // end treeContains()


    /**
     * Print the items in the tree in inorder, one item to a line.
     * Since the tree is a sort tree, the output will be in increasing order.
     */
    public static void treeList(TreeNode node) {
        if (node != null) {
            treeList(node.left);             // Print items in left subtree.
            logger.info("    {}" , node.item);  // Print item in the node.
            treeList(node.right);            // Print items in the right subtree.
        }
    } // end treeList()


    /**
     * Count the nodes in the binary tree.
     *
     * @param node A pointer to the root of the tree.  A null value indicates
     *             an empty tree.
     * @return the number of nodes in the tree to which node points.  For an
     * empty tree, the value is zero.
     */
    private static int countNodes(TreeNode node) {
        if (node == null) {
            // Tree is empty, so it contains no nodes.
            return 0;
        } else {
            // Add up the root node and the nodes in its two subtrees.
            int leftCount = countNodes(node.left);
            int rightCount = countNodes(node.right);
            return 1 + leftCount + rightCount;
        }
    } // end countNodes()


} // end class SortTreeDemo