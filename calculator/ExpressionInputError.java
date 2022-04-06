package calculator;

public class ExpressionInputError extends Exception{
    public String msg;

    public ExpressionInputError(String msg) {
        this.msg = msg;
    }

}
