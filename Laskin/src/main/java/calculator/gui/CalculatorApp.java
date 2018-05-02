
package calculator.gui;
import calculator.logics.Calculator;
import calculator.logics.Input;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

public class CalculatorApp extends Application {
    
    private boolean resultShowing;
    Calculator calculator = new Calculator();
    Label text = new Label("");
    
    private void clearText() {
        calculator.clear();
        text.setText("");
        this.resultShowing = false;
    }
    
    public void numberButtonAction(Button b){
        b.setOnAction((event) -> {
            /*tarkistetaan, ettei tulos jää näytölle, jos käyttäjä ei
            muista painaa C-nappulaa ennen numeroita*/
            if (this.resultShowing == true) {
                this.clearText();
            }
            String currentText = text.getText();
            text.setText(currentText + b.getText());
        });
    }
    
    public void operationButtonAction(Button b) {
        b.setOnAction((event) -> {
            if (this.resultShowing == true) {
                calculator.subtract(Double.parseDouble(text.getText()));
            }
            text.setText(text.getText() + b.getText());
            this.resultShowing = false;
        });
    }
    
    @Override
    public void start(Stage window) {
        this.resultShowing = false;
        
        BorderPane layout = new BorderPane();
        
        text.setMinHeight(40);
        layout.setTop(text);
        
        GridPane buttons = new GridPane();
        
        int number = 9;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                Button numberButton = new Button("" + number);
                buttons.add(numberButton, j, i);
                number--;

                this.numberButtonAction(numberButton);
            }
        }
        
        Button zero = new Button("0");
        zero.setOnAction((event) -> {
            this.numberButtonAction(zero);
        });
        buttons.add(zero, 2, 4);
        
        Button clear = new Button("C");
        clear.setOnAction((event) -> {
            this.clearText();
        });
        buttons.add(clear, 4, 1);
        // kertoma- ja potenssinapit eivät vielä toimi laskimen kanssa oikein
        Button equals = new Button("=");
        equals.setOnAction((event) -> {
            Input input = new Input(text.getText(), calculator);
            input.calculate();
            text.setText("" + calculator.getCurrentValue());
            this.resultShowing = true;
        });
        buttons.add(equals, 3, 4);
        
        Button plus = new Button("+");
        this.operationButtonAction(plus);
        buttons.add(plus, 5, 1);
        
        Button minus = new Button("-");
        this.operationButtonAction(minus);
        buttons.add(minus, 5, 2);
        
        Button times = new Button("*");
        this.operationButtonAction(times);
        buttons.add(times, 5, 3);
        
        Button divide = new Button("/");
        this.operationButtonAction(divide);
        buttons.add(divide, 5, 4);
        
        Button exponent = new Button("^");
        this.operationButtonAction(exponent);
        buttons.add(exponent, 4, 2);
        
        Button factorial = new Button("!");
        this.operationButtonAction(factorial);
        buttons.add(factorial, 4, 3);
        
        Button point = new Button(".");
        point.setOnAction((event) -> {
            text.setText(text.getText() + point.getText());
        });
        buttons.add(point, 1, 4);
        
        layout.setCenter(buttons);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setTitle("Laskin");
        window.show();
    }
    
    public static void main(String[] args) {
        launch(CalculatorApp.class);
    }
    
}
