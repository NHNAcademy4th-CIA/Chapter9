package org.nhnacademy.lsj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem4 {


    private static final Logger logger = LoggerFactory.getLogger(Problem4.class);


    public static void problem4() {
        Queue<BinarySearchTree> q = new LinkedList<>();

        Scanner sc = new Scanner(System.in);

        BinarySearchTree bst = new BinarySearchTree("root");


        for (int i = 0; i < 7; i++) {
            bst.add(bst, sc.nextLine());
        }

        q.add(bst);

        bfs(q, bst);


    }

    public static void bfs(Queue<BinarySearchTree> q, BinarySearchTree bst) {


        while (!q.isEmpty()) {

            BinarySearchTree temp = q.poll();


            logger.info("{}", temp.getValue());

            if (temp.getLeft() != null) {
                q.add(temp.getLeft());
            }

            if (temp.getRight() != null) {
                q.add(temp.getRight());
            }

        }


    }


}

