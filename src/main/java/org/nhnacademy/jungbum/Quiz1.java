package org.nhnacademy.jungbum;

import java.math.BigInteger;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quiz1 {
    private final Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(Quiz1.class);
    public Quiz1(){
        BigInteger bigInteger = new BigInteger(scanner.next());
        logger.info("{}",customMath.factorial(bigInteger));
        logger.info("{}",customMath.fibonacci(bigInteger));
    }
}


class customMath{
    private customMath(){};
    public static BigInteger factorial(BigInteger n){
        if(n.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        else{
            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
        }
    }

    public static BigInteger fibonacci(BigInteger n){
        if(n.equals(BigInteger.ZERO)||n.equals(BigInteger.ONE))
            return BigInteger.ONE;
        else{
            return fibonacci(n.subtract(BigInteger.ONE)).add(fibonacci(n.subtract(BigInteger.TWO)));
        }
    }
}