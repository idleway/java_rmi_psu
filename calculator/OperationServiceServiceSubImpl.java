package calculator;

import java.math.BigDecimal;

public class OperationServiceServiceSubImpl implements OperationService {
    @Override
    public BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception{
        return a.subtract(b);
    }

}