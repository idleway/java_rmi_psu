package calculator;

public class Validator {

	public boolean IsValid(String line){
        return IsValidSymbols(line) && IsCorrectBrackets(line) && line.length()>0 && IsCorrectOrder(line);
    }
	
	private boolean IsValidSymbols(String line){
        String correctLine = "1234567890.()*+-/";
        boolean result = true;
        for(int i=0;i<line.length(); i++)
            result = result & correctLine.contains(Character.toString(line.charAt(i)));
        return result;
    }
    
    private boolean IsCorrectBrackets(String line){
        Stack stack= new Stack();
        for(int i=0; i<line.length();i++){
            char elem = line.charAt(i);
            if(elem == '(') stack.push(elem);
            if(elem == ')'){
                if(stack.empty()) return false;
                if((Character)stack.peek() == '(') stack.pop();
                else return false;
            }
        }
        return stack.empty();
    }
    
    private boolean IsCorrectOrder(String oldLine){
        String line = oldLine.replaceAll(" ", "");
        String nums="0123456789";
        String operations ="+*/";
        if ((operations.contains(Character.toString(line.charAt(0))) && line.charAt(0) != '-') || operations.contains(Character.toString(line.charAt(line.length()-1))) ) return false;
        for(int i=1; i<line.length()-1;i++){
            String elem = Character.toString(line.charAt(i));
            String prev = Character.toString(line.charAt(i-1));
            String next = Character.toString(line.charAt(i+1));
            if(operations.contains(elem) && prev.charAt(0) == '-' && (operations.contains(prev) || prev.charAt(0) == '(')) return false;
            if(operations.contains(elem) && next.charAt(0) != '-' && (operations.contains(next) || next.charAt(0) == ')')) return false;
            //if(operations.contains(elem) && ( !nums.contains(prev) || prev.charAt(0) != ')')  && ( !nums.contains(next) || next.charAt(0) != '(') ) return false;
        }
        return true;
    }    
   
}
