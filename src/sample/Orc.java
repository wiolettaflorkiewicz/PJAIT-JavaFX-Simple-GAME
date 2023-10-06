package sample;

public class Orc {

    enum DifficultyLevel {EASY, MEDIUM, HARD}
    private static double attackFactor;
    private final DifficultyLevel difficultyLevel;

    public Orc(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        switch (difficultyLevel) {
            case EASY -> attackFactor = 0.01;
            case MEDIUM -> attackFactor = 0.02;
            case HARD -> attackFactor = 0.03;
        }
    }

    public void ranniLudzie(World swiat) {
        swiat.ranniLudzie();
    }

    public static void attackRateReduction(double percent) { attackFactor *= 1 - percent; }

    public static double getAttackFactor() { return attackFactor; }

    public DifficultyLevel getLevelDifficulty() { return difficultyLevel; }
}