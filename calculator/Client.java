package calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {    

    public static void main(String[] args) {
        int registryPort = 2233;
        Registry registry = LocateRegistry.getRegistry(registryPort);
        Calculator calc = (Calculator) registry.lookup("CalculatorService");

        System.out.println(calc.add(7,3));
        System.out.println(calc.sub(7,3));
        System.out.println(calc.mul(7,3));
        System.out.println(calc.div(7,3));    
    }
}