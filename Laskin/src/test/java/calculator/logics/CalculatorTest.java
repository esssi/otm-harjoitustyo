
package calculator.logics;

import calculator.logics.Calculator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public void exponentZeroGivesOne(){
        c.add(10);
        c.power(0);
        
        assertTrue(c.getCurrentValue() == 1);
    }
    
    @Test
    public void powerIsCorrect(){
        c.add(2);
        c.power(4);
        
        assertTrue(c.getCurrentValue() == 16);
    }
    
    @Test
    public void zeroFactorialEqualsOne(){
        c.factorial(0);
        
        assertTrue(c.getCurrentValue() == 1);
    }
    
    @Test
    public void factorialGivesCorrectAnswer(){
        c.factorial(9);
        
        assertTrue(c.getCurrentValue() == 362880);
    }
    
    @Test
    public void clearSetsZeroAsCurrentValue(){
        c.subtract(4);
        c.clear();
        
        assertTrue(c.getCurrentValue() == 0);
    }
    
    @Test
    public void negativeExponentDoesNotChangeTheValue(){
        c.add(5);
        c.power(-2);
        
        assertTrue(c.getCurrentValue() == 5);
    }
}
