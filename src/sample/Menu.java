package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Menu extends Stage {

    public Menu() {

        Font customFont = Font.loadFont(getClass().getResourceAsStream("MorrisRoman.ttf"), 50);
        Font titleFont = Font.loadFont(getClass().getResourceAsStream("MorrisRoman.ttf"), 70);
        // Set the title of the window
        setTitle("Lord of The Rings: The Plague of Orcs");

        VBox vBox = new VBox();
        // Create buttons
        Button startGameButton = new Button("START GAME");
        startGameButton.setFont(customFont);
        Button highscoresButton = new Button("HIGHSCORES");
        highscoresButton.setFont(customFont);
        Button exitButton = new Button("EXIT");
        exitButton.setFont(customFont);
        startGameButton.setBackground(Background.EMPTY);
        highscoresButton.setBackground(Background.EMPTY);
        exitButton.setBackground(Background.EMPTY);
        // Add buttons to the VBox layout
        vBox.getChildren().addAll(startGameButton, highscoresButton, exitButton);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        startGameButton.setTextFill(Color.web("#c7ae48"));
        highscoresButton.setTextFill(Color.web("#c7ae48"));
        exitButton.setTextFill(Color.web("#c7ae48"));
        Text titleText = new Text("Lord of The Rings: The Plague of Orcs");
        titleText.setFill(Color.valueOf("#c7ae48"));
        titleText.setFont(titleFont);
        vBox.getChildren().add(0, titleText);

        Image backgroundImage1 = new Image("sample/Images/menu.jpg");
        BackgroundSize backgroundSize1 = new BackgroundSize(100, 100, true, true, true, true);
        vBox.setBackground(new Background(new BackgroundImage(backgroundImage1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize1)));
        ImageView imageView = new ImageView(backgroundImage1);
        imageView.setFitWidth(1200);
        imageView.setFitHeight(800);

        // Create a new scene
        Scene scene = new Scene(vBox, 1200, 800);
        // Set the scene of the window
        setScene(scene);

        Text text = new Text("Menu");

        // Set the custom font to the Text object
        text.setFont(customFont);

        startGameButton.setOnAction(event -> {
            DifficultySelectionWindow difficultySelectionWindow = new DifficultySelectionWindow();
            difficultySelectionWindow.show();
            difficultySelectionWindow.setOnHidden(e -> new DifficultySelectionWindow());
        });

        highscoresButton.setOnAction(event -> {
            HighscoresWindow highscoresWindow = new HighscoresWindow();


        });

        exitButton.setOnAction(event -> System.exit(0));
        show();
    }
}
