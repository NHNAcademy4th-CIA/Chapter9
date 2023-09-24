package org.nhnacademy.jungbum;

import java.math.BigInteger;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * BigInteger를 사용한 fibonacci factorial 구현
 */
public class Quiz1 {
    private final Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(Quiz1.class);
    public Quiz1(){
        BigInteger bigInteger = new BigInteger(scanner.next());
        logger.info("{}",customMath.factorial(bigInteger));
        logger.info("{}",customMath.fibonacci(bigInteger));
        scanner.close();
    }
}

/***
 * 사용자 정의 수학 클래스
 */
class customMath{
    private customMath(){};

    /***
     * factorial
     * @param n 입력값
     * @return n이 Zero가 될 때까지 재귀
     */
    public static BigInteger factorial(BigInteger n){
        if(n.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        else{
            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
        }
    }
    /***
     * fibonacci
     * @param n 입력값
     * @return  n이 Zero OR One이 될 때까지 재귀
     */
    public static BigInteger fibonacci(BigInteger n){
        if(n.equals(BigInteger.ZERO)||n.equals(BigInteger.ONE))
            return BigInteger.ONE;
        else{
            return fibonacci(n.subtract(BigInteger.ONE)).add(fibonacci(n.subtract(BigInteger.TWO)));
        }
    }
}