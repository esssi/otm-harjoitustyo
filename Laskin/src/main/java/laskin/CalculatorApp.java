
package laskin;

public class CalculatorApp {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        //testikoodia... tänne tulee myöhemmin käyttöliittymä
        calculator.add(9);
        System.out.println("laskimen arvo nyt: " + calculator.getCurrentValue());
        calculator.divideBy(0);
        calculator.divideBy(3);
        System.out.println("laskimen arvo nyt: " + calculator.getCurrentValue());
        calculator.power(6);
        System.out.println("3 potenssiin 6 on " + calculator.getCurrentValue());
        calculator.power(0);
        System.out.println("nollas potenssi: " + calculator.getCurrentValue());
        calculator.factorial(6);
        System.out.println("6-kertoma on " + calculator.getCurrentValue());
        calculator.clear();
        System.out.println("laskimen arvo nollauksen jälkeen: " + calculator.getCurrentValue());
    }
    
}
