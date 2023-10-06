package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class GameWindow {
    private final Stage stage;
    Orc orc;
    World world;

    public GameWindow(Orc.DifficultyLevel difficultyLevel) {
        orc = new Orc(difficultyLevel);
        world = new World();
        // Create a new window
        stage = new Stage();
        stage.setTitle("New Game");

        // Create a BorderPane layout
        BorderPane borderPane = new BorderPane();

        // Set the background image of the BorderPane layout
        Image backgroundImage = new Image("sample/Images/Map.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        borderPane.setBackground(new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize)));
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(700);

        ImageView icon = new ImageView(new Image("sample/gandalf.png"));
        icon.setX(0);
        icon.setY(0);
        icon.setFitWidth(50);
        icon.setFitHeight(50);
        borderPane.getChildren().add(icon);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(4), icon);
        animation.setFromX(100);
        animation.setFromY(100);
        animation.setToX(300);
        animation.setToY(300);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();

        ImageView icon2 = new ImageView(new Image("sample/horseknight.png"));
        icon2.setX(0);
        icon2.setY(0);
        icon2.setFitWidth(50);
        icon2.setFitHeight(50);
        borderPane.getChildren().add(icon2);
        TranslateTransition animation1 = new TranslateTransition(Duration.seconds(4), icon2);
        animation1.setFromX(400);
        animation1.setFromY(300);
        animation1.setToX(220);
        animation1.setToY(310);
        animation1.setCycleCount(Animation.INDEFINITE);
        animation1.setAutoReverse(true);
        animation1.play();

        ImageView icon3 = new ImageView(new Image("sample/dragon.png"));
        icon3.setX(0);
        icon3.setY(0);
        icon3.setFitWidth(90);
        icon3.setFitHeight(90);
        borderPane.getChildren().add(icon3);
        TranslateTransition animation2 = new TranslateTransition(Duration.seconds(4), icon3);
        animation2.setFromX(700);
        animation2.setFromY(500);
        animation2.setToX(550);
        animation2.setToY(550);
        animation2.setCycleCount(Animation.INDEFINITE);
        animation2.setAutoReverse(true);
        animation2.play();
        // Create the top panel
        HBox topPanel = new HBox();
        topPanel.setPrefHeight(50);
        topPanel.setMaxHeight(50);
        topPanel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));

        // Create a label for the timer
        Label timerLabel = new Label("Timer:");
        timerLabel.setTextFill(Color.web("#FFFFFF"));
        timerLabel.setFont(Font.font("Verdana", 20));



        // Create a ProgressBar for the timer
        ProgressBar timerProgressBar = new ProgressBar();
        timerProgressBar.setPrefWidth(150);

        // Create a label for the points
        Label pointsLabel = new Label("Points:");
        pointsLabel.setTextFill(Color.web("#FFFFFF"));
        pointsLabel.setFont(Font.font("Verdana", 20));

        // Create a label to display the current points
        Label currentPointsLabel = new Label("0");
        currentPointsLabel.setTextFill(Color.web("#FFFFFF"));
        currentPointsLabel.setFont(Font.font("Verdana", 20));;

        // Create a new thread for the timer
        Thread timerThread = new Thread(() -> {
            int counter = 0;
            int timeDuration = 120;
            while (counter++ < timeDuration) {
                int minutes = (timeDuration - counter) / 60;
                int seconds = (timeDuration - counter) % 60;

                Platform.runLater(() -> {
                timerLabel.setText(minutes + ":" + (seconds < 10 ? "0" + seconds : seconds) + " "); });

                World.addPoints();

                timerProgressBar.setProgress(counter / (double) timeDuration);
                if (counter % 4 == 0) {
                    orc.ranniLudzie(world);
                    if (world.getWoundedPeople() / world.getPopulation() > 1) {
                        break;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }

            int points = World.getPoints();
            switch (orc.getLevelDifficulty()) {
                case HARD -> points *= 10;
                case MEDIUM -> points *= 5;
            }
            stage.close();
            new DataWindow(points, orc.getLevelDifficulty());

        });

        // Start the timer thread
        timerThread.start();

        topPanel.getChildren().addAll(timerLabel, timerProgressBar, pointsLabel, currentPointsLabel);


        // Create a label to display the name of the selected country
        Label countryNameLabel = new Label();
        countryNameLabel.setFont(new Font(20));
        countryNameLabel.setPadding(new Insets(10));

        // Create labels to display the population and area of the selected country
        Label populationLabel = new Label();
        populationLabel.setFont(new Font(20));
        populationLabel.setPadding(new Insets(10));

        Label areaLabel = new Label();
        areaLabel.setFont(new Font(20));
        areaLabel.setPadding(new Insets(10));

        Label woundedPeopleLabel = new Label();
        populationLabel.setFont(new Font(20));
        populationLabel.setPadding(new Insets(10));

        topPanel.setAlignment(Pos.CENTER);
        topPanel.setSpacing(150);
        topPanel.setBackground(new Background(new BackgroundFill(Color.web("#FAD6A5"), CornerRadii.EMPTY, Insets.EMPTY)));




        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            int score = World.addPoints();
            double scoreAdding = Double.parseDouble(String.valueOf(score));
            currentPointsLabel.setText(String.valueOf(scoreAdding));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        // Create a button for the description of the game
        Button gameDescriptionButton = new Button("Game Description");
        topPanel.getChildren().add(gameDescriptionButton);
        gameDescriptionButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
        gameDescriptionButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Description");
            alert.setHeaderText("Orc Invasion");
            alert.setContentText("In this game, players must defend their kingdom from orcs by managing resources and upgrading their defenses. As the game progresses, the difficulty level increases and players must adapt to new challenges. The goal of the game is to survive for as long as possible and score the most points.");
            alert.showAndWait();
        });

        VBox rightPanel = new VBox();

        // Create a list of countries
        // Add more countries as necessary
        ObservableList<Land> countries = FXCollections.observableArrayList(
               new Land("Beleriand",1, 1453253, 3455333),
               new Land("Rohan",1, 1453253, 3455333),
               new Land("Gondor",1, 1453253, 3455333),
                new Land("Arnor",1, 1453253, 3455333),
                new Land("Mordor",1, 1453253, 3455333),
                new Land("Rhovanion",1, 1453253, 3455333),
                new Land("Forodwaith",1, 1453253, 3455333),
        new Land("Eriador",1, 1453253, 3455333));

        // Create a ListView to display the countries
        ListView<Land> listView = new ListView<>(countries);
        listView.setPrefWidth(190);
        listView.setPrefHeight(80);

        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Land> call(ListView<Land> param) {
                return new ListCell<>() {
                   @Override
                    protected void updateItem(Land item, boolean empty) {
                       super.updateItem(item, empty);
                      if (item != null) {
                           setText(item.getName());
                            setOnMouseClicked(event -> {
                                //Display the other information when the cell is clicked on
                                double atackedPeopleString = item.getAtackedPeople();
                                double atackedPeopleDouble = Double.parseDouble(String.valueOf(atackedPeopleString));
                                woundedPeopleLabel.setText("Wounded people: " + atackedPeopleDouble);
                                countryNameLabel.setText("Land: "+ item.getName());
                                long populationString = ( item.getPopulation());
                                double populationDouble = Double.parseDouble(String.valueOf(populationString));
                                populationLabel.setText("Population: " + populationDouble);
                                double areaString = item.getArea();
                                double areaDouble = Double.parseDouble(String.valueOf(areaString));
                                areaLabel.setText("Arena: " + areaDouble);
                            });
                       } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        // Add the ListView to the right panel
       rightPanel.getChildren().add(listView);

        // Add the country information labels to the right panel
        rightPanel.getChildren().addAll(countryNameLabel, populationLabel, areaLabel, woundedPeopleLabel);

        // Create the right panel
        rightPanel.setPrefWidth(250); // set width to 300px
        rightPanel.setSpacing(10); // set spacing between buttons to 10px
        rightPanel.setAlignment(Pos.CENTER);
        rightPanel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));

        // Create the upgrade buttons
        UpgradePanel upgradePanel = new UpgradePanel(world);
        for (UpgradeButtons upgradeButton : upgradePanel.upgradeButtons) {
            rightPanel.getChildren().add(upgradeButton);
        }

        borderPane.setRight(rightPanel);

        // Set the background color of the right panel to blue
        rightPanel.setBackground(new Background(new BackgroundFill(Color.web("#FAD6A5"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Add the right panel to the BorderPane layout
        borderPane.setRight(rightPanel);

        // Add the top panel and right panel to the BorderPane layout
        borderPane.setTop(topPanel);
        borderPane.setRight(rightPanel);

        // Create a new scene
        Scene scene = new Scene(borderPane, 1400, 800);

        // Set the scene of the window
        stage.setScene(scene);


    }

    public static void start(Orc.DifficultyLevel difficultyLevel) {
        GameWindow game = new GameWindow(difficultyLevel);
        game.stage.show();
    }


}