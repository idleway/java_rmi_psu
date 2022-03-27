package calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {    

    public static void main(String[] args) {
        int registryPort = 2233;        
        try{

            Registry registry = LocateRegistry.getRegistry(registryPort);
            Calculator calcAdd = (Calculator) registry.lookup("CalculatorAddService");
            Calculator calcSub = (Calculator) registry.lookup("CalculatorSubService");
            Calculator calcMul = (Calculator) registry.lookup("CalculatorMulService");
            Calculator calcDiv = (Calculator) registry.lookup("CalculatorDivService");

            System.out.println(calcAdd.add(7,3));
            System.out.println(calcSub.sub(7,3));
            System.out.println(calcMul.mul(7,3));
            System.out.println(calcDiv.div(7,3));
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
    }
}