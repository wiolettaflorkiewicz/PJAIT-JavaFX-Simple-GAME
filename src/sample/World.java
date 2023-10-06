package sample;

import java.util.*;


public class World {
    private final List<Land> lands;
    private final long population;
    private int woundedPeople;
    private static int points;
    private static int maxPoints;

    public World() {
        points = 1;
        maxPoints = points;
        lands = new ArrayList<>();
        population = 0;
        woundedPeople = 0;
    }

    public void ranniLudzie() {
        lands.forEach(land -> woundedPeople += land.woundedPeople());
    }

    public List<Land> getKraina() {
        return lands;
    }

    public long getPopulation() {
        return population;
    }

    public int getWoundedPeople() {
        return woundedPeople;
    }

    public static int getPoints() { return points; }

    public static int getMaxPoints() { return maxPoints; }

    public static int addPoints() { return points += 5;
    }

    public static void deleteBoughtPoints(int n) { points = points - n; }

    public void setWoundedPeople(int woundedPeople) {
        this.woundedPeople = woundedPeople;
    }


}