package calculator;
        
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server{
            
    public static void main(String args[]) {    
        int registryPort = 2233;

        try {
            Calculator calcAdd = new CalculatorServiceAddImpl();
            Calculator calcSub = new CalculatorServiceSubImpl();
            Calculator calcMul = new CalculatorServiceMulImpl();
            Calculator calcDiv = new CalculatorServiceDivImpl();

            Calculator stubAdd = (Calculator) UnicastRemoteObject.exportObject((Calculator) calcAdd, 0);
            Calculator stubSub = (Calculator) UnicastRemoteObject.exportObject((Calculator) calcSub, 0);
            Calculator stubMul = (Calculator) UnicastRemoteObject.exportObject((Calculator) calcMul, 0);
            Calculator stubDiv = (Calculator) UnicastRemoteObject.exportObject((Calculator) calcDiv, 0);

            Registry registry = LocateRegistry.createRegistry(registryPort);
            registry.rebind("CalculatorAddService", stubAdd);
            registry.rebind("CalculatorSubService", stubSub);
            registry.rebind("CalculatorMulService", stubMul);
            registry.rebind("CalculatorDivService", stubDiv);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}