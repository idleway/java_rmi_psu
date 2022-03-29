package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OperationServiceServiceDivImpl implements OperationService {
    private final RoundingMode rm;

    public OperationServiceServiceDivImpl(RoundingMode rm) {
        this.rm = rm;
    }

    @Override
    public BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception{
        return a.divide(b, rm);
    }    
}