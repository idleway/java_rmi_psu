package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.EmptyStackException;
import java.math.BigDecimal;

public class RPNParser {
    private static final char[] operatorTokens = {'*', '/', '-', '+'};

    public static boolean isNumToken(String token) {
        try {
            new BigDecimal(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private static boolean isOpToken(char token) {
        for (char nt: operatorTokens) {
            if (nt == token)
                return true;
        }

        return false;
    }

    public static boolean isOpToken(String token) {
        for (char c: operatorTokens) {
            if (token.equals(String.valueOf(c)))
                return true;
        }

        return false;
    }

    private static int getIndexOfOpToken(char c) {
        for (int i = 0; i < operatorTokens.length; i++) {
            if (operatorTokens[i] == c)
                return i;
        }

        return 9999;
    }

    private List<String> splitExpr(String expression) {
        StringBuilder stringBuilder = new StringBuilder(expression.length());
        List<String> output = new ArrayList<>();
        boolean minusMayBeUnary = true;

        for (char c: expression.toCharArray()) {
            if(c == '(')
            {
                output.add(String.valueOf(c));
                minusMayBeUnary = true;
                continue;
            }

            if(c == ')') {
                if (stringBuilder.length() > 0)
                    output.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                output.add(String.valueOf(c));
                minusMayBeUnary = false;
                continue;
            }

            if (c == ' ') {
                if (stringBuilder.length() > 0)
                    output.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                continue;
            }

            if (isOpToken(c)) {
                if (minusMayBeUnary && c == '-')
                {
                    stringBuilder.append(c);
                    continue;
                }
                if (stringBuilder.length() > 0)
                    output.add(stringBuilder.toString());
                output.add(String.valueOf(c));
                stringBuilder.setLength(0);
                minusMayBeUnary = true;
            }
            else {
                stringBuilder.append(c);
                minusMayBeUnary = false;
            }

        }

        if (stringBuilder.length() > 0)
            output.add(stringBuilder.toString());

        return output;
    }

    public String toRPN(String expression) throws Exception {
        List<String> splitExpr = splitExpr(expression);

        StringBuilder stringBuilder = new StringBuilder(expression.length());
        Stack<Character> opStack = new Stack<>();

        for (String token: splitExpr) {
            if (isNumToken(token)) {
                stringBuilder.append(token);
                stringBuilder.append(" ");
                continue;
            }

            if (isOpToken(token)) {
                char stackOpToken;
                try {
                    stackOpToken = opStack.peek();
                }
                catch (EmptyStackException e) {
                    stackOpToken = '\0';
                }

                if (stackOpToken == '(') {
                    opStack.add(token.charAt(0));
                    continue;
                }


                while (getIndexOfOpToken(stackOpToken) <= getIndexOfOpToken(token.charAt(0))) {
                    stringBuilder.append(stackOpToken);
                    stringBuilder.append(" ");
                    opStack.pop();
                    try {
                        stackOpToken = opStack.peek();
                    }
                    catch (EmptyStackException e) {
                        break;
                    }
                }

                opStack.add(token.charAt(0));
                continue;
            }

            if (token.equals("(")) {
                opStack.add('(');
                continue;
            }

            if (token.equals(")")) {
                try {
                    char stackOpToken = opStack.pop();

                    while (stackOpToken != '(') {
                        stringBuilder.append(stackOpToken);
                        stringBuilder.append(" ");
                        stackOpToken = opStack.pop();
                    }
                }
                catch (EmptyStackException e) {
                     throw new Exception("Missed bracket");
                    // Incorrect
                }
            }

        }

        while (!opStack.empty()) {
            char token = opStack.pop();
            if (token == '(') {
                throw new Exception("Missed bracket");
            }
            stringBuilder.append(token);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
}
