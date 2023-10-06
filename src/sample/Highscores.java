package sample;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import java.io.*;

class HighscoresWindow{
    ListView<Player> playerList;

    public HighscoresWindow() {
        Stage highscoresWindow = new Stage();
        highscoresWindow.initModality(Modality.APPLICATION_MODAL);
        highscoresWindow.setTitle("High Scores");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        playerList = new ListView<>(Scores.readScores());
        root.setCenter(playerList);

        Scene scene = new Scene(root, 500, 600);
        highscoresWindow.setScene(scene);
        highscoresWindow.show();
    }

}




class Scores {
    private static ObservableList<Player> players = FXCollections.observableArrayList();

    public static ObservableList<Player> readScores() {
        players.clear();
        try (FileInputStream fis = new FileInputStream("sample/highscore");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true)
                players.add((Player) ois.readObject());
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return players;
    }

    public static void saveGame() {
        if (!players.isEmpty()) {
            try (FileOutputStream fos = new FileOutputStream("sample/highscore");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (Player p : players)
                    oos.writeObject(p);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void addPlayer(String name, int amountOfPoints, Orc.DifficultyLevel difficultyLevel) {
        players = readScores();
        players.add(new Player(name, amountOfPoints, difficultyLevel));
        players.sort(Comparator.reverseOrder());
        saveGame();
    }
}