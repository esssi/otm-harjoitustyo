
package calculator.logics;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CalculatorTest {
    
    Calculator c;
 
    
    @Before
    public void setUp() {
        c = new Calculator();
    }
    
    @Test
    public void currentValueIsZeroInTheBeginning(){
        assertTrue(c.getCurrentValue() == 0);
    }
    
    @Test
    public void additionWorksRight(){
        c.add(5);
        
        assertTrue(c.getCurrentValue() == 5);
    }
    
    @Test
    public void subtractionWorksRight(){
        c.subtract(3);
        
        assertTrue(c.getCurrentValue() == -3);
    }
    
    @Test
    public void multiplicationWorksRight(){
        c.add(2);
        c.multiplyBy(3);
        
        assertTrue(c.getCurrentValue() == 6);
    }
    
    @Test
    public void divisionWorksForNonZeroDivider(){
        c.add(8);
        c.divideBy(4);
        
        assertTrue(c.getCurrentValue() == 2);
    }
    
    @Test
    public void dividingByZeroDoesNotChangeTheValue(){
        c.add(1);
        c.divideBy(0);
        
        assertTrue(c.getCurrentValue() == 1);
    }
    
    @Test
    public void clearSetsZeroAsCurrentValue(){
        c.subtract(4);
        c.clear();
        
        assertTrue(c.getCurrentValue() == 0);
    }

}
