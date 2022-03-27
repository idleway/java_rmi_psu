package calculator;

public class CalculatorServiceSubImpl implements Calculator {     
    @Override
    public double add(double a, double b) throws Exception{
        throw new Exception("Not Implemented");
    }

    @Override
    public double sub(double a, double b) throws Exception{
        return a - b;
    }

    @Override
    public double mul(double a, double b) throws Exception{
        throw new Exception("Not Implemented");    
    }

    @Override
    public double div(double a, double b) throws Exception{
        throw new Exception("Not Implemented");
    }    
}