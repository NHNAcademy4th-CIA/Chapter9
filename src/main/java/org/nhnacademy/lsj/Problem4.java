package org.nhnacademy.lsj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 큐를 사용한 이진트리 출력 프로그램.
 */
public class Problem4 {


    private static final Logger logger = LoggerFactory.getLogger(Problem4.class);


    /**
     * a s t v b c d 넣어서 postCondition Check 완료했음.
     * 이진트리 생성 -> add 완료 -> Queue를 만든다 -> bfs통해서 tree요소들 print하기.
     */
    public static void problem4() {

        Queue<BinarySearchTree> q = new LinkedList<>();

        Scanner sc = new Scanner(System.in);

        BinarySearchTree bst = new BinarySearchTree("root");


        for (int i = 0; i < 7; i++) {
            bst.add(bst, sc.nextLine());
        }

        q.add(bst);

        bfs(q);


    }

    /**
     * queue 에 root를 넣은채로 온다 -> q를 poll 함 -> poll 한거 출력해.
     * -> 그게 root , 얘가 left 존재하면 q에 넣어 , right 존재하면 q에 넣어.
     * 이 행동 반복해.
     *
     * @param q bfs를 돌릴 queue.
     */
    public static void bfs(Queue<BinarySearchTree> q) {


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

