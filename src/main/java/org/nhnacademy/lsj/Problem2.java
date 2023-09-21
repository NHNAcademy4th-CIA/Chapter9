package org.nhnacademy.lsj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem2 {


    public static void problem2() {

        BinarySearchTree bst = null;

        boolean flag = true;

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/lsj.txt"))) {

            StringTokenizer stk;
            String line;

            while ((line = br.readLine()) != null) {
                stk = new StringTokenizer(line);
                while (stk.hasMoreTokens()) {

                    String str = stk.nextToken();

                    if (flag) {
                        bst = new BinarySearchTree(str);
                        flag = false;
                        continue;
                    }

                    bst.add(bst, str);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BinarySearchTree.printPreOrder(bst);


    }

}

class BinarySearchTree {

    private static final Logger logger = LoggerFactory.getLogger(BinarySearchTree.class);


    private BinarySearchTree left;

    private BinarySearchTree right;

    private String value;


    public String getValue() {
        return value;
    }

    public BinarySearchTree getLeft() {
        return left;
    }

    public BinarySearchTree getRight() {
        return right;
    }


    public BinarySearchTree(String str) {
        this.value = str;
        this.left = null;
        this.right = null;
    }

    public void add(BinarySearchTree bst, String value) {


        String temp = bst.getValue();


        if (temp.compareTo(value) > 0) { // 아스키가 내가 더 커  , 죽 알파벳 후순위임
            if (bst.left != null) {
                add(bst.left, value);

            } else {
                bst.left = new BinarySearchTree(value);

            }
        } else if (temp.compareTo(value) < 0) { // 아스키가 내가 더 작 , 즉 알파벳 선순위야

            if (bst.right != null) {
                add(bst.right, value);

            } else {
                bst.right = new BinarySearchTree(value);

            }

        }

    }

    public static void printPreOrder(BinarySearchTree bst) {

        if (bst == null) {
            return;
        }
        logger.info("{}", bst.value);

        printPreOrder(bst.left);
        printPreOrder(bst.right);

    }


}