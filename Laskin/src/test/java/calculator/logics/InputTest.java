
package calculator.logics;

import org.junit.Test;
import static org.junit.Assert.*;

public class InputTest {
    
    @Test
    public void multiplicationsAreFound() {
        Input i = new Input("12+34*234-100", new Calculator());
        
        assertTrue(i.hasMultiplicationsOrDivisions() == true);
    }
    
    @Test
    public void divisionsAreFound() {
        Input i = new Input("356/2-78+3", new Calculator());
                
        assertTrue(i.hasMultiplicationsOrDivisions() == true);      
    }
    
    @Test
    public void noMultiplicationsOrDivisionsReturnsFalse() {
        Input i = new Input("34324+6545-2+656+3!", new Calculator());
        
        assertTrue(i.hasMultiplicationsOrDivisions() == false);
    }
    
    @Test
    public void listOperationSymbolsWorksRight() {
        Input i = new Input("634*20+36/3", new Calculator());
        i.listOperationSymbols();
        
        assertTrue(i.getOperationSymbols().get(0) == 3 && 
        i.getOperationSymbols().get(1) == 6 && i.getOperationSymbols().get(2) == 9);
    }
    
    @Test
    public void listNumbersAsDoubleWorksRight() {
        Input i = new Input("369/3+51", new Calculator());
        i.listNumbersAsDouble();
        
        assertTrue(i.getNumbers().get(0) == 369 && i.getNumbers().get(1) == 3 &&
        i.getNumbers().get(2) == 51);
    }
    
    @Test
    public void listNumbersAsDoubleWorksRightWhenFirstIsNegative() {
        Input i = new Input("-100+55", new Calculator());
        i.listNumbersAsDouble();
        
        assertTrue(i.getNumbers().get(0) == 100 && i.getNumbers().get(1) == 55);
    }
    
    @Test
    public void additionAndSubtractionWorkRight() {
        Calculator calculator = new Calculator();
        Input i = new Input("10-15+20+1-4", calculator);
        i.calculate();
        
        assertTrue(calculator.getCurrentValue() == 12);        
    }
    
    @Test
    public void additionAndSubtractionWorkWhenFirstNumberNegative() {
        Calculator c = new Calculator();
        Input i = new Input("-4+10", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == 6);
    }
    
    @Test
    public void multiplicationWorksRight() {
        Calculator calculator = new Calculator();
        Input i = new Input("2*3*10", calculator);
        i.calculate();
        
        assertTrue(calculator.getCurrentValue() == 60);
    }
    
    @Test
    public void divisionWorksRight() {
        Calculator calculator = new Calculator();
        Input i = new Input("16/2/4", calculator);
        i.calculate();
        
        assertTrue(calculator.getCurrentValue() == 2);
    }
    
    @Test
    public void multiplicationWorksRightWhenFirstIsNegative() {
        Calculator c = new Calculator();
        Input i = new Input("-5*10*3", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == -150);
    }
    
    @Test
    public void divisionWorksRightWhenFirstIsNegative() {
        Calculator c = new Calculator();
        Input i = new Input("-42/7", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == -6);
    }
    
    @Test
    public void calculatingWorksWhenMultiplicationsAndDivisionsComeFirst() {
        Calculator c = new Calculator();
        Input i = new Input("20/4*7+5", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == 40);
    }
    // huomaa että laskin ei toistaiseksi toimi jos + ja - laskut ennen muita
    
    @Test
    public void getProductsWorksRight() {
        Calculator c = new Calculator();
        Input i = new Input("5+7*9+11-300/20-40*2", c);
        i.listNumbersAsDouble();
        // entä jos peräkkäisiä kertolaskuja
        assertTrue(i.getProductsAndQuotients().get(0) == 63 &&
        i.getProductsAndQuotients().get(1) == 15 &&
        i.getProductsAndQuotients().get(2) == 80);
    }
    
}
