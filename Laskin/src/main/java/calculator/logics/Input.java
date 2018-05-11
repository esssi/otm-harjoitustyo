
package calculator.logics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Input {

    private String s;
    private Calculator c;
    private List<Integer> operationSymbols;
    private List<Double> numbers;
    private List<Double> products;
    
    public Input(String s, Calculator c) {
        this.s = s;
        this.c = c;
        this.operationSymbols = new ArrayList<>();
        this.numbers = new ArrayList<>();
        this.products = new ArrayList<>();
    }
    
    public boolean hasMultiplicationsOrDivisions() {
        if (s.contains("*") || s.contains("/")) {
            return true;
        }
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
        c.clear();
        if (s.charAt(this.operationSymbols.get(0)) == '+' || 
            s.charAt(this.operationSymbols.get(0)) == '-') {
            c.add(this.numbers.get(0));
            this.products.remove(0);
        } 
        
        for (int i = 0; i < this.operationSymbols.size(); i++) {
            if (s.charAt(this.operationSymbols.get(i)) == '+') {
                if (i == this.operationSymbols.size() - 1 || 
                    (s.charAt(this.operationSymbols.get(i + 1)) != '*' &&
                    s.charAt(this.operationSymbols.get(i + 1)) != '/')) {
                    c.add(this.numbers.get(i + 1));
                }
            } else if (s.charAt(this.operationSymbols.get(i)) == '-') {
                if (i == this.operationSymbols.size() - 1 || 
                    (s.charAt(this.operationSymbols.get(i + 1)) != '*' &&
                    s.charAt(this.operationSymbols.get(i + 1)) != '/')) {
                    c.subtract(this.numbers.get(i + 1));
                }
            }
        }
        
        for (int i = 0; i < this.products.size(); i++) {
            c.add(this.products.get(i));
        }
    }
    
    private void listProducts() {
        if (s.charAt(this.operationSymbols.get(0)) == '*') {
            c.add(this.numbers.get(0));
            c.multiplyBy(this.numbers.get(1));
        } else if (s.charAt(this.operationSymbols.get(0)) == '/') {
            c.add(this.numbers.get(0));
            c.divideBy(this.numbers.get(1));
        }
            
        for (int i = 1; i < this.operationSymbols.size(); i++) {  
            if (s.charAt(this.operationSymbols.get(i)) == '*') {
                if (s.charAt(this.operationSymbols.get(i - 1)) != '*' &&
                    s.charAt(this.operationSymbols.get(i - 1)) != '/') {
                    this.products.add(c.getCurrentValue());
                    c.clear();
                    c.add(this.numbers.get(i));
                    if (s.charAt(this.operationSymbols.get(i - 1)) == '-') {
                        c.multiplyBy(-1.0);
                    }
                }
                c.multiplyBy(this.numbers.get(i + 1));
                    
            } else if (s.charAt(this.operationSymbols.get(i)) == '/') {
                if (s.charAt(this.operationSymbols.get(i - 1)) != '/' &&
                    s.charAt(this.operationSymbols.get(i - 1)) != '*') {
                    this.products.add(c.getCurrentValue());
                    c.clear();
                    c.add(this.numbers.get(i));
                    if (s.charAt(this.operationSymbols.get(i - 1)) == '-') {
                        c.multiplyBy(-1.0);
                    }
                }
                c.divideBy(this.numbers.get(i + 1));
            }
        }
    }
    
    public void calculate() {
        this.listNumbersAsDouble();

        if (s.charAt(0) == '-') {
            this.numbers.set(0, -1 * this.numbers.get(0));
            this.operationSymbols.remove(0);
        }
        
        if (this.hasMultiplicationsOrDivisions()) {
            this.listProducts();
        } 
        
        this.products.add(c.getCurrentValue());
        this.sumAndSubtract(); 
    }
    
}
