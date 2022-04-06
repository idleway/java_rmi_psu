package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class OperationServiceServiceMulImpl implements OperationService {
    MathContext mc;

    public OperationServiceServiceMulImpl(MathContext mc) {
        this.mc = mc;
    }

    @Override
    public BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception{
        return a.multiply(b, mc);
    }
}