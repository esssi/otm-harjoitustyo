
package calculator.logics;

public class Calculator {
    private double currentValue;
    
    public Calculator(){
        this.currentValue = 0;
    }
    
    public void clear(){
        currentValue = 0;
    }
    
    public double getCurrentValue(){
        return currentValue;
    }
    
    public void add(double x){
        currentValue += x;
    }
    
    public void subtract(double x){
        currentValue -= x;
    }
    
    public void divideBy(double x){
        if(x == 0){
            return;
        }
        
        currentValue /= x;
    }
    
    public void multiplyBy(double x){
        currentValue *= x;
    }
    
    public void power(int n){
        if(n < 0){
            return;
        }
        
        if(n == 0){
            currentValue = 1;
        }
        
        double base = currentValue;
        for(int i = 1; i < n; i++){
            multiplyBy(base);
        }
    }
    
    public void factorial(int n){
        if(n == 0){
            currentValue = 1;
        } else {
            for(int i = n - 1; i > 0; i--){
                n *= i;
            }
            
            currentValue = n;
            // huom muuttaa laskimen arvon kertomaksi, ei lisää tms kertomaa
        }   
    }
}
