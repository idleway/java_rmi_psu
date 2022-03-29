package calculator;

import java.math.BigDecimal;

public class OperationServiceServiceAddImpl implements OperationService {
    @Override
    public BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception{
        return a.add(b);
    }
}