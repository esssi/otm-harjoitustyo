
package calculator.gui;
import calculator.logics.Calculator;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

public class CalculatorApp extends Application {
    
    @Override
    public void start(Stage window) {
        Calculator calculator = new Calculator();
   
        BorderPane layout = new BorderPane();
        Label text = new Label("");
        text.setMinHeight(40);
        layout.setTop(text);
        
        GridPane buttons = new GridPane();
        // muista muuttaa napit samankokoisiksi
        int number = 9;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                Button numberButton = new Button("" + number);
                buttons.add(numberButton, j, i);
                number--;

                numberButton.setOnAction((event) -> {
                    String currentText = text.getText();
                    text.setText(currentText + numberButton.getText());
                });
            }
        }
        
        Button zero = new Button("0");
        zero.setOnAction((event) -> {
            String currentText = text.getText();
            text.setText(currentText + "0");
        });
        buttons.add(zero, 2, 4);
        // harkitse nappien luomisen mahdollistavaa luokkaa? liikaa tekstiä nyt
        Button clear = new Button("C");
        clear.setOnAction((event) -> {
            calculator.clear();
            text.setText("");
        });
        buttons.add(clear, 4, 1);
        //napit eivät vielä suorita mitään laskuja
        Button equals = new Button("=");
        equals.setOnAction((event) -> {
            text.setText("" + calculator.getCurrentValue());
        });
        buttons.add(equals, 4, 4);
        
        Button plus = new Button("+");
        plus.setOnAction((event) -> {
            text.setText(text.getText() + plus.getText());
        });
        buttons.add(plus, 5, 1);
        
        Button minus = new Button("-");
        minus.setOnAction((event) -> {
            text.setText(text.getText() + minus.getText());
        });
        buttons.add(minus, 5, 2);
        
        Button times = new Button("*");
        times.setOnAction((event) -> {
            text.setText(text.getText() + times.getText());
        });
        buttons.add(times, 5, 3);
        
        Button divide = new Button("/");
        divide.setOnAction((event) -> {
            text.setText(text.getText() + divide.getText());
        });
        buttons.add(divide, 5, 4);
        
        Button exponent = new Button("^");
        exponent.setOnAction((event) -> {
            //kesken
            text.setText(text.getText() + exponent.getText());
        });
        buttons.add(exponent, 4, 2);
        
        Button factorial = new Button("!");
        factorial.setOnAction((event) -> {
            //kesken
            text.setText(text.getText() + factorial.getText());
        });
        buttons.add(factorial, 4, 3);
        
        Button point = new Button(".");
        point.setOnAction((event) -> {
            text.setText(text.getText() + point.getText());
        });
        buttons.add(point, 1, 4);
        
        Button negative = new Button("(-)");
        negative.setOnAction((event) -> {
            text.setText(text.getText() + "-");
        });
        buttons.add(negative, 3, 4);
        
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
