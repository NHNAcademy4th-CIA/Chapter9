package org.nhnacademy.lsj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * resources 에 있는 lsj.txt를 읽어  .
 * 파일에 나오는 모든 단어를 이진 정렬트리에 넣는 프로그램.
 */
public class Problem2 {


    /**
     * br을 통해서 txt 읽음 , StringTokenizer를 통해 공백 제거 후 이진 정렬트리에 넣어줌.
     * 이진정렬 트리의 add는 메서드로 구현돼있음 , 자동으로 정렬돼서 들어감.
     */
    public static void problem2() {

        BinarySearchTree bst = null;


        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/lsj.txt"))) {

            StringTokenizer stk;
            String line;

            while ((line = br.readLine()) != null) {
                stk = new StringTokenizer(line);
                while (stk.hasMoreTokens()) {

                    String str = stk.nextToken();

                    if (bst == null) {
                        bst = new BinarySearchTree(str);
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

    /**
     * value가 왔을때
     * root 보다 작으면 ==  즉 root아스키코드가 더 크면 -> 지금 들어온 value가 알파벳 선순위임
     * 선순위는 left로.
     * root 보다 크면 == 즉 root아스키코드가 더 작으면 -> 지금 들어오면 value가 알파벳 후순위임
     * 후순위는 right로/
     *
     * @param bst   이진정렬트리 .
     * @param value add할 value.
     */
    public void add(BinarySearchTree bst, String value) {


        String temp = bst.getValue();


        if (temp.compareTo(value) > 0) { // 아스키가 내가 더 커
            if (bst.left != null) {
                add(bst.left, value);

            } else {
                bst.left = new BinarySearchTree(value);

            }
        } else if (temp.compareTo(value) < 0) { // 아스키가 내가 더 작아

            if (bst.right != null) {
                add(bst.right, value);

            } else {
                bst.right = new BinarySearchTree(value);

            }

        }

    }


    /**
     * preOrder 순회 .
     *
     * @param bst 이진정렬트리.
     */
    public static void printPreOrder(BinarySearchTree bst) {

        if (bst == null) {
            return;
        }
        logger.info("{}", bst.value);

        printPreOrder(bst.left);
        printPreOrder(bst.right);

    }


}