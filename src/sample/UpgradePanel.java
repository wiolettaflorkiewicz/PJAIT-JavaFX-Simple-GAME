package sample;

//PROGRAM JEST NIE JEST DO KONCA ZROBIONY

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.awt.*;
import java.util.ArrayList;

class UpgradePanel extends Pane {
    ArrayList<UpgradeButtons> upgradeButtons;
    World world;

   public UpgradePanel(World world) {
        this.world = world;
        setBackground(new Background(new BackgroundFill(Color.rgb(225, 177, 95), CornerRadii.EMPTY, Insets.EMPTY)));
        setPrefSize(new Dimension(230, 700));
        upgradeLoad();
    }

    public void setPrefSize(Dimension dimension) {
        setPrefSize(dimension.getWidth(), dimension.getHeight());
    }

    private void upgradeLoad() {
        upgradeButtons = new ArrayList<>();
        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade Knight") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(1);
                    }
                },
                "By leveling up your knights, you're getting closer to defeating the orc scourge"
        ));
        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade a mounted knight") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(1);
                    }
                },
                "By leveling up your mounted knights, you come closer to defeating the orc scourge"
        ));
        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade the Dragon Riders") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(1);
                    }
                },
                "By leveling up your dragonriders, you're getting closer to defeating the orc scourge"
        ));
        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade archers") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(2);
                    }
                },
                "By leveling up archers, you get closer to defeating the orc scourge"
        ));
        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade dwarves") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(2);
                    }
                },
                "By leveling up the dwarves, you get closer to defeating the orc scourge"
        ));
        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade wizards") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(3);
                    }
                },
                "By leveling up your wizards, you get closer to defeating the orc scourge"
        ));

        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade Hobbits") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(3);
                    }
                },
                "By leveling up hobbits, you get closer to defeating the orc scourge"
        ));

        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade Citadel Guardian") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(3);
                    }
                },
                "By leveling up Citadel Guardian, you get closer to defeating the orc scourge"
        ));

        upgradeButtons.add(new UpgradeButtons(
                new Upgrade("Upgrade Elfs") {
                    @Override
                    public void upgrade() {
                        Orc.attackRateReduction(3);
                    }
                },
                "By leveling up Elfs, you get closer to defeating the orc scourge"
        ));
    }
}


class UpgradeButtons extends Button {
    Upgrade upgrade;
    World world;

    public UpgradeButtons(Upgrade upgrade, String description) {
            this.upgrade = upgrade;
            setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            setBackground(new Background(new BackgroundFill(Color.rgb(213, 168, 62), CornerRadii.EMPTY, Insets.EMPTY)));
            addElements(description);
            setOnAction(e -> upgradeButtons());
            setPrefSize(new Dimension(200, 50));
    }

    public void setPrefSize(Dimension dimension) {
        setPrefSize(dimension.getWidth(), dimension.getHeight());
    }

    private void addElements(String description) {
        setText(upgrade.getName() + " Upgrade");
        setTooltip(new Tooltip("Upgrade " + description));
    }


    private void upgradeButtons() {
        if (!(world.getPoints() < Upgrade.getUpgradePrice())) {
            upgrade.upgrade();
            world.deleteBoughtPoints(Upgrade.getUpgradePrice());
            setDisable(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You can't afford this upgrade, please wait.");
            alert.showAndWait();
        }
    }

}

abstract class Upgrade {

    private final String name;

    public abstract void upgrade();

    public Upgrade(String name) { this.name = name; }

    public String getName() { return name; }

    public static int getUpgradePrice() {
        return 100; }
}


