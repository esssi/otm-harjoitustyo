
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
    // huomiselle hommaa: hoida laskujärjestys loppuun, lisää ( ja )
    public boolean hasMultiplicationsOrDivisions(){
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
    
    public void listNumbersAsDouble(){
        this.listOperationSymbols();
        Iterator<Integer> iter = this.operationSymbols.iterator();
        
        int first = 0;
        if (this.operationSymbols.contains(0)) {
            // toimiiko nyt, jos lasku on "-3*8" tms? -ei näköjään
            first = 1;
        }
        
        while(iter.hasNext()){
            int last = iter.next();
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
    
    public void calculate() {
        this.listNumbersAsDouble();
        
        if (this.hasMultiplicationsOrDivisions()) {
            //tänne jotain rekursiivista?
            for (int i = 0; i < this.operationSymbols.size(); i++) {
                if (i > 0 && s.charAt(this.operationSymbols.get(i - 1)) == '+') {
                    c.add(this.numbers.get(i));
                } else if (i > 0 && s.charAt(this.operationSymbols.get(i - 1)) == '-') {
                    // tämä kohta ei toimi oikein - varmaan koska oletus on että miinus ei alussa
                    c.subtract(this.numbers.get(i));
                } else {
                    c.add(this.numbers.get(i));
                }
                
                if(s.charAt(this.operationSymbols.get(i)) == '*'){
                    c.multiplyBy(this.numbers.get(i + 1));
                } else if (s.charAt(this.operationSymbols.get(i)) == '/') {
                    c.divideBy(this.numbers.get(i + 1));
                }
            }
        } else {
            c.add(this.numbers.get(0));
            
            for (int i = 0; i < this.operationSymbols.size(); i++) {
                if(s.charAt(this.operationSymbols.get(i)) == '+'){
                    c.add(this.numbers.get(i + 1));
                } else {
                    c.subtract(this.numbers.get(i + 1));
                }
            }
        }
    }
}
