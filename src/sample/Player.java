package sample;

import java.io.Serializable;

class Player implements Serializable, Comparable<Player> {
    private final String name;
    private final int games;
    private final Orc.DifficultyLevel difficultyLevel;


    public Player(String name, int games, Orc.DifficultyLevel difficultyLevel) {
        this.name = name;
        this.games = games;
        this.difficultyLevel = difficultyLevel;
    }

    public String getName() { return name; }

    public int getGames() { return games; }

    public Orc.DifficultyLevel getDifficultyLevel() { return difficultyLevel; }

    @Override
    public int compareTo(Player ps) {
        return games - ps.games;
    }
}
