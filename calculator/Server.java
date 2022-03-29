package calculator;
        
import java.math.RoundingMode;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server{
    public static void main(String args[]) {    
        int registryPort = 2233;

        try {
            RoundingMode rm = RoundingMode.HALF_UP;  // Режим округления по правилам математики

            OperationService calcAdd = new OperationServiceServiceAddImpl();
            OperationService calcSub = new OperationServiceServiceSubImpl();
            OperationService calcMul = new OperationServiceServiceMulImpl();
            OperationService calcDiv = new OperationServiceServiceDivImpl(rm);

            OperationService stubAdd = (OperationService) UnicastRemoteObject.exportObject((OperationService) calcAdd, 0);
            OperationService stubSub = (OperationService) UnicastRemoteObject.exportObject((OperationService) calcSub, 0);
            OperationService stubMul = (OperationService) UnicastRemoteObject.exportObject((OperationService) calcMul, 0);
            OperationService stubDiv = (OperationService) UnicastRemoteObject.exportObject((OperationService) calcDiv, 0);

            Registry registry = LocateRegistry.createRegistry(registryPort);
            registry.rebind("CalculatorAddService", stubAdd);
            registry.rebind("CalculatorSubService", stubSub);
            registry.rebind("CalculatorMulService", stubMul);
            registry.rebind("CalculatorDivService", stubDiv);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}