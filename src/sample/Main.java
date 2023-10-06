package sample;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create an instance of the MenuWindow class
        Menu menuWindow = new Menu();
        // Show the window
        menuWindow.show();
    }
}