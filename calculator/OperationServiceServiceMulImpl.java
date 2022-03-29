package calculator;

import java.math.BigDecimal;

public class OperationServiceServiceMulImpl implements OperationService {
    @Override
    public BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception{
        return a.multiply(b);
    }
}