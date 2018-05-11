
package calculator.logics;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class InputTest {
    Calculator c;
    
    @Before
    public void setUp() {
        c = new Calculator();
    }
    
    @Test
    public void multiplicationsAreFound() {
        Input i = new Input("12+34*234-100", c);
        
        assertTrue(i.hasMultiplicationsOrDivisions() == true);
    }
    
    @Test
    public void divisionsAreFound() {
        Input i = new Input("356/2-78+3", c);
                
        assertTrue(i.hasMultiplicationsOrDivisions() == true);      
    }
    
    @Test
    public void noMultiplicationsOrDivisionsReturnsFalse() {
        Input i = new Input("34324+6545-2+656+3", c);
        
        assertTrue(i.hasMultiplicationsOrDivisions() == false);
    }
    
    @Test
    public void listOperationSymbolsWorksRight() {
        Input i = new Input("634*20+5+1", c);
        i.listOperationSymbols();
        
        assertTrue(i.getOperationSymbols().get(0) == 3 && 
        i.getOperationSymbols().get(1) == 6 && i.getOperationSymbols().get(2) == 8);
    }
    
    @Test
    public void listNumbersAsDoubleWorksRight() {
        Input i = new Input("369/3+51", c);
        i.listNumbersAsDouble();
        
        assertTrue(i.getNumbers().get(0) == 369 && i.getNumbers().get(1) == 3 &&
        i.getNumbers().get(2) == 51);
    }
    
    @Test
    public void listNumbersAsDoubleWorksRightWhenFirstIsNegative() {
        Input i = new Input("-100+55", c);
        i.listNumbersAsDouble();
        
        assertTrue(i.getNumbers().get(0) == 100 && i.getNumbers().get(1) == 55);
    }
    
    @Test
    public void calculateWorksRightWhenAdding() {
        Input i = new Input("1+2+3", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == 6);
    }
    
    @Test
    public void calculateWorksRightWhenSubtracting() {
        Input i = new Input("-5-10",c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == -15);
    }
    
    @Test
    public void calculateWorksWhenMultiplying() {
        Input i = new Input("2*3*10",c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == 60);
    }
    
    @Test
    public void calculateWorksWhenMultiplyingNegativeNumber() {
        Input i = new Input("-4*5", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == -20);
    }
    
    @Test
    public void calculateWorksWhenDividing() {
        Input i = new Input("27/3/3", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == 3);
    } 
    
    @Test
    public void calculateWorksRightWhenDividingNegativeNumber() {
        Input i = new Input("-40/8", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == -5);
    }
    
    @Test
    public void calculateWorksForSeveralBinaryOperations() {
        Input i = new Input("-2+10/5-2*3*4+16+14", c);
        i.calculate();
        
        assertTrue(c.getCurrentValue() == 6);
    }
}
