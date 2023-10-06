package sample;

import java.io.Serializable;

public class Land implements Serializable, Comparable<Land> {

    private final int id;
    private final String name;
    private final long population;
    private final double area;
    private double atackedPeople;

    public Land(String name, int id, long population, double area) {
        this.name = name;
        this.id = id;
        this.population = population;
        this.area = area;
        atackedPeople = 0;
    }

    public double woundedPeople() {
        if (atackedPeople < population) {
            double newAttacks;
            if (atackedPeople == 0)
                newAttacks = Math.random() > 0.05 ? 1 : 0;
            else
                newAttacks = atackedPeople * (1 + Orc.getAttackFactor() / 19) *
                        (population - atackedPeople) / population;
            if (atackedPeople + newAttacks > population)
                newAttacks = population - atackedPeople;
            atackedPeople += newAttacks;
            return newAttacks;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getAtackedPeople() {
        return atackedPeople;
    }

    @Override
    public int compareTo(Land o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + population + " " + area;
    }

}