package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class DifficultySelectionWindow extends Stage {

    public DifficultySelectionWindow() {
        setTitle("Select Difficulty");
        VBox vBox = new VBox();

        // Create buttons for difficulty levels
        Button easyButton = new Button("EASY");
        Button mediumButton = new Button("MEDIUM");
        Button hardButton = new Button("HARD");

        easyButton.setFont(Font.font("Verdana", 30));
        easyButton.setStyle("-fx-background-color: #658864;");
        easyButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));
        mediumButton.setFont(Font.font("Verdana", 30));
        mediumButton.setStyle("-fx-background-color: #E4C988;");
        mediumButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));

        hardButton.setFont(Font.font("Verdana", 30));
        hardButton.setStyle("-fx-background-color: #F55050;");
        hardButton.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));

        easyButton.setPrefWidth(200);
        easyButton.setPrefHeight(50);
        mediumButton.setPrefWidth(200);
        mediumButton.setPrefHeight(50);
        hardButton.setPrefWidth(200);
        hardButton.setPrefHeight(50);

        vBox.getChildren().addAll(easyButton, mediumButton, hardButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        Scene scene = new Scene(vBox, 400, 300);
        setScene(scene);

        easyButton.setOnAction(event -> GameWindow.start(Orc.DifficultyLevel.EASY));
        mediumButton.setOnAction(event -> GameWindow.start(Orc.DifficultyLevel.MEDIUM));
        hardButton.setOnAction(event -> GameWindow.start(Orc.DifficultyLevel.HARD));
    }
}