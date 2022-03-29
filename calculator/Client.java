package calculator;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Stack;

public class Client {    

    public static void main(String[] args) throws Exception {
        RPNParser parser = new RPNParser();
        String expr = "1/0";
        Validator validator = new Validator();
        OperationService calcAdd;
        OperationService calcSub;
        OperationService calcMul;
        OperationService calcDiv;

        int registryPort = 2233;
        try{
            Registry registry = LocateRegistry.getRegistry(registryPort);
            calcAdd = (OperationService) registry.lookup("CalculatorAddService");
            calcSub = (OperationService) registry.lookup("CalculatorSubService");
            calcMul = (OperationService) registry.lookup("CalculatorMulService");
            calcDiv = (OperationService) registry.lookup("CalculatorDivService");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return;
        }

        if (!validator.validate(expr)) {  // Первичная валидация
            System.out.println("Incorrect input");
        }
        else {  // +- нормальный ввод
            String preparedExpr = parser.toRPN(expr);  // Можем упасть из-за скобок
            Stack<BigDecimal> operands = new Stack<>();

            for (String token: preparedExpr.split(" ")) {
                if(RPNParser.isNumToken(token)) {  // Сохраняем операнды
                    operands.add(new BigDecimal(token));
                }
                else if(RPNParser.isOpToken(token)) {
                    BigDecimal op1 = operands.pop();
                    BigDecimal op2 = operands.pop();
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
                        case "/" -> {  // Нужно обработать деление на 0
                            BigDecimal res = calcDiv.calcOp(op2, op1);
                            operands.add(res);
                        }
                    }
                }
            }

            System.out.println(operands.pop());  // Результат на вершине стэка
        }
    }
}