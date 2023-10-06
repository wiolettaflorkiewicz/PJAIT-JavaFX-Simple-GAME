package sample;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class DataWindow extends Stage {

    private final TextField textField;
    private final Label prompt;
    private final Button enterButton;

    public DataWindow(int points, Orc.DifficultyLevel difficultyLevel) {
        textField = new TextField();
        prompt = new Label("Name: ");
        enterButton = new Button("Enter");
        dodajOkno(points, difficultyLevel);
    }

    private void dodajOkno(int points, Orc.DifficultyLevel difficultyLevel) {
        setWidth(400);
        setHeight(100);
        HBox layout = new HBox();
        layout.getChildren().addAll(prompt, textField, enterButton);
        setScene(new Scene(layout));
        setOnCloseRequest(e -> System.exit(0));
        enterButton.setOnAction(e ->  {
            String name = textField.getText();
            if (!name.equals("")) {
                Scores.addPlayer(name, points, difficultyLevel);
                close();
                new Menu();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No name");
                alert.setHeaderText("You have to add name");
                alert.showAndWait();
            }
        });
        show();
    }
}