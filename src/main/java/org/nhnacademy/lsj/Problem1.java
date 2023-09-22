package org.nhnacademy.lsj;

import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 피보나치를 실행함.
 * 피보나치 조건 f(0) = 1 , f(1) = 1 ,  f(n) = f(n-1) + f(n-2) .
 */

public class Problem1 {

    private static final Logger logger = LoggerFactory.getLogger(Problem1.class);

    /**
     * 재귀로 구현한 피보나치 함수.
     *
     * @param number 피보나치 함수 f(N).
     * @return f(N)의 리턴값.
     */
    public static BigInteger fibonacci(BigInteger number) {

        if (number.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        } else if (number.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }

        return fibonacci(number.subtract(BigInteger.ONE))
                .add(fibonacci(number.subtract(BigInteger.TWO)));
    }




    /**
     * f(1) ... f(10) 까지의 피보나치 .
     */
    public static void problem1() {


        for (int i = 0; i <= 10; i++) {
            logger.info("{}", fibonacci(new BigInteger(String.valueOf(i))));
        }

    }

}
