
package calculator.logics;

import org.junit.Test;
import static org.junit.Assert.*;

public class InputTest {
    
    @Test
    public void multiplicationsAreFound() {
        Input i = new Input("12+34*234-100");
        
        assertTrue(i.hasMultiplicationsOrDivisions() == true);
    }
    
    @Test
    public void divisionsAreFound() {
        Input i = new Input("356/2-78+3");
                
        assertTrue(i.hasMultiplicationsOrDivisions() == true);      
    }
    
    @Test
    public void noMultiplicationsOrDivisionsReturnsFalse() {
        Input i = new Input("34324+6545-2+656+3!");
        
        assertTrue(i.hasMultiplicationsOrDivisions() == false);
    }
    
    @Test
    public void listNumbersAsDoubleWorksRight() {
        Input i = new Input("123-56");
        //tämä testi ei mene läpi
        assertTrue(i.getNumbers().get(0) == 123 && i.getNumbers().get(1) == 56);
    }
    
//    @Test
//    public void listOperationSymbolsWorksRight(){
//        Input i = new Input("546-3+10*5+4/2");
        
        // kuinka tätä testataan?
//    }
}
