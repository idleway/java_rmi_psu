package calculator;

public class CalculatorServiceImpl implements Calculator {     
    @Override
    public double add(double a, double b){
        return a + b;
    }

    @Override
    public double sub(double a, double b){
        return a - b;
    }

    @Override
    public double mul(double a, double b){
        return a * b;
    }

    @Override
    public double div(double a, double b){
        return a / b;
    }
}