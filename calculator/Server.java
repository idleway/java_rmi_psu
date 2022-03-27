package calculator;
        
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server{
            
    public static void main(String args[]) {    
        int registryPort = 2233;

        try {
            Calculator server = new CalculatorServiceImpl();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject((Calculator) server, 0);

            Registry registry = LocateRegistry.createRegistry(registryPort);
            registry.rebind("CalculatorService", stub);            

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}