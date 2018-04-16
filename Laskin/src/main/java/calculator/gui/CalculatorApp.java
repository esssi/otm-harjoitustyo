
package calculator.gui;
import calculator.logics.Calculator;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;

public class CalculatorApp extends Application{
    
    @Override
    public void start(Stage window){
        Calculator calculator = new Calculator();
   
        BorderPane layout = new BorderPane();
        Label text = new Label("");
        layout.setTop(text);
        
        GridPane buttons = new GridPane();
        
        int number = 9;
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                Button numberButton = new Button("" + number);
                buttons.add(numberButton, j ,i);
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
        
        Button equals = new Button("=");
        equals.setOnAction((event) -> {
            text.setText("" + calculator.getCurrentValue());
        });
        buttons.add(equals, 1, 4);
        
        Button plus = new Button("+");
        plus.setOnAction((event) -> {
            // ei toimi sitten kun ruudussa näkyy operaation symboli
            // ehkä kannattaa pilkkoa syöte osiin?
            int figure = Integer.parseInt(text.getText());
            calculator.add(figure);
            text.setText(text.getText() + "+");
        });
        buttons.add(plus, 4, 2);
        
        layout.setCenter(buttons);
        // mieti nappien asettelua
        Scene scene = new Scene(layout, 150, 150);
        window.setScene(scene);
        window.setTitle("Laskin");
        window.show();
    }
    
    public static void main(String[] args) {
        launch(CalculatorApp.class);
    }
    
}
