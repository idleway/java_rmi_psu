package calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Calculator extends Remote {
    double add(double a, double b) throws Exception
    double sub(double a, double b) throws Exception
    double mul(double a, double b) throws Exception;
    double div(double a, double b) throws Exception;
    }