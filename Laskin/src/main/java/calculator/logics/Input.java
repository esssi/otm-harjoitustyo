
package calculator.logics;

import java.util.ArrayList;
import java.util.List;

public class Input {
    // täällä käsitellään syötettyä laskua (merkkijono s)
    private String s;
    private List<Integer> operationSymbols;
    private List<Double> numbers;
    
    public Input(String s) {
        this.s = s;
        // tänne listataan indeksit, joista +,-,* jne löytyvät
        this.operationSymbols = new ArrayList<>();
        this.numbers = new ArrayList<>();
    }
    
    public boolean hasMultiplicationsOrDivisions(){
        if (s.contains("*") || s.contains("/")) {
            return true;
        }
        
        return false;
    }
    
    public void listOperationSymbols() {
        for (int i = 0; i < s.length(); i++) {
            // aika kömpelö
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || 
                s.charAt(i) == '*' || s.charAt(i) == '/' ||
                s.charAt(i) == '^' || s.charAt(i) == '!') {
                this.operationSymbols.add(i);
            }
        }
        //entä piste ym?
    }
    
    public void listNumbersAsDouble(){
        int first = 0;
        int last = 0;
        //entä jos syöte on "+1+" tms?
        for (int i = 0; i < this.operationSymbols.size(); i++) {
            last = this.operationSymbols.get(i);
            int number = Integer.parseInt(s.substring(first, last));
            first = last + 1;
            
            double numberAsDouble = (double) number;
            this.numbers.add(numberAsDouble);
        }
    }
    
    public List<Double> getNumbers() {
        return this.numbers;
    }
    
    public List<Integer> getOperationSymbols() {
        return this.operationSymbols;
    }
    
}
