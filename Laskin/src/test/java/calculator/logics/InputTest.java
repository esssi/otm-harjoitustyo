
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
    public void additionAndSubtractionWorkRight() {
        Calculator calculator = new Calculator();
        Input i = new Input("10-15+20+1-4", calculator);
        i.calculate();
        
        assertTrue(calculator.getCurrentValue() == 12);        
    }
    // edelleen ongelmia negatiivisten lukujen kanssa
}
