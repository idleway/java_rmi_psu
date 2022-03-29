package calculator;

import java.rmi.Remote;
import java.math.BigDecimal;


public interface OperationService extends Remote {
    BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception;
}