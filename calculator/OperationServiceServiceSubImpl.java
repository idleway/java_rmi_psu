package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class OperationServiceServiceSubImpl implements OperationService {

    MathContext mc;

    public  OperationServiceServiceSubImpl(MathContext mc) {
        this.mc = mc;
    }

    @Override
    public BigDecimal calcOp(BigDecimal a, BigDecimal b) throws Exception{
        return a.subtract(b, mc);
    }

}