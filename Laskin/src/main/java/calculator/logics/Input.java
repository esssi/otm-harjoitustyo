
package calculator.logics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Input {
    // täällä käsitellään syötettyä laskua (merkkijono s)
    private String s;
    private Calculator c;
    private List<Integer> operationSymbols;
    private List<Double> numbers;
    
    public Input(String s, Calculator c) {
        this.s = s;
        this.c = c;
        // tänne listataan indeksit, joista +,-,* jne löytyvät
        this.operationSymbols = new ArrayList<>();
        this.numbers = new ArrayList<>();
    }
    
    public boolean hasMultiplicationsOrDivisions() {
        if (s.contains("*") || s.contains("/")) {
            return true;
        }
        // koska tällöin ei lasketa vain vasemmalta oikealle!
        return false;
    }
    
    public void listOperationSymbols() {
        // ei-numerosymbolien sijainnit merkkijonossa
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || 
                s.charAt(i) == '*' || s.charAt(i) == '/' ||
                s.charAt(i) == '^' || s.charAt(i) == '!') {
                this.operationSymbols.add(i);
            }
        }
        //entä piste ym?
    }
    
    public void listNumbersAsDouble() {
        this.listOperationSymbols();
        Iterator<Integer> iter = this.operationSymbols.iterator();
        
        int first = 0;
        
        while (iter.hasNext()) {
            int last = iter.next();
            if (last == 0) {
                first = 1;
                last = iter.next();
            }
            
            double numberAsDouble = Double.parseDouble(s.substring(first, last));
            first = last + 1;
            // listataan numerot, huom. viimeinen lisätään erikseen
            this.numbers.add(numberAsDouble);
        }
        
        int size = this.operationSymbols.size();
        int next = this.operationSymbols.get(size - 1) + 1;
        double lastNumber = Double.parseDouble(s.substring(next, s.length()));    
        this.numbers.add(lastNumber);
    }
    
    public List<Double> getNumbers() {
        return this.numbers;
    }
    
    public List<Integer> getOperationSymbols() {
        return this.operationSymbols;
    }
    
    private void sumAndSubtract() {
        // ei jako- ja kertolaskuja -> voidaan laskea vas. oikealle
        for (int i = 0; i < this.operationSymbols.size(); i++) {
            if (s.charAt(this.operationSymbols.get(i)) == '+') {
                c.add(this.numbers.get(i + 1));
            } else if (s.charAt(this.operationSymbols.get(i)) == '-') {
                c.subtract(this.numbers.get(i + 1));
            }
        }
        // ei toimi tällä hetkellä laskulle muotoa "-x+y" tms
    }
    
    public void calculate() {
        // tapahtuu todella kummia kun liukulukua kerrotaan..
        this.listNumbersAsDouble();
        boolean firstNumberNegative = false;
        
        if (s.charAt(0) == '-') {
            c.subtract(this.numbers.get(0));
            firstNumberNegative = true;
        } else {
            c.add(this.numbers.get(0));
        }
        
        if (this.hasMultiplicationsOrDivisions()) {
            //tänne jotain rekursiivista?     
            for (int i = 0; i < this.operationSymbols.size(); i++) { 
                if (s.charAt(this.operationSymbols.get(i)) == '*') {
                    if (firstNumberNegative) {
                        c.multiplyBy(this.numbers.get(i));
                    } else {
                        c.multiplyBy(this.numbers.get(i + 1));
                    }
                    
                } else if (s.charAt(this.operationSymbols.get(i)) == '/') {
                    if (firstNumberNegative) {
                        c.divideBy(this.numbers.get(i));
                    } else {
                        c.divideBy(this.numbers.get(i + 1));
                    }
                }
            }
        } else {
            this.sumAndSubtract();
        }
    }

}
