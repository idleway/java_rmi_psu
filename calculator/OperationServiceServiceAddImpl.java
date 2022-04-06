package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class OperationServiceServiceAddImpl implements OperationService {

    private final MathContext mc;

    public OperationServiceServiceAddImpl(MathContext mc) {
        this.mc = mc;
    }

    @Override
    public BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception{
        return a.add(b, mc);
    }
}