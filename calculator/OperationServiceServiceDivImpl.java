package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class OperationServiceServiceDivImpl implements OperationService {
    private final MathContext mc;

    public OperationServiceServiceDivImpl(MathContext mc) {
        this.mc = mc;
    }

    @Override
    public BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception{
        return a.divide(b, mc);
    }    
}