package org.nhnacademy.jungbum;
import org.nhnacademy.jungbum.textio.TextIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quiz6 {
    private static Logger logger = LoggerFactory.getLogger(Quiz6.class);
    public Quiz6() {
        while (true) {
           logger.info("종료할려면 공백을 입력하세요.");
            TextIO.skipBlanks();
            if ( TextIO.peek() == '\n' )
                break;
            try {
                SimpleParser3.ExpNode exp = SimpleParser3.expressionTree();
                TextIO.skipBlanks();
                if ( TextIO.peek() != '\n' )
                    throw new SimpleParser3.ParseError("표현식이 종료됬지만 끝에 뭔가 있다.");
                TextIO.getln();
                logger.info("Value at x = 0 is {}", exp.value(0));
                logger.info("Value at x = 1 is {}", exp.value(1));
                logger.info("Value at x = 2 is {}", exp.value(2));
               logger.info("Value at x = 3 is {}", exp.value(3));
               logger.info("");
                exp.printStackCommands();
            }
            catch (SimpleParser3.ParseError e) {
                logger.error("Errror {}",e.toString());
             logger.error("입력취소:  " + TextIO.getln());
            }
        }
    }

}

 class SimpleParser3 {

    public abstract static class ExpNode {
        abstract double value(double xValue);
        abstract void printStackCommands();
    }

    private static class ConstNode extends ExpNode {
        double number;
        ConstNode(double val) {
            number = val;
        }
        double value(double xValue) {
            return number;
        }
        void printStackCommands() {
            System.out.println("  Push " + number);
        }
    }


    private static class BinOpNode extends ExpNode {
        char op;
        ExpNode left;
        ExpNode right;
        BinOpNode(char op, ExpNode left, ExpNode right) {
            assert op == '+' || op == '-' || op == '*' || op == '/';
            assert left != null && right != null;
            this.op = op;
            this.left = left;
            this.right = right;
        }
        double value(double xValue) {

            double x = left.value(xValue);
            double y = right.value(xValue);
            switch (op) {
                case '+':  return x + y;
                case '-':  return x - y;
                case '*':  return x * y;
                case '/':  return x / y;
                default:   return Double.NaN;
            }
        }
        void  printStackCommands() {

            left.printStackCommands();
            right.printStackCommands();
            System.out.println("  Operator " + op);
        }
    }


    private static class UnaryMinusNode extends ExpNode {
        ExpNode operand;
        UnaryMinusNode(ExpNode operand) {
            assert operand != null;
            this.operand = operand;
        }
        double value(double xValue) {
            double neg = operand.value(xValue);
            return -neg;
        }
        void printStackCommands() {

            operand.printStackCommands();
            System.out.println("  Unary minus");
        }
    }


    private static class VariableNode extends ExpNode {
        VariableNode() {
        }
        double value(double xValue) {
            return xValue;
        }
        void printStackCommands() {
            System.out.println("  Push X");
        }
    }

    public static class ParseError extends Exception {
        ParseError(String message) {
            super(message);
        }
    }

    public static ExpNode expressionTree() throws ParseError {
        TextIO.skipBlanks();
        boolean negative;
        negative = false;
        if (TextIO.peek() == '-') {
            TextIO.getAnyChar();
            negative = true;
        }
        ExpNode exp;
        exp = termTree();
        if (negative)
            exp = new UnaryMinusNode(exp);
        TextIO.skipBlanks();
        while ( TextIO.peek() == '+' || TextIO.peek() == '-' ) {

            char op = TextIO.getAnyChar();
            ExpNode nextTerm = termTree();
            exp = new BinOpNode(op, exp, nextTerm);
            TextIO.skipBlanks();
        }
        return exp;
    }

    private static ExpNode termTree() throws ParseError {
        TextIO.skipBlanks();
        ExpNode term;
        term = factorTree();
        TextIO.skipBlanks();
        while ( TextIO.peek() == '*' || TextIO.peek() == '/' ) {
            char op = TextIO.getAnyChar();
            ExpNode nextFactor = factorTree();
            term = new BinOpNode(op,term,nextFactor);
            TextIO.skipBlanks();
        }
        return term;
    }

    private static ExpNode factorTree() throws ParseError {
        TextIO.skipBlanks();
        char ch = TextIO.peek();
        if ( Character.isDigit(ch) ) {
            double num = TextIO.getDouble();
            return new ConstNode(num);
        }
        else if ( ch == 'x' || ch == 'X' ) {
            TextIO.getAnyChar();
            return new VariableNode();
        }
        else if ( ch == '(' ) {
            TextIO.getAnyChar();
            ExpNode exp = expressionTree();
            TextIO.skipBlanks();
            if ( TextIO.peek() != ')' )
                throw new ParseError(")누락");
            TextIO.getAnyChar();
            return exp;
        }
        else if ( ch == '\n' )
            throw new ParseError("\n 라인이 표현식 중간에 나타남.");
        else if ( ch == ')' )
            throw new ParseError(")괄호가 남습니다.");
        else if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' )
            throw new ParseError("잘못된 연산자가 있습니다");
        else
            throw new ParseError("예기치 않은 문자 \"" + ch + "\"가 발생했습니다.");
    }
}