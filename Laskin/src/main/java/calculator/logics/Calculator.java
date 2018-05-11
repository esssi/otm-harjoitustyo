
package calculator.logics;

public class Calculator {
    private double currentValue;
    
    public Calculator() {
        this.currentValue = 0;
    }
    
    public void clear() {
        currentValue = 0;
    }
    
    public double getCurrentValue() {
        return currentValue;
    }
    
    public void add(double x) {
        currentValue += x;
    }
    
    public void subtract(double x) {
        currentValue -= x;
    }
    
    public void divideBy(double x) {
        if (x == 0) {
            return;
        }
        
        currentValue /= x;
    }
    
    public void multiplyBy(double x) {
        currentValue *= x;
    }
    
}
