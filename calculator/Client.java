package calculator;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Client {
    private static OperationService calcAdd;
    private static OperationService calcSub;
    private static OperationService calcMul;
    private static OperationService calcDiv;
    private static int registryPort = 2233;

    private static void PrepareServices() {
        System.out.println("Соединение с сервером...");
        try{
            Registry registry = LocateRegistry.getRegistry(registryPort);
            calcAdd = (OperationService) registry.lookup("CalculatorAddService");
            calcSub = (OperationService) registry.lookup("CalculatorSubService");
            calcMul = (OperationService) registry.lookup("CalculatorMulService");
            calcDiv = (OperationService) registry.lookup("CalculatorDivService");
            System.out.println("Соединение успешно!");
        } catch (Exception e) {
            System.err.println("Ошибка инициализации: " + e);
            e.printStackTrace();
        }
    }

    private static String GetUserInput(Scanner in, Validator validator) {
        System.out.print("Введите выражение: ");
        var expr = in.nextLine();
        while (!validator.IsValid(expr)) {
            System.out.println();
            System.out.print("Ошибка в выражении, попробуйте заново: ");
            expr = in.nextLine();
        }
        return expr;
    }

    private static String CalculateExpression(String expr, RPNParser parser) throws Exception {
        String preparedExpr = parser.toRPN(expr);
        Stack<BigDecimal> operands = new Stack<>();
//        operands.add(BigDecimal.ZERO);

        for (String token: preparedExpr.split(" ")) {
            if(RPNParser.isNumToken(token)) {
                operands.add(new BigDecimal(token));
            }
            else if(RPNParser.isOpToken(token)) {
                BigDecimal op1;
                BigDecimal op2;
                try {
                    op1 = operands.pop();
                    op2 = operands.pop();
                } catch (EmptyStackException e) {
                    throw new ExpressionInputError("Not enough operands!");
                }
                switch (token) {
                    case "+" -> {
                        BigDecimal res = calcAdd.calcOp(op2, op1);
                        operands.add(res);
                    }
                    case "-" -> {
                        BigDecimal res = calcSub.calcOp(op2, op1);
                        operands.add(res);
                    }
                    case "*" -> {
                        BigDecimal res = calcMul.calcOp(op2, op1);
                        operands.add(res);
                    }
                    case "/" -> {
                        BigDecimal res = calcDiv.calcOp(op2, op1);
                        operands.add(res);
                    }
                }
            }
        }

        if(!operands.empty())
        {
            var res = operands.pop().toString();
            if (operands.empty())
                return res;
            if(Objects.equals(operands.peek(), BigDecimal.ZERO))
                return res;
        }

        throw new ExpressionInputError("Expression calc error! Probably missed sign or added space in num!");
    }

    // Tests
    // 123
    // -1
    // -
    // --1
    // -(-1)
    // 1+-1
    // 1--1
    // 1-(-1)
    // 1 / 0
    // 3 * 312
    // 1-(1-)
    // -(-1) + 2
    // 123 123
    // 1 / 3
    // 1---1
    // 1----1
    public static void main(String[] args) throws Exception {
        PrepareServices();
        Scanner in = new Scanner(System.in);
        Validator validator = new Validator();
        RPNParser parser = new RPNParser();

        while (true) {
            var expr = GetUserInput(in, validator);
            try {
                var result = CalculateExpression(expr, parser);
                System.out.println("Ответ: " + result);
            }
            catch (ExpressionInputError e) {
                System.out.println(e.msg);
            }
            catch (ArithmeticException e) {
                System.out.println("Ошибка! Деление на 0!");
            }
        }

    }
}