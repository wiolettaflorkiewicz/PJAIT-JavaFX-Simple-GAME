package sample;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

class PlayerListView extends ListView<Player> {

    public PlayerListView() {
        setItems(getPlayerList());
        setCellFactory(listView -> new PlayerListCell());
    }

    private ObservableList<Player> getPlayerList() {
        List<Player> playerList = Scores.readScores().sorted();
        return FXCollections.observableArrayList(playerList);
    }
}

class PlayerListCell extends ListCell<Player> {

    @Override
    protected void updateItem(Player player, boolean empty) {
        super.updateItem(player, empty);
        if (player != null) {
            setText((getIndex() + 1) + ". " + player.getName() + ", " + player.getDifficultyLevel() + ", Points: " + player.getGames());
        } else {
            setText(null);
        }
    }
}

