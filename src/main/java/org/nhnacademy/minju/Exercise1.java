package org.nhnacademy.minju;

import java.math.BigInteger;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * .음수가 아닌 정수 N에 대해
 * fibonacci(n)
 * factorial(n)
 */
public class Exercise1 {
    private static final Logger logger = LoggerFactory.getLogger(Exercise1.class);

    /**
     * .피보나치, 팩토리얼
     */
    public static void exercise1() {
        Scanner scanner = new Scanner(System.in);

        logger.info("Enter N");
        String input = scanner.nextLine();
        BigInteger bigInteger;
        try {
            if (Integer.parseInt(input) < 0) {
                throw new IllegalArgumentException("N은 non-negative Integer이다.");
            }
            bigInteger = new BigInteger(input);
            logger.info("fibonacci = {}", fibonacci(bigInteger));
            logger.info("factorial = {}", factorial(bigInteger));
        } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage());
        }
    }

    private static BigInteger fibonacci(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        }
        return fibonacci(n.subtract(BigInteger.ONE)).add(fibonacci(n.subtract(BigInteger.TWO)));
    }

    private static BigInteger factorial(BigInteger n) {

        if (n.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }
}
