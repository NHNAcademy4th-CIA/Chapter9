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
     *
     * @param N
     * @return
     */
    public static BigInteger fibonacci(BigInteger N) {

        if (N.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        } else if (N.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }

        return fibonacci(N.subtract(BigInteger.ONE))
                .add(fibonacci(N.subtract(BigInteger.TWO)));
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
