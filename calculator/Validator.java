package calculator;

public class Validator {
    private final char[] allowedSymbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '(', ')', '+', '*', '-', '/'};

    private boolean checkEmptyString(String expression) {
        return expression.equals("");
    }

    private boolean checkAllowedSymbols(String expression) {
        for(char c: expression.toCharArray()) {
            boolean foundChar = false;
            for (char sym: allowedSymbols) {
                if (sym == c) {
                    foundChar = true;
                    break;
                }
            }
            if(!foundChar)
                return false;
        }
        return true;
    }


    public boolean validate(String expression) {
        String trimmedExpr = expression.trim();

        if (this.checkEmptyString(trimmedExpr))
            return false;

        return this.checkAllowedSymbols(trimmedExpr);
    }
}
